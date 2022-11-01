/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import models.Team;

@Stateless
public class TeamService {

    @PersistenceContext(unitName = "jpaPU")
    EntityManager em;

    public List<Team> getTeams() {
        return em.createNamedQuery("Team.findAll", Team.class).getResultList();
    }

    public void addTeam(Team team) {
        em.persist(team);
    }

    public void removeTeam(Long id) {
        Team team = em.find(Team.class, id);
        em.remove(team);
    }

}
