package com.javatech.lab8.entity;


import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "Select u from User u"),
        @NamedQuery(name = "User.findByName", query = "Select u from User u where u.name = ?1"),
        @NamedQuery(name = "User.countByName", query = "Select count(u) from User u where u.name = ?1"),
        @NamedQuery(name = "User.countById", query = "Select count(u) from User u where u.id = ?1")
})
@SessionScoped
public class User implements ApplicationEntity, Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(
            name = "user_seq_id",
            sequenceName = "user_seq_id",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq_id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    @Size(min = 5, max = 20)
    private String name;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "role", nullable = false)
    private String role;

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public User(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
