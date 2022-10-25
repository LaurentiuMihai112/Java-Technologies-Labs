package com.lab3.controllers;

import com.lab3.dao.implementation.TeamDAOImplementation;
import com.lab3.dao.interfaces.TeamDAO;
import com.lab3.models.Team;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Date;

@ManagedBean(name = "TeamInputBean ", eager = false)
@RequestScoped
public class TeamInputBean {
    private String name;
    private Date foundingDate;
    private int cityId;

    private TeamDAO teamDao;

    public TeamInputBean() {
        teamDao = new TeamDAOImplementation();
    }

    public void submit() {
        teamDao.addTeam(new Team(name, foundingDate, cityId));
    }

    public String getName() {
        return name;
    }

    public void setName(String fullName) {
        this.name = fullName;
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

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
