package com.lab3.dao.implementation;

import com.lab3.dao.interfaces.CityDAO;
import com.lab3.models.City;
import com.lab3.utilities.DatabaseRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImplementation implements CityDAO {
    private final DatabaseRepository databaseRepository;

    public CityDAOImplementation() {
        this.databaseRepository = DatabaseRepository.get();
    }

    @Override
    public List<City> getCities() {
        List<City> cities = new ArrayList<>();
        ResultSet resultSet = databaseRepository.run("SELECT * FROM TEAM");

        while (true) {
            try {
                if (!resultSet.next()) {
                    break;
                }
                cities.add(new City(
                        resultSet.getInt(0),
                        resultSet.getString(1)
                ));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cities;
    }

    @Override
    public City getCityById(int id) {
        String query = "SELECT * FROM TEAM WHERE id=" + "'" + id + "'" + ";";
        ResultSet resultSet = databaseRepository.run(query);
        try {
            if (resultSet.first()) {
                return new City(resultSet.getInt(0), resultSet.getString(1));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCity(City city) {
        String query = "INSERT INTO CITY VALUES (" + "'" + city.getId() + "'," + "'" + city.getName() + "'" + ");";
        databaseRepository.run(query);
    }

    @Override
    public void removeCity(int id) {
        String query = "DELETE FROM CITY WHERE ID = " + id;
        databaseRepository.run(query);
    }
}
