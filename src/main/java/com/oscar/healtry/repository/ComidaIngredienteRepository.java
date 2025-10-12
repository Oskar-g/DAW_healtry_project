package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.ComidaIngrediente;
import com.oscar.healtry.model.ComidaIngredienteId;

@Repository
public interface ComidaIngredienteRepository extends JpaRepository<ComidaIngrediente, ComidaIngredienteId> {
	List<ComidaIngrediente> findByComidaIdComida(Integer idComida);
}
