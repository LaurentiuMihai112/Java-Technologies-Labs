/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.Serializable;
import models.Match;
import repositories.MatchRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import models.Match;
import repositories.MatchRepository;

@Stateless
public class MatchService implements Serializable{
    
    @EJB
    private MatchRepository matchRepo;

    public List<Match> getMatches() {
        return matchRepo.findAll();
    }

    public void addMatch(Match match) {
        matchRepo.persist(match);
    }

    public void removeMatch(Long id) {
        Match match = matchRepo.findById(id);
        matchRepo.remove(match);
    }

    public Match getMatch(Long id) {
        return matchRepo.findById(id);
    }

}
