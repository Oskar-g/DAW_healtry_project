package com.oscar.healtry.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.ComidaAlimento;
import com.oscar.healtry.model.ComidaAlimentoId;

@Repository
public interface ComidaAlimentoRepository extends ListCrudRepository<ComidaAlimento, ComidaAlimentoId> {
	
	void deleteByIdComidaId(Integer idComida);
	List<ComidaAlimento> findByComidaId(Integer idComida);
}
