/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.Match;
import repositories.template.AbstractRepository;

/**
 *
 * @author Laurentiu
 */
@Stateless
public class MatchRepository extends AbstractRepository<Match, Long> {

    @PersistenceContext(name = "jpaPU")
    EntityManager em;

    public MatchRepository() {
        super(Match.class);
    }

    public List<Match> getAllByWeekNumber(Integer weekNumber) {
        return getResultList("SELECT m FROM Match m WHERE m.weekNumber = ?", weekNumber);
    }
}
