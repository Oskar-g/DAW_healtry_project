package com.oscar.healtry.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.oscar.healtry.model.Usuario;

@Repository
public interface UsuarioRepository extends ListCrudRepository<Usuario, Integer> {
	Optional<Usuario> findByCorreo(String correo);
}
