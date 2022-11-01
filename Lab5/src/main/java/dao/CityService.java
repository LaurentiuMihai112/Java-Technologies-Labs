/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
        return em.createQuery("select c from City c", City.class).getResultList();
//        return em.createNamedQuery("City.findAll", City.class).getResultList();
    }

    public void addCity(City city) {
        try {
            em.persist(city);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
