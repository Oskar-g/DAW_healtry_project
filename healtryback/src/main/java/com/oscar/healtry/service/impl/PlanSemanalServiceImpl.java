package com.oscar.healtry.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oscar.healtry.dto.dieta.PlanDiaDTO;
import com.oscar.healtry.dto.dieta.PlanSemanalDTO;
import com.oscar.healtry.model.Comida;
import com.oscar.healtry.model.PlanDia;
import com.oscar.healtry.model.PlanSemanal;
import com.oscar.healtry.repository.ComidaRepository;
import com.oscar.healtry.repository.PlanSemanalRepository;
import com.oscar.healtry.service.PlanSemanalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanSemanalServiceImpl implements PlanSemanalService {

	private final PlanSemanalRepository planRepository;
	private final ComidaRepository comidaRepository;

	@Override
	@Transactional
	public PlanSemanalDTO guardar(PlanSemanalDTO dto) {
		PlanSemanal plan = new PlanSemanal();
		plan.setAlias(dto.getAlias());

		List<PlanDia> dias = dto.getDias().stream().map(d -> {
			PlanDia dia = new PlanDia();
			dia.setPlan(plan);
			dia.setDia(d.getDia());
			dia.setTipoComida(d.getTipoComida());
			dia.setComida(getComidaDia(d));
			return dia;
		}).toList();

		plan.setDias(dias);
		PlanSemanal guardado = planRepository.save(plan);

		return PlanSemanalService.mapToDto(guardado);
	}

	private Comida getComidaDia(PlanDiaDTO d) {
		if (null == d.getComida() || null == d.getComida().getId()) {
			return null;
		}

		return comidaRepository.findById(d.getComida().getId())
				.orElseThrow(() -> new IllegalArgumentException("Comida no encontrada: " + d.getComida().getId()));

	}

	@Override
	public PlanSemanalDTO obtener(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanSemanalDTO> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
}
