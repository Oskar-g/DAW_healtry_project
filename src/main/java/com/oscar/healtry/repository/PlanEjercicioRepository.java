package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.PlanEjercicio;

@Repository
public interface PlanEjercicioRepository extends ListCrudRepository<PlanEjercicio, Integer> {
	List<PlanEjercicio> findByClienteIdCliente(Integer idCliente);
}
