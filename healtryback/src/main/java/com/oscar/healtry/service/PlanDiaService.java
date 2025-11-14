package com.oscar.healtry.service;

import java.util.Collection;
import java.util.List;

import com.oscar.healtry.dto.dieta.PlanDiaDTO;
import com.oscar.healtry.dto.dieta.PlanSemanalDTO;
import com.oscar.healtry.model.PlanDia;

public interface PlanDiaService {
	PlanSemanalDTO guardar(PlanDiaDTO dto);

	PlanSemanalDTO obtenerPorPlanId(Integer planId);

	public static PlanDiaDTO mapToDTO(PlanDia entidad) {
		if (null == entidad) {
			return null;
		}

		return PlanDiaDTO.builder()
				.id(entidad.getId())
				.dia(entidad.getDia())
				.tipoComida(entidad.getTipoComida())
				.comida(ComidaService.mapToDto(entidad.getComida()))
				.build();
	}

	public static List<PlanDiaDTO> mapToDTO(Collection<PlanDia> dias) {
		return dias.stream().map(PlanDiaService::mapToDTO).toList();
	}

}
