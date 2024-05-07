package com.project.db.repositpry;

import com.project.db.entity.NewWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
public interface NewWordRepository extends JpaRepository<NewWord, String> {
    @Query("SELECT nw FROM NewWord nw WHERE nw.status = 'REQUESTED'")
    List<NewWord> findAllRequestedNewWords();
}
