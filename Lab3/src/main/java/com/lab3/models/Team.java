package com.lab3.models;

import java.util.Date;

public class Team {
    private int id;
    private String name;
    private Date foundingDate;
    private final int cityId;

    public Team(int id, String name, Date foundingDate, int cityId) {
        this.id = id;
        this.name = name;
        this.foundingDate = foundingDate;
        this.cityId = cityId;
    }

    public Team(String name, Date foundingDate, int cityId) {
        this.name = name;
        this.foundingDate = foundingDate;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    public int getCityId() {
        return cityId;
    }
}
