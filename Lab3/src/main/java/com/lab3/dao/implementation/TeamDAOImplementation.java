package com.lab3.dao.implementation;

import com.lab3.dao.interfaces.TeamDAO;
import com.lab3.models.Team;
import com.lab3.utilities.DatabaseRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDAOImplementation implements TeamDAO {
    private final DatabaseRepository databaseRepository;

    public TeamDAOImplementation() {
        this.databaseRepository = DatabaseRepository.get();
    }

    @Override
    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();
        ResultSet resultSet = databaseRepository.run("SELECT * FROM TEAM");

        while (true) {
            try {
                if (!resultSet.next()) {
                    break;
                }
                teams.add(new Team(
                        resultSet.getInt(0),
                        resultSet.getString(1),
                        resultSet.getDate(2),
                        resultSet.getInt(3)
                ));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return teams;
    }

    @Override
    public Team getTeamById(int id) {
        return null;
    }

    @Override
    public void addTeam(Team team) {
        String query = "INSERT INTO TEAM VALUES (" + "'" + team.getId() + "'," + "'" + team.getName() + "'" + "'" + team.getFoundingDate() + "'" + "'" + team.getCityId() + "'" + ");";

        databaseRepository.run(query);
    }

    @Override
    public void removeTeam(int id) {
        String query = "DELETE FROM TEAM WHERE ID = " + id;
        databaseRepository.run(query);
    }
}
