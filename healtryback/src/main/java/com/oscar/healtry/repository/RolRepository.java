package com.oscar.healtry.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Rol;

@Repository
public interface RolRepository extends ListCrudRepository<Rol, Long> {
}
