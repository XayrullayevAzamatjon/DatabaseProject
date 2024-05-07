package com.project.db.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Members")
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Entry_id", referencedColumnName = "Entry_id")
    private Entry entry;

    @ManyToOne
    @JoinColumn(name = "Synset_id", referencedColumnName = "Synset_id")
    private Synset synset;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
