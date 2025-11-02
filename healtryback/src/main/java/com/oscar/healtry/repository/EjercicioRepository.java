package com.oscar.healtry.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Ejercicio;

@Repository
public interface EjercicioRepository extends ListCrudRepository<Ejercicio, Integer> {
}
