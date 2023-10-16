package com.example.demo.Qualification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Qualification.model.Qualification;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {
}
