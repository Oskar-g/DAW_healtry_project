package com.oscar.healtry.service;

import java.util.List;

import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.dto.admin.UsuarioDTO.NutricionistaInfo;
import com.oscar.healtry.model.Nutricionista;

public interface NutricionistaService {
	UsuarioDTO crear(UsuarioDTO nutricionista);

	UsuarioDTO buscar(Long id);

	UsuarioDTO obtener(Long id);

	List<UsuarioDTO> listarTodos();

	UsuarioDTO parchear(Long id, UsuarioDTO nutricionista);

	void parchear(UsuarioDTO nutricionista, Nutricionista entidad);

	public static UsuarioDTO mapToDto(Nutricionista entidad) {
		if (null == entidad || null == entidad.getUsuario()) {
			return null;
		}

		NutricionistaInfo nutricionistaInfo = NutricionistaInfo.builder().especialidad(entidad.getEspecialidad())
				.experienciaAnios(entidad.getExperienciaAnios()).build();

		return UsuarioService.mapToDto(entidad.getUsuario()).toBuilder().nutricionistaInfo(nutricionistaInfo).build();
	}

	public static Nutricionista mapToEntity(UsuarioDTO dto) {
		if (null == dto || null == dto.getNutricionistaInfo()) {
			return null;
		}

		NutricionistaInfo nutricionistaInfo = dto.getNutricionistaInfo();

		return Nutricionista.builder().usuario(UsuarioService.mapToEntity(dto))
				.especialidad(nutricionistaInfo.getEspecialidad())
				.experienciaAnios(nutricionistaInfo.getExperienciaAnios()).build();

	}

}
