package com.oscar.healtry.service;

import java.util.List;

import com.oscar.healtry.dto.dieta.PlanSemanalDTO;
import com.oscar.healtry.model.PlanSemanal;

public interface PlanSemanalService {
	PlanSemanalDTO guardar(PlanSemanalDTO dto);

	PlanSemanalDTO obtener(Long id);

	List<PlanSemanalDTO> listarTodos();

	public static PlanSemanalDTO mapToDto(PlanSemanal entidad) {
		if (null == entidad) {
			return null;
		}

		return PlanSemanalDTO.builder()
				.id(entidad.getId())
				.alias(entidad.getAlias())
				.dias(PlanDiaService.mapToDTO(entidad.getDias()))
				.build();
	}

}
