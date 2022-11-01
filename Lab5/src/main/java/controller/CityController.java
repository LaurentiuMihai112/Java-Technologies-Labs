package controller;

import service.CityService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import models.City;

/**
 *
 * @author Laurentiu
 */
@ManagedBean(name = "cityBean", eager = false)
@RequestScoped
public class CityController {

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
