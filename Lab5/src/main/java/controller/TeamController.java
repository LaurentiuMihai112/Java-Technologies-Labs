package controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import service.TeamService;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import models.Team;
import service.CityService;

/**
 *
 * @author Laurentiu
 */
@ManagedBean(name = "teamBean", eager = false)
@SessionScoped
//@Named("cityBean")
//@ViewScoped
public class TeamController implements Serializable{

    private Long id;
    private String name;
    private Date foundingDate;
    private Long cityId;
    private String cityName;
    private String result;

    @Inject
    private TeamService teamService;

    @Inject
    private CityService cityService;

    public List<Team> getTeams() {
        return this.teamService.getTeams();
    }

    public void addTeam() {
        try {
            System.out.println("*** DATE " + foundingDate);
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

    public Date getFoundingDate() {
        return this.foundingDate;
    }

    public String getStringDate(Date date) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String stringDate = simpleDateFormat.format(date);
        return stringDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName(Long cityId) {
        cityName = this.cityService.getCity(cityId).getName();
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTeamName(long teamId) {
        return teamService.getTeam(teamId).getName();

    }
}
