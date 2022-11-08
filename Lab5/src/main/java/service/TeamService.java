/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import models.Team;
import repositories.TeamRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class TeamService {
    
    @EJB
    private TeamRepository teamRepo;

    public List<Team> getTeams() {
        return teamRepo.findAll();
    }

    public void addTeam(Team team) {
        teamRepo.persist(team);
    }

    public void removeTeam(Long id) {
        Team team = teamRepo.findById(id);
        teamRepo.remove(team);
    }

    public Team getTeam(Long id) {
        return teamRepo.findById(id);
    }
}
