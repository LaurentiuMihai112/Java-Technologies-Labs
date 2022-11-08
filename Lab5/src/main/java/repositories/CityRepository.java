/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import javax.ejb.Stateless;
import models.City;
import repositories.template.AbstractRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Laurentiu
 */
@Stateless
public class CityRepository extends AbstractRepository<City, Long>{
    @PersistenceContext(name = "jpaPU")
    EntityManager em;

    public CityRepository() {
        super(City.class);
    }
}
