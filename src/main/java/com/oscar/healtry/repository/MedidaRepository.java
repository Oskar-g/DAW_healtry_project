package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Medida;

@Repository
public interface MedidaRepository extends ListCrudRepository<Medida, Integer> {
	List<Medida> findByClienteIdCliente(Integer idCliente);
}
