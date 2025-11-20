package com.oscar.healtry.service;

import java.util.Collection;
import java.util.List;

import com.oscar.healtry.dto.dieta.PlanSemanalDTO;
import com.oscar.healtry.model.PlanSemanal;

public interface PlanSemanalService {

	PlanSemanalDTO crear(PlanSemanalDTO dto);

	PlanSemanalDTO editar(Long id, PlanSemanalDTO dto);

	List<PlanSemanalDTO> listarPorNutricionista(Long idNutricionista);

	PlanSemanalDTO obtenerActualPorCliente(Long idCliente);

	void eliminar(Long idNutricionista);

	public static List<PlanSemanalDTO> mapToDto(final Collection<PlanSemanal> entidades) {
		return entidades.stream().map(PlanSemanalService::mapToDto).toList();
	}

	public static PlanSemanalDTO mapToDto(final PlanSemanal entidad) {
		if (null == entidad) {
			return null;
		}

		return PlanSemanalDTO.builder()
				.id(entidad.getId())
				.alias(entidad.getAlias())
				.dias(PlanDiaService.mapToDTO(entidad.getDias()))
				.idNutricionista(entidad.getNutricionista().getId())
				.build();
	}

}
