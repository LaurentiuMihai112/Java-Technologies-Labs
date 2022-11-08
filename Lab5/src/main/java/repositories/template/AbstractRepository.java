/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories.template;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Laurentiu
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractRepository<T, ID extends Serializable> implements Serializable {

    protected Class<T> entityClass;
    @PersistenceContext(name = "jpaPU")
    private EntityManager em;

    protected AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @PostConstruct
    protected void init() {
    } //why this instead of the constructor?

    public T newInstance() {
        try {
            return entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            //...should throw a custom runtime exception
            return null;
        }
    }

    public void persist(T entity) {
        em.persist(entity);
    }

    public void update(T entity) {
        em.merge(entity);
    }

    public void remove(T entity) {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
    }

    public T refresh(T entity) {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.refresh(entity);
        return entity;
    }

    public T findById(ID id) {
        if (id == null) {
            return null;
        }
        return em.find(entityClass, id);
    }

    public void clearCache() {
        em.getEntityManagerFactory().getCache().evictAll();
    }

    public List<T> findAll() {
        return getResultList(entityClass.getSimpleName() + ".findAll");
    }

    public List<T> getResultList(String namedQuery, Object... params) {
        String prefix = entityClass.getSimpleName();
        if (!namedQuery.startsWith(prefix)) {
            namedQuery = prefix + "." + namedQuery;
        }
        Query query = em.createNamedQuery(namedQuery);
        if (params != null) {
            for (int i = 1; i <= params.length; i++) {
                query.setParameter(i, params[i - 1]);
            }
        }
        return query.getResultList();
    }
// public T getSingleResult(String namedQuery, Object... params) { ... }
// public int getIntResult(String namedQuery, Object... params) {
}
