package com.lab3.dao.interfaces;

import com.lab3.models.Team;

import java.util.List;

public interface TeamDAO {

    List<Team> getTeams();

    Team getTeamById(int id);

    void addTeam(Team team);

    void removeTeam(int id);
}
