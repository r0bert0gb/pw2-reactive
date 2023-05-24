package dev.ifrs.repository;

import dev.ifrs.model.Cat;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CatRepository implements PanacheRepository<Cat> {

}
