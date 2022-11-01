package controller;

import models.City;
import service.TeamService;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import models.Team;

/**
 *
 * @author Laurentiu
 */
@ManagedBean(name = "teamBean", eager = false)
@RequestScoped
public class TeamController {

    private Long id;
    private String name;
    private Date foundingDate;
    private long cityId;
    private String result;

    @Inject
    private TeamService teamService;

    public List<Team> getTeams() {
        return this.teamService.getTeams();
    }

    public void addTeam() {
        try {
            teamService.addTeam(new Team(name, foundingDate, cityId));
            this.result = "Team added succesully";
        } catch (Exception e) {
            this.result = "Team adding failed";
        }
    }

    public void removeTeamById() {
        try {
            teamService.removeTeam(id);
            this.result = "Team removed succesully";
        } catch (Exception e) {
            this.result = "Team removing failed";
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
