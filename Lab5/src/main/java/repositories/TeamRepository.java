/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import javax.ejb.Stateless;
import models.Team;
import repositories.template.AbstractRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * @author Laurentiu
 */
@Stateless
public class TeamRepository extends AbstractRepository<Team, Long>{
    @PersistenceContext(name = "jpaPU")
    EntityManager em;

    public TeamRepository() {
        super(Team.class);
    }
}
