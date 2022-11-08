package controller;

import java.io.Serializable;
import service.CityService;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import models.City;

/**
 *
 * @author Laurentiu
 */
@Named("cityBean")
@RequestScoped
public class CityController implements Serializable {

    private Long id;
    private String name;
    private String result;

    @Inject
    private CityService cityService;

    public List<City> getCities() {
        return this.cityService.getCities();
    }

    public void addCity() {
        try {
            cityService.addCity(new City(name));
            this.result = "City added succesully";
        } catch (Exception e) {
            this.result = "City adding failed";
        }
    }

    public void removeCityById() {
        try {
            cityService.removeCity(id);
            this.result = "City removed succesully";
        } catch (Exception e) {
            this.result = "City removing failed";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
