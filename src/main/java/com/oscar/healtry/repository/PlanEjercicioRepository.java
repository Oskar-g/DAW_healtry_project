package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.PlanEjercicio;

@Repository
public interface PlanEjercicioRepository extends JpaRepository<PlanEjercicio, Integer> {
	List<PlanEjercicio> findByClienteIdCliente(Integer idCliente);
}
