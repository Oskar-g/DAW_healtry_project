package com.oscar.healtry.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oscar.healtry.model.Dieta;

public interface DietaRepository extends JpaRepository<Dieta, Long> {

	List<Dieta> findByPlanNutricionistaId(Long idNutricionista);

	@Query("""
			    SELECT d
			    FROM Dieta d
			    JOIN d.dietaClientes dc
			    WHERE dc.cliente.id = :idCliente
			      AND :fecha BETWEEN d.fechaInicio AND d.fechaFin
			""")
	Optional<Dieta> findActivaPorCliente(@Param("idCliente") Long idCliente, @Param("fecha") LocalDate fecha);

}
