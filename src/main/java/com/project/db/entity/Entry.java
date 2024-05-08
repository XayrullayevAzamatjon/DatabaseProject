package com.project.db.entity;

import com.project.db.utils.PartOfSpeech;
import jakarta.persistence.*;

@Entity
@Table(name = "Entry")
public class Entry {

    @Id
    @Column(name = "Entry_id")
    private String entryId;

    @Column(name = "WrittenForm")
    private String writtenForm;

    @Column(name = "PartOfSpeech")
    @Enumerated(EnumType.STRING)
    private PartOfSpeech partOfSpeech;

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getWrittenForm() {
        return writtenForm;
    }

    public void setWrittenForm(String writtenForm) {
        this.writtenForm = writtenForm;
    }

    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }
}