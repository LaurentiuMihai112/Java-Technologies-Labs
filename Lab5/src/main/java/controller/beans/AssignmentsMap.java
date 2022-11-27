/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.beans;

import javax.ejb.EJB;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Startup;
import lombok.NoArgsConstructor;
import models.Match;
import repositories.MatchRepository;

/**
 *
 * @author Laurentiu
 */
@Singleton
@Startup
public class AssignmentsMap {
    @EJB
    private MatchRepository matchRepository;

    private List<Match> assignedMatches;

    @PostConstruct
    public void init() {
        updateMap();
    }

    public void updateMap() {
        assignedMatches = (List<Match>)matchRepository.findAll();
    }

    public List<Match> getMatches() {
        return assignedMatches;
    }
}
