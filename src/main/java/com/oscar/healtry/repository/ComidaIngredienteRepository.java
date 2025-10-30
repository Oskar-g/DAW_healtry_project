package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.ComidaIngrediente;
import com.oscar.healtry.model.ComidaIngredienteId;

@Repository
public interface ComidaIngredienteRepository extends ListCrudRepository<ComidaIngrediente, ComidaIngredienteId> {
	List<ComidaIngrediente> findByComidaIdComida(Integer idComida);
}
