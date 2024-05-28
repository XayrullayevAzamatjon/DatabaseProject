package com.project.db.repository;

import com.project.db.entity.NewWord;
import com.project.db.utils.PartOfSpeech;
import com.project.db.utils.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewWordRepository extends JpaRepository<NewWord, String> {

    @Query(""" 
            SELECT n from NewWord n where n.status='REQUESTED'
           """
    )
    List<NewWord> findAllRequestedWords();
    List<NewWord> findAllByStatus(Status status);

    Boolean existsByWrittenFormAndPartOfSpeech(String writtenForm, PartOfSpeech partOfSpeech);
    NewWord findByWrittenForm(String writtenForm);

}
