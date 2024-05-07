package com.project.db.repositpry;

import com.project.db.entity.Synset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SynsetRepository extends JpaRepository<Synset, String> {
}
