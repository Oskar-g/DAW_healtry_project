package com.oscar.healtry.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Ingrediente;

@Repository
public interface IngredienteRepository extends ListCrudRepository<Ingrediente, Integer> {
}
