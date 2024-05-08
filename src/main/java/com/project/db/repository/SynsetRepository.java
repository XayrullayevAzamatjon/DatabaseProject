package com.project.db.repository;

import com.project.db.entity.Synset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SynsetRepository extends JpaRepository<Synset, String> {
}
