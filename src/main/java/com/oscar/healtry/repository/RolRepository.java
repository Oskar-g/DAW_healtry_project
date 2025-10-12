package com.oscar.healtry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
