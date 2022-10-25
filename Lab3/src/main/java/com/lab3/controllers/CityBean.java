package com.lab3.controllers;


import com.lab3.dao.implementation.CityDAOImplementation;
import com.lab3.dao.interfaces.CityDAO;
import com.lab3.models.City;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "CityBean", eager = false)
@RequestScoped
public class CityBean {

    private CityDAO cityDAO;
    private int id;

    public CityBean() {
        cityDAO = new CityDAOImplementation();
    }

    public List<City> getCities() {
        return cityDAO.getCities();
    }

    public void removeCity() {
        cityDAO.removeCity(this.id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}