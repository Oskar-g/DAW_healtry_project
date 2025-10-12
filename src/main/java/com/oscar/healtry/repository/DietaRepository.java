package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Dieta;

@Repository
public interface DietaRepository extends JpaRepository<Dieta, Integer> {
	List<Dieta> findByClienteIdCliente(Integer idCliente);

	List<Dieta> findByNutricionistaIdNutricionista(Integer idNutricionista);
}
