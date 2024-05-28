package com.project.db.repository;

import com.project.db.entity.Entry;
import com.project.db.utils.PartOfSpeech;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, String> {

    List<Entry> findByWrittenFormContainingIgnoreCase(String query, Pageable pageable);

    @Query("SELECT MAX(CAST(SUBSTRING(e.entryId, 2) AS integer)) FROM Entry e")
    Integer findMaxNumericId();

    Boolean existsByWrittenFormAndPartOfSpeech(String writtenForm, PartOfSpeech partOfSpeech);
}
