/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.beans;

/**
 *
 * @author Laurentiu
 */
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import models.Match;
import models.Team;
import repositories.MatchRepository;

@Stateful
public class AssignmentBean implements Serializable {

    @EJB
    private CheckAvailability checkAvailabilty;

    @EJB
    private MatchRepository matchRepository;

    @EJB
    private AssignmentsMap assignmentsMap;

    private List<Match> assignedMatches;

    @PostConstruct
    public void init() {
        assignedMatches = new ArrayList<>();
    }

    public void assignMatch(Team team1, Team team2, Integer weekNumber, Integer weekDay) {
        assignedMatches.add(new Match(team1, team2, weekNumber, weekDay));
    }

    public boolean scheduleMatches() {
        for (Match match : assignedMatches) {
            if (!checkAvailabilty.isAvailable(match.getWeekDay(), match.getWeekNumber())) {
                return false;
            }
        }

        assignedMatches.forEach(match -> matchRepository.persist(match));
        assignmentsMap.updateMap();
        return true;
    }

    public List<Match> getAssignments() {
        return assignedMatches;
    }

    public void clear() {
        assignedMatches = new ArrayList<>();
    }

    public List<Match> getAssignedMatches() {
        return assignedMatches;
    }

    public void setAssignedMatches(List<Match> assignedMatches) {
        this.assignedMatches = assignedMatches;
    }
    
}
