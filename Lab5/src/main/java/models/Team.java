/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Laurentiu
 */
@Entity
@Table(name = "teamv2")
@NamedQueries({
    @NamedQuery(name = "Team.findAll", query = "select t from Team t")
})
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private Date foundingDate;
    @Column(name = "city_id")
    private long cityId;

    public Team(String name, Date foundingDate, Long cityId) {
        this.name = name;
        this.foundingDate = foundingDate;
        this.cityId = cityId;
    }

    public Team(Long id, String name, Date foundingDate, long cityId) {
        this.id = id;
        this.name = name;
        this.foundingDate = foundingDate;
        this.cityId = cityId;
    }

    public Team() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

}
