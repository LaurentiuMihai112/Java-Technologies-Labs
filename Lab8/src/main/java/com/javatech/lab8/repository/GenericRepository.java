package com.javatech.lab8.repository;

import com.javatech.lab8.entity.ApplicationEntity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;


public class GenericRepository<T extends ApplicationEntity, ID> implements Repository<T, ID> {

    @Inject
    protected EntityManager entityManager;


    public GenericRepository() {
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public T save(T obj) {
        if (obj.getId() == null) {
            entityManager.persist(obj);
        } else {
            obj = entityManager.merge(obj);
        }
        return obj;
    }

    @Override
    public T findById(Class<T> tClass, ID id) {
        try {
            return entityManager.find(tClass, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    @Override
    public boolean deleteById(Class<T> tClass, ID id) {
        try {
            T obj = findById(tClass, id);
            if (obj == null)
                return false;
            return delete(obj);
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean delete(T obj) {
        try {
            if (entityManager.contains(obj)) {
                entityManager.remove(obj);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
