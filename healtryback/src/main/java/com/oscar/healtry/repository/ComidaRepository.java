package com.oscar.healtry.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Comida;

@Repository
public interface ComidaRepository extends ListCrudRepository<Comida, Integer> {
}
