package com.oscar.healtry.service;

import java.util.List;

import com.oscar.healtry.dto.admin.RolDTO;
import com.oscar.healtry.model.Rol;

public interface RolService {
	List<RolDTO> listarTodos();

	// =========================
	// Mapeo
	// =========================

	public static Rol mapToEntity(RolDTO dto) {
		if (dto == null) {
			return null;
		}
		return Rol.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.build();
	}

	public static RolDTO mapToDto(Rol entidad) {
		return RolDTO.builder()
				.id(entidad.getId())
				.nombre(entidad.getNombre())
				.build();
	}
}
