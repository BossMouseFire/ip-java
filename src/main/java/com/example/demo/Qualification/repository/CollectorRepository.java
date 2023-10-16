package com.example.demo.Qualification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Qualification.model.Collector;

public interface CollectorRepository extends JpaRepository<Collector, Long> {
}
