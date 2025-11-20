package com.oscar.healtry.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.oscar.healtry.dto.dieta.ComidaAlimentoDTO;
import com.oscar.healtry.dto.dieta.ComidaDTO;
import com.oscar.healtry.model.Comida;
import com.oscar.healtry.model.ComidaAlimento;
import com.oscar.healtry.model.ComidaAlimentoId;

public interface ComidaService {
	ComidaDTO crear(ComidaDTO alimentoDTO);

	List<ComidaDTO> listarTodos();

	ComidaDTO obtener(Long id);

	ComidaDTO editar(ComidaDTO alimentoDTO);

	void eliminar(Long id);

	// =========================
	// Mapeo
	// =========================

	public static Comida mapToEntity(ComidaDTO dto) {
		if (null == dto) {
			return null;
		}

		Comida entity = Comida.builder().id(dto.getId()).nombre(dto.getNombre()).build();

		return entity.toBuilder()
				.alimentos(new ArrayList<>(dto.getAlimentos()
						.stream()
						.map(alimento -> ComidaAlimento.builder()
								.id(ComidaAlimentoId.builder()
										.idComida(entity.getId())
										.idAlimento(alimento.getId())
										.build())
								.comida(entity)
								.alimento(AlimentoService.mapToEntity(alimento))
								.gramos(alimento.getGramos())
								.build())
						.toList()))
				.build();
	}

	public static ComidaDTO mapToDto(Comida entidad) {
		if (null == entidad) {
			return null;
		}

		return ComidaDTO.builder()
				.id(entidad.getId())
				.nombre(entidad.getNombre())
				.alimentos(entidad.getAlimentos().stream().map(ComidaService::mapToComidaAlimentoDTO).toList())
				.build();
	}

	public static ComidaAlimentoDTO mapToComidaAlimentoDTO(ComidaAlimento entidad) {
		if (null == entidad) {
			return null;
		}
		return Optional.ofNullable(entidad.getAlimento())
				.map(alimento -> ComidaAlimentoDTO.builder()
						.id(alimento.getId())
						.nombre(alimento.getNombre())
						.proteinas(alimento.getProteinas())
						.grasas(alimento.getGrasas())
						.carbohidratos(alimento.getCarbohidratos())
						.gramos(entidad.getGramos())
						.build())
				.orElse(null);

	}
}
