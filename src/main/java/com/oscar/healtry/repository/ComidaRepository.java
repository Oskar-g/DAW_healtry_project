package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Comida;

@Repository
public interface ComidaRepository extends JpaRepository<Comida, Integer> {
	List<Comida> findByDietaIdDieta(Integer idDieta);
}
