package com.oscar.healtry.service;

import java.util.List;
import java.util.Optional;

import com.oscar.healtry.dto.admin.UsuarioCrearDTO;
import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.model.Rol;
import com.oscar.healtry.model.Usuario;

public interface UsuarioService {
	UsuarioDTO guardar(UsuarioDTO dto);

	List<UsuarioDTO> listarTodos();

	UsuarioDTO obtener(Integer id);

	UsuarioDTO parchear(Integer id, UsuarioDTO usuario);

	void eliminar(Integer id);

	// =========================
	// Mapeo
	// =========================

	public static Usuario mapToEntity(UsuarioDTO dto) {
		if (null == dto) {
			return null;
		}
		return Usuario.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.apellidos(dto.getApellidos())
				.correo(dto.getCorreo())
				.rol(RolService.mapToEntity(dto.getRol()))
				.activo(dto.getActivo())
				.build();
	}

	public static Usuario mapToEntity(UsuarioCrearDTO dto) {
		if (null == dto) {
			return null;
		}

		Rol rol = Optional.ofNullable(dto.getIdRol()).map(id -> Rol.builder().id(id).build()).orElse(null);

		return Usuario.builder()
				.nombre(dto.getNombre())
				.apellidos(dto.getApellidos())
				.correo(dto.getCorreo())
				.contrasena(dto.getContrasena())
				.rol(rol)
				.activo(true)
				.build();
	}

	public static UsuarioDTO mapToDto(Usuario entidad) {
		if (null == entidad) {
			return null;
		}
		return UsuarioDTO.builder()
				.id(entidad.getId())
				.nombre(entidad.getNombre())
				.apellidos(entidad.getApellidos())
				.correo(entidad.getCorreo())
				.rol(RolService.mapToDto(entidad.getRol()))
				.activo(entidad.getActivo())
				.build();
	}
}
