package com.oscar.healtry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Configuracion;

@Repository
public interface ConfiguracionRepository extends JpaRepository<Configuracion, String> {
}
