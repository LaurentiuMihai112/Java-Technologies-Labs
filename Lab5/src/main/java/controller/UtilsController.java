/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.github.javafaker.Faker;
import java.io.Serializable;
import models.City;
import models.Player;
import models.Team;
import service.CityService;
import service.PlayerService;
import service.TeamService;


import javax.inject.Inject;
import java.util.*;

import static java.lang.Math.min;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author Laurentiu
 */
@Named("Utils")
@RequestScoped
public class UtilsController implements Serializable{

    private final List<String> positions = new ArrayList<>(Arrays.asList("Goal Keeper", "Quarterback", "Quarterback", "Midfielder", "Midfielder", "Midfielder", "Forward", "Forward", "Forward", "Forward"));
    @Inject
    private PlayerService playerService;
    @Inject
    private CityService cityService;
    @Inject
    private TeamService teamService;
    private int playerCount = 10;
    private int teamCount = 10;

    public void generateTeamsAndCities() {
        Faker faker = new Faker();
        for (int index = 0; index < teamCount; index++) {
            String cityName = faker.address().cityName();
            City city = new City(cityName);
            cityService.addCity(city);
        }
        List<City> cities = cityService.getCities();
        for (int i = 0; i < min(cities.size(), 10); i++) {
            String teamName = "FC " + cities.get(i).getName();
            Date date = faker.date().between(new Date(1990, Calendar.JANUARY, 1), new Date(2010, Calendar.JANUARY, 1));
            Team team = new Team(teamName, date, cities.get(i).getId());
            teamService.addTeam(team);

        }
        List<Team> teams = teamService.getTeams();
        for (int i = 0; i < min(teams.size(), 10); i++) {
            for (int p = 0; p < 25; p++) {
                String playerName = faker.name().fullName();
                int age = faker.number().numberBetween(20, 40);
                int positionIndex = faker.number().numberBetween(0, 9);
                Player player = new Player(playerName, age, positions.get(positionIndex), teams.get(i).getId());
                playerService.addPlayer(player);
            }
        }

    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(int teamCount) {
        this.teamCount = teamCount;
    }
}
