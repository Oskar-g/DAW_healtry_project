package com.oscar.healtry.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Configuracion;

@Repository
public interface ConfiguracionRepository extends ListCrudRepository<Configuracion, String> {
}
