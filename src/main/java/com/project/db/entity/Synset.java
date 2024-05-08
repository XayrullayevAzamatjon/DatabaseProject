package com.project.db.entity;

import com.project.db.utils.PartOfSpeech;
import jakarta.persistence.*;

@Entity
@Table(name = "Synset")
public class Synset {

    @Id
    @Column(name = "Synset_id")
    private String synsetId;

    @Column(name = "PartOfSpeech")
    @Enumerated(EnumType.STRING)
    private PartOfSpeech partOfSpeech;


    @Column(name = "Definition")
    private String definition;


    public String getSynsetId() {
        return synsetId;
    }

    public void setSynsetId(String synsetId) {
        this.synsetId = synsetId;
    }

    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}