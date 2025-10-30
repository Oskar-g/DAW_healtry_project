package com.oscar.healtry.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Cliente;

@Repository
public interface ClienteRepository extends ListCrudRepository<Cliente, Integer> {
}
