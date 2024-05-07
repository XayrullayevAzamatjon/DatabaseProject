package com.project.db.entity;

import com.project.db.utils.Part_of_speech;
import com.project.db.utils.Statuus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "New_word")
public class NewWord {
    @Id
    private String Word_Id;
    @ManyToOne
    @JsonIgnore
    private User user;

    @Column(name = "written_form")
    private String Written_form;

    private String Definition;

    @Column(name = "part_of_speech")
    private com.project.db.utils.Part_of_speech Part_of_speech;

    private Statuus Status;


    @Column(name = "created_date")
    private LocalDateTime created_date;

    @Column(name = "confirmed_date")
    private LocalDateTime confirmed_date;

    public String getWord_Id() {
        return Word_Id;
    }

    public void setWord_Id(String word_Id) {
        Word_Id = word_Id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getWritten_form() {
        return Written_form;
    }

    public String getDefinition() {
        return Definition;
    }

    public void setDefinition(String definition) {
        Definition = definition;
    }

    public void setWritten_form(String written_form) {
        Written_form = written_form;
    }

    public Part_of_speech getPart_of_speech() {
        return Part_of_speech;
    }

    public void setPart_of_speech(Part_of_speech part_of_speech) {
        Part_of_speech = part_of_speech;
    }

    public Statuus getStatus() {
        return Status;
    }

    public void setStatus(Statuus status) {
        Status = status;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public LocalDateTime getConfirmed_date() {
        return confirmed_date;
    }

    public void setConfirmed_date(LocalDateTime confirmed_date) {
        this.confirmed_date = confirmed_date;
    }

}
