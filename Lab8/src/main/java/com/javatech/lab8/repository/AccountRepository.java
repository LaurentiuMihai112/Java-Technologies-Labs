package com.javatech.lab8.repository;

import com.javatech.lab8.entity.User;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class AccountRepository extends GenericRepository<User, Long> {


    public User getUserByName(String name) {
        Object result = entityManager.createNamedQuery("User.findByName")
                .setParameter(1, name)
                .getSingleResult();
        if (result == null) {
            return null;
        }

        return (User) result;

    }

    public boolean checkIfExists(String name) {
        Long count = (Long) entityManager.createNamedQuery("User.countByName")
                .setParameter(1, name)
                .getSingleResult();
        return !count.equals(0L);
    }

    public boolean checkIfExistsById(Long id) {
        Long count = (Long) entityManager.createNamedQuery("User.countById")
                .setParameter(1, id)
                .getSingleResult();
        return !count.equals(0L);
    }

    public List<User> getAll() {
        try {
            return (List<User>) entityManager
                    .createQuery("Select t from User t")
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }


}


