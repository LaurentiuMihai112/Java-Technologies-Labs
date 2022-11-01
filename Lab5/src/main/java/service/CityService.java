/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import models.City;

@Stateless
public class CityService {

    @PersistenceContext(unitName = "jpaPU")
    EntityManager em;

    public List<City> getCities() {
        return em.createNamedQuery("City.findAll", City.class).getResultList();
    }

    public void addCity(City city) {
        em.persist(city);
    }

    public void removeCity(Long id) {
        City city = em.find(City.class, id);
        em.remove(city);
    }

}
