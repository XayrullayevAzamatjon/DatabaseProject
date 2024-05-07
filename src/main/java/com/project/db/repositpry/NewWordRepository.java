package com.project.db.repositpry;

import com.project.db.entity.NewWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewWordRepository extends JpaRepository<NewWord, String> {
}
