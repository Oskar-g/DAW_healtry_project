package com.oscar.healtry.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Alimento;

@Repository
public interface AlimentoRepository extends ListCrudRepository<Alimento, Integer> {
}
