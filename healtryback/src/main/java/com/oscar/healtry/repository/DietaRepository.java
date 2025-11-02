package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Dieta;

@Repository
public interface DietaRepository extends ListCrudRepository<Dieta, Integer> {
	List<Dieta> findByClienteId(Integer idCliente);

	List<Dieta> findByNutricionistaId(Integer idNutricionista);
}
