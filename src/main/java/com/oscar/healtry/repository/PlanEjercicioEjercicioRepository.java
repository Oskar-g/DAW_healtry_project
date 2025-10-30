package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.PlanEjercicioEjercicio;
import com.oscar.healtry.model.PlanEjercicioEjercicioId;

@Repository
public interface PlanEjercicioEjercicioRepository
		extends ListCrudRepository<PlanEjercicioEjercicio, PlanEjercicioEjercicioId> {
	List<PlanEjercicioEjercicio> findByPlanIdPlan(Integer idPlan);
}
