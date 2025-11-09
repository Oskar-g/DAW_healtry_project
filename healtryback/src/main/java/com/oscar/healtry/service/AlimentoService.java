package com.oscar.healtry.service;

import java.util.List;

import com.oscar.healtry.dto.dieta.AlimentoDTO;
import com.oscar.healtry.model.Alimento;

public interface AlimentoService {
	AlimentoDTO guardar(AlimentoDTO alimentoDTO);

	List<AlimentoDTO> listarTodos();

	AlimentoDTO obtener(Long id);

	void eliminar(Long id);

	// =========================
	// Mapeo
	// =========================

	public static Alimento mapToEntity(AlimentoDTO dto) {
		if (dto == null) {
			return null;
		}
		return Alimento.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.proteinas(dto.getProteinas())
				.grasas(dto.getGrasas())
				.carbohidratos(dto.getCarbohidratos())
				.build();
	}

	public static AlimentoDTO mapToDto(Alimento entidad) {
		return AlimentoDTO.builder()
				.id(entidad.getId())
				.nombre(entidad.getNombre())
				.proteinas(entidad.getProteinas())
				.grasas(entidad.getGrasas())
				.carbohidratos(entidad.getCarbohidratos())
				.build();

	}
}
