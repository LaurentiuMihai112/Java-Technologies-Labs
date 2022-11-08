/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import javax.ejb.Stateless;
import models.Player;
import repositories.template.AbstractRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Laurentiu
 */
@Stateless
public class PlayerRepository extends AbstractRepository<Player, Long> {
    @PersistenceContext(name = "jpaPU")
    EntityManager em;

    public PlayerRepository() {
        super(Player.class);
    }

    public EntityManager getEm() {
        return em;
    }
}
