package com.project.db.repository;

import com.project.db.entity.Sense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SenseRepository extends JpaRepository<Sense, String> {
}
