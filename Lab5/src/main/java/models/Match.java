/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Laurentiu
 */
@Entity
@Table(name = "matcht")
@NamedQueries({
    @NamedQuery(name = "Match.findAll", query = "select m from Match m")
})
public class Match implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "team_id1")
    private Team team1;

    @OneToOne
    @JoinColumn(name = "team_id2")
    private Team team2;

    private Integer weekNumber;

    private Integer weekDay;

    public Match() {
    }

    public Match(Team team1, Team team2, Integer weekNumber, Integer weekDay) {
        this.team1 = team1;
        this.team2 = team2;
        this.weekNumber = weekNumber;
        this.weekDay = weekDay;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.team1);
        hash = 83 * hash + Objects.hashCode(this.team2);
        hash = 83 * hash + Objects.hashCode(this.weekNumber);
        hash = 83 * hash + Objects.hashCode(this.weekDay);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Match other = (Match) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.team1, other.team1)) {
            return false;
        }
        if (!Objects.equals(this.team2, other.team2)) {
            return false;
        }
        if (!Objects.equals(this.weekNumber, other.weekNumber)) {
            return false;
        }
        return Objects.equals(this.weekDay, other.weekDay);
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", team1=" + team1 + ", team2=" + team2 + ", weekNumber=" + weekNumber + ", weekDay=" + weekDay + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
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

}
