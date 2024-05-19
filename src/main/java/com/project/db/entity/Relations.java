package com.project.db.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Relations")
public class Relations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Synset_id", nullable = false)
    private Synset synset;

    @ManyToOne
    @JoinColumn(name = "target_synset_id", nullable = false)
    private Synset targetSynset;

    @Column(name = "relType")
    private String relType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Synset getSynset() {
        return synset;
    }

    public void setSynset(Synset synset) {
        this.synset = synset;
    }

    public Synset getTargetSynset() {
        return targetSynset;
    }

    public void setTargetSynset(Synset targetSynset) {
        this.targetSynset = targetSynset;
    }

    public String getRelType() {
        return relType;
    }

    public void setRelType(String relType) {
        this.relType = relType;
    }
}
