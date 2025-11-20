package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscar.healtry.model.PlanSemanal;

public interface PlanSemanalRepository extends JpaRepository<PlanSemanal, Long> {

	List<PlanSemanal> findByNutricionistaId(Long nutricionistaId);}
