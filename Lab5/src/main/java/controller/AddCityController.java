/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CityService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import models.City;

/**
 *
 * @author Laurentiu
 */
@ManagedBean(name = "AddCityController", eager = false)
@RequestScoped
public class AddCityController {

    private String name;

    @Inject
    private CityService cityService;

    public void addCity() {
        try {
            City city = new City(name);
            cityService.addCity(city);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
