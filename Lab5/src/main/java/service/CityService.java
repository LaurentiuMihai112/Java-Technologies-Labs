/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.Serializable;
import models.City;
import repositories.CityRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CityService implements Serializable{
    
    @EJB
    private CityRepository cityRepo;

    public List<City> getCities() {
        return cityRepo.findAll();
    }

    public void addCity(City city) {
        cityRepo.persist(city);
    }

    public void removeCity(Long id) {
        City city = cityRepo.findById(id);
        cityRepo.remove(city);
    }

    public City getCity(Long id) {
        return cityRepo.findById(id);
    }

}
