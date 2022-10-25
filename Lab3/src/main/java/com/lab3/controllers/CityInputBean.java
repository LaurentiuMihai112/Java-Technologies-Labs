package com.lab3.controllers;

import com.lab3.dao.implementation.CityDAOImplementation;
import com.lab3.dao.interfaces.CityDAO;
import com.lab3.models.City;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "CityInputBean", eager = false)
@RequestScoped
public class CityInputBean {
    private String name;

    CityDAO cityDao;

    public CityInputBean() {
        cityDao = new CityDAOImplementation();
    }

    public void submit() {
        cityDao.addCity(new City(name));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}