package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Comida;

@Repository
public interface ComidaRepository extends ListCrudRepository<Comida, Integer> {
	List<Comida> findByDietaId(Integer idDieta);
}
