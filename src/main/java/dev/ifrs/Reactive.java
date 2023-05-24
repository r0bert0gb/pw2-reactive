package dev.ifrs;

import dev.ifrs.model.Cat;
import dev.ifrs.repository.CatRepository;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;

@Path("/cat")
public class Reactive {

    @Inject
    CatRepository rep;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Cat> createCat(@FormParam("name") String name, @FormParam("age") int age) {

        Cat cat = new Cat();

        cat.setName(name);
        cat.setAge(age);

        return rep.persist(cat)
                .onItem().transform(gato -> gato)
                .onFailure().recoverWithItem(cat)
                .onItem().ifNull().failWith(new WebApplicationException(500));
    }
}
