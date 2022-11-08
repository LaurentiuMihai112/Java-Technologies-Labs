/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import models.Player;
import repositories.PlayerRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;


@Stateless
public class PlayerService implements Serializable {

    @EJB
    private PlayerRepository playerRepo;

    public List<Player> getPlayers() {
        return playerRepo.findAll();
    }

    public void addPlayer(Player player) {
        playerRepo.persist(player);
    }

    public void removePlayer(Long id) {
        Player player = playerRepo.findById(id);
        playerRepo.remove(player);
    }

    public List<Player> searchByCriteria(boolean subjectCriteria, String subject, boolean timeframeCriteria, String age) {
        CriteriaBuilder builder = playerRepo.getEm().getCriteriaBuilder();
        CriteriaQuery<Player> query = builder.createQuery(Player.class);


        Root<Player> root = query.from(Player.class);

        if (subjectCriteria) {
            query.select(root).where(builder.equal(root.get("position"), subject));
        }
        if (timeframeCriteria) {
            query.select(root).where(builder.equal(root.get("age"), age));
        }

        TypedQuery<Player> tquery = playerRepo.getEm().createQuery(query);

        return tquery.getResultList();

    }

    public Player getPlayer(Long id) {
        return playerRepo.findById(id);
    }

}
