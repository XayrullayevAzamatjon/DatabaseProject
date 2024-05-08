package com.project.db.entity;
import jakarta.persistence.*;

import java.util.Set;

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

    @ManyToMany(mappedBy = "userSense")
    private Set<User> userSet;

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

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

