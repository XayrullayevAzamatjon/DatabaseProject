package com.project.db.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Sense")
public class Sense {

    @Id
    @Column(name = "Sense_id")
    private String senseId;

    @ManyToOne
    @JoinColumn(name = "Entry_id", referencedColumnName = "Entry_id")
    private Entry entry;

    @ManyToOne
    @JoinColumn(name = "Synset_id", referencedColumnName = "Synset_id")
    private Synset synset;

    public String getSenseId() {
        return senseId;
    }

    public void setSenseId(String senseId) {
        this.senseId = senseId;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public Synset getSynset() {
        return synset;
    }

    public void setSynset(Synset synset) {
        this.synset = synset;
    }
}

