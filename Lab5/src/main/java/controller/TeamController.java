package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import service.TeamService;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import models.Team;
import service.CityService;

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
        cityName = this.cityService.getCity(cityId);
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
