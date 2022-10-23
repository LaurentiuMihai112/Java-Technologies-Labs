package com.lab3.dao.interfaces;

import com.lab3.models.City;

import java.util.List;

public interface CityDAO {

    List<City> getCities();

    City getCityById(int id);

    void addCity(City city);

    void removeCity(int id);
}
