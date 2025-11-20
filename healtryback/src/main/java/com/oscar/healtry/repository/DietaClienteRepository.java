package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscar.healtry.model.DietaCliente;
import com.oscar.healtry.model.DietaClienteId;

public interface DietaClienteRepository extends JpaRepository<DietaCliente, DietaClienteId> {
	List<DietaCliente> findByIdIdDieta(Long idDieta);

	void deleteAllByIdIdDieta(Long idDieta);
}
