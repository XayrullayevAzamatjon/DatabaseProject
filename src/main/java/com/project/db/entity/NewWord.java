package com.project.db.entity;

import com.project.db.utils.PartOfSpeech;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import  com.project.db.utils.Status;
import java.time.LocalDateTime;

@Entity
@Table(name = "New_word")
public class NewWord {
    @Id
    private String wordId;
    @ManyToOne
    @JsonIgnore
    private User user;

    @Column(name = "written_form")
    private String writtenForm;

    private String definition;

    @Column(name = "part_of_speech")
    @Enumerated(EnumType.STRING)
    private PartOfSpeech partOfSpeech;

    @Enumerated(EnumType.STRING)
    @Column(name = "word_status")
    private Status status;


    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "confirmed_date")
    private LocalDateTime confirmedDate;

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getWrittenForm() {
        return writtenForm;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setWrittenForm(String writtenForm) {
        this.writtenForm = writtenForm;
    }

    public PartOfSpeech getPart_of_speech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime created_date) {
        this.createdDate = created_date;
    }

    public LocalDateTime getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(LocalDateTime confirmed_date) {
        this.confirmedDate = confirmed_date;
    }

}
