package com.project.db.entity;
import jakarta.persistence.*;


@Entity
@Table(name = "Relations")
public class Relations {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @MapsId("synsetId")
    @JoinColumn(name = "Synset_id", referencedColumnName = "Synset_id")
    private Synset synset;

    @ManyToOne
    @JoinColumn(name = "target_synset_id", referencedColumnName = "Synset_id")
    private Synset targetSynset;

    @Column(name = "relType")
    private String relType;

}