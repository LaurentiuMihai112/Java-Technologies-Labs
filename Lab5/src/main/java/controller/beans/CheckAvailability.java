/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import models.Match;
import repositories.MatchRepository;

/**
 *
 * @author Laurentiu
 */
@Stateless
public class CheckAvailability implements Serializable {

    @EJB
    private MatchRepository matchRepository;

    public boolean isAvailable(Integer weekDay, Integer weekNumber) {
        List<Match> matches = matchRepository.getResultList("SELECT m FROM Match m WHERE m.weekNumber = ?", weekNumber);
        if (matches.isEmpty()) {
            return true;
        }

        Long numberOfMatches = matches.stream().filter(match -> match.getWeekDay().equals(weekDay)).count();
        return numberOfMatches == 0;
    }
}
