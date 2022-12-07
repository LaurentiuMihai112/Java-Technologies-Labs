package com.javatech.lab8;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class ApplicationResources {
    @Produces
    @PersistenceContext(unitName = "jpa8")
    private EntityManager entityManager;
}