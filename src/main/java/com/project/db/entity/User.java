package com.project.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.db.utils.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "_users")
public class User {
    @Id
    String Id;
    @Column(name = "first_name")
    String FirstName;
    @Column(name = "last_name")
    String LastName;
    @Column(name = "email")
    String Email;
    @Column(name = "password")
    String Password;
    @Column(name = "score")
    Double Score;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;
    @Column(name = "joined_date")
    LocalDateTime joined_date;

    @ManyToMany
    @JoinTable(
            name = "user_word",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sense_id")
    )
    Set<Sense> userSense;

    public Set<Sense> getUserSense() {
        return userSense;
    }

    public void setUserSense(Set<Sense> userSense) {
        this.userSense = userSense;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<NewWord> newWordList = new ArrayList<>();

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Double getScore() {
        return Score;
    }

    public void setScore(Double score) {
        Score = score;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getJoined_date() {
        return joined_date;
    }

    public void setJoined_date(LocalDateTime joined_date) {
        this.joined_date = joined_date;
    }

    @JsonIgnore
    public List<NewWord> getNewWordList() {
        return newWordList;
    }

    public void setNewWordList(List<NewWord> newWordList) {
        this.newWordList = newWordList;
    }
}
