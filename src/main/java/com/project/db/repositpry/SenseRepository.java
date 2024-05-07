package com.project.db.repositpry;

import com.project.db.entity.Sense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenseRepository extends JpaRepository<Sense, String> {
}
