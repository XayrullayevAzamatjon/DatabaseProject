package com.project.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.db.utils.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "_users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private Long score;

    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime joinedDate;
    @ManyToMany
    @JoinTable(
            name = "user_word",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "sense_id")
    )
    Set<Sense> userSense = new HashSet<>();

    public Set<Sense> getUserSense() {
        return userSense;
    }

    public void setUserSense(Set<Sense> userSense) {
        this.userSense = userSense;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<NewWord> newWordList = new ArrayList<>();



    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDateTime joinedDate) {
        this.joinedDate = joinedDate;
    }

    @JsonIgnore
    public List<NewWord> getNewWordList() {
        return newWordList;
    }

    public void setNewWordList(List<NewWord> newWordList) {
        this.newWordList = newWordList;
    }
}
