package controller;

import dao.CityService;
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

    private int id;

    @Inject
    private CityService cityService;

    public List<City> getCities() {
        List myList = this.cityService.getCities();
        System.out.println("SIZEEEEEEEEEEEEEEEEEEEEEEE = " + myList.size());
        return myList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
