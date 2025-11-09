package com.oscar.healtry.service;

import java.util.List;

import com.oscar.healtry.dto.admin.RolDTO;
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
		Rol rol = dto.getRol() != null ? RolService.mapToEntity(dto.getRol()) : null;
		return Usuario.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.apellidos(dto.getApellidos())
				.correo(dto.getCorreo())
				.rol(rol)
				.activo(dto.getActivo())
				.build();
	}
    
	public static Usuario mapToEntity(UsuarioCrearDTO dto) {
		Rol rol = dto.getIdRol() != null ? Rol.builder().id(dto.getIdRol()).build() : null;
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
		RolDTO rol = entidad.getRol() != null ? RolService.mapToDto(entidad.getRol()) : null;
		return UsuarioDTO.builder()
				.id(entidad.getId())
				.nombre(entidad.getNombre())
				.apellidos(entidad.getApellidos())
				.correo(entidad.getCorreo())
				.rol(rol)
				.activo(entidad.getActivo())
				.build();
	}
}
