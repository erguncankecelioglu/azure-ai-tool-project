package com.azure.bau.aiproject.repository;

import com.azure.bau.aiproject.model.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
}
