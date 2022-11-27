/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 * @author Laurentiu
 */

import controller.beans.AssignmentBean;
import controller.beans.AssignmentsMap;
import models.Match;
import models.Team;
import repositories.CityRepository;
import repositories.TeamRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named("matchBean")
@RequestScoped
public class MatchController implements Serializable {
    @EJB
    private AssignmentBean assignmentsBean;

    @EJB
    private AssignmentsMap assignmentsMap;

    @EJB
    private TeamRepository teamRepository;

    @EJB
    private CityRepository cityRepository;

    private Long firstTeamId;
    private Long secondTeamId;
    private Integer weekNumber;
    private Integer weekDay;

    private boolean commitTransaction = false;

    public List<Match> getMatchesMap() {
        return assignmentsMap.getMatches();
    }

    public List<Match> getMatchAssignments() {
        return assignmentsBean.getAssignments();
    }


    public Long getFirstTeamId() {
        return firstTeamId;
    }

    public void setFirstTeamId(Long firstTeamId) {
        this.firstTeamId = firstTeamId;
    }

    public Long getSecondTeamId() {
        return secondTeamId;
    }

    public void setSecondTeamId(Long secondTeamId) {
        this.secondTeamId = secondTeamId;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public boolean isDoCommit() {
        return commitTransaction;
    }

    public void setDoCommit(boolean doCommit) {
        this.commitTransaction = doCommit;
    }

    public AssignmentBean getAssignmentsBean() {
        return assignmentsBean;
    }

    public void setAssignmentsBean(AssignmentBean assignmentsBean) {
        this.assignmentsBean = assignmentsBean;
    }

    public AssignmentsMap getAssignmentsMap() {
        return assignmentsMap;
    }

    public void setAssignmentsMap(AssignmentsMap assignmentsMap) {
        this.assignmentsMap = assignmentsMap;
    }

    public TeamRepository getTeamRepository() {
        return teamRepository;
    }

    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public CityRepository getCityRepository() {
        return cityRepository;
    }

    public void setCityRepository(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void scheduleMatch() {
        Team team1 = teamRepository.findById(firstTeamId);
        Team team2 = teamRepository.findById(secondTeamId);


        assignmentsBean.assignMatch(
                team1, team2,
                weekNumber,
                weekDay);

    }

    public void commitScheduledMatches() {
        if (commitTransaction) {
            assignmentsBean.scheduleMatches();
            assignmentsBean.clear();
            commitTransaction = false;
        }
    }
}

