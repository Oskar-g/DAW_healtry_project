package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Medida;

@Repository
public interface MedidaRepository extends JpaRepository<Medida, Integer> {
	List<Medida> findByClienteIdCliente(Integer idCliente);
}
