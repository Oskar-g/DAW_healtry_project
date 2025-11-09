package com.oscar.healtry.service;

import java.util.ArrayList;
import java.util.List;

import com.oscar.healtry.dto.dieta.ComidaAlimentoDTO;
import com.oscar.healtry.dto.dieta.ComidaDTO;
import com.oscar.healtry.model.Alimento;
import com.oscar.healtry.model.Comida;
import com.oscar.healtry.model.ComidaAlimento;
import com.oscar.healtry.model.ComidaAlimentoId;

public interface ComidaService {
	ComidaDTO crear(ComidaDTO alimentoDTO);

	List<ComidaDTO> listarTodos();

	ComidaDTO editar(ComidaDTO alimentoDTO);

	ComidaDTO obtener(Integer id);

	void eliminar(Long id);

	// =========================
	// Mapeo
	// =========================

	public static Comida mapToEntity(ComidaDTO dto) {
		if (dto == null) {
			return null;
		}

		Comida entity = Comida.builder().id(dto.getId()).nombre(dto.getNombre()).build();

		return entity.toBuilder()
				.alimentos(new ArrayList<>(dto.getAlimentos()
						.stream()
						.map(alimento -> ComidaAlimento.builder()
								.id(ComidaAlimentoId.builder()
										.comidaId(entity.getId())
										.alimentoId(alimento.getId())
										.build())
								.comida(entity)
								.alimento(AlimentoService.mapToEntity(alimento))
								.gramos(alimento.getGramos())
								.build())
						.toList()))
				.build();
	}

	public static ComidaDTO mapToDto(Comida entidad) {
		return ComidaDTO.builder()
				.id(entidad.getId())
				.nombre(entidad.getNombre())
				.alimentos(entidad.getAlimentos().stream().map(ComidaService::toComidaAlimentoDTO).toList())
				.build();
	}

	public static ComidaAlimentoDTO toComidaAlimentoDTO(ComidaAlimento entidad) {

		if (entidad == null || entidad.getAlimento() == null) {
			return null;
		}

		Alimento alimento = entidad.getAlimento();
		return ComidaAlimentoDTO.builder()
				.id(alimento.getId())
				.nombre(alimento.getNombre())
				.proteinas(alimento.getProteinas())
				.grasas(alimento.getGrasas())
				.carbohidratos(alimento.getCarbohidratos())
				.gramos(entidad.getGramos())
				.build();
	}
}
