package com.oscar.healtry.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Nutricionista;

@Repository
public interface NutricionistaRepository extends ListCrudRepository<Nutricionista, Integer> {
}
