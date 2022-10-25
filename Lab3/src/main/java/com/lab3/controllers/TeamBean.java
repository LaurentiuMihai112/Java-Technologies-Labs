package com.lab3.controllers;

import com.lab3.dao.implementation.TeamDAOImplementation;
import com.lab3.dao.interfaces.TeamDAO;
import com.lab3.models.Team;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "TeamBean ", eager = false)
@SessionScoped
public class TeamBean implements Serializable {

    private int id;
    private TeamDAO teamDAO;

    public TeamBean() {
        teamDAO = new TeamDAOImplementation();
    }

    public List<Team> getTeams() {
        return teamDAO.getTeams();
    }

    public Team getTeamById() {
        return teamDAO.getTeamById(id);
    }

    public void removeTeamById() {
        teamDAO.removeTeam(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
