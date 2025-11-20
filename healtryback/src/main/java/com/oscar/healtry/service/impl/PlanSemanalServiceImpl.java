package com.oscar.healtry.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oscar.healtry.dto.dieta.PlanDiaDTO;
import com.oscar.healtry.dto.dieta.PlanSemanalDTO;
import com.oscar.healtry.model.Comida;
import com.oscar.healtry.model.Dieta;
import com.oscar.healtry.model.Nutricionista;
import com.oscar.healtry.model.PlanDia;
import com.oscar.healtry.model.PlanSemanal;
import com.oscar.healtry.repository.ComidaRepository;
import com.oscar.healtry.repository.DietaRepository;
import com.oscar.healtry.repository.NutricionistaRepository;
import com.oscar.healtry.repository.PlanSemanalRepository;
import com.oscar.healtry.service.PlanSemanalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanSemanalServiceImpl implements PlanSemanalService {

	private final PlanSemanalRepository planRepository;
	private final NutricionistaRepository nutricionistaRepository;
	private final ComidaRepository comidaRepository;
	private final DietaRepository dietaRepository;

	@Override
	@Transactional
	public PlanSemanalDTO crear(final PlanSemanalDTO dto) {
		var plan = new PlanSemanal();
		var nutricionista = getNutricionista(dto);
		var dias = dto.getDias().stream().map(d -> {
			var dia = new PlanDia();
			dia.setPlan(plan);
			dia.setDia(d.getDia());
			dia.setTipoComida(d.getTipoComida());
			dia.setComida(getComidaDia(d));
			return dia;
		}).toList();

		plan.setAlias(dto.getAlias()).setDias(dias).setNutricionista(nutricionista);
		var guardado = planRepository.save(plan);
		return PlanSemanalService.mapToDto(guardado);
	}

	@Override
	public List<PlanSemanalDTO> listarPorNutricionista(final Long idNutricionista) {
		var planes = planRepository.findByNutricionistaId(idNutricionista);
		return PlanSemanalService.mapToDto(planes);
	}

	@Override
	public PlanSemanalDTO obtenerActualPorCliente(final Long idCliente) {
		var ahora = LocalDate.now();

		var plan = dietaRepository.findActivaPorCliente(idCliente, ahora).map(Dieta::getPlan).orElse(null);
		return PlanSemanalService.mapToDto(plan);
	}

	@Override
	@Transactional
	public PlanSemanalDTO editar(final Long id, final PlanSemanalDTO dto) {

		var plan = planRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Plan semanal no encontrado: " + id));

		var dias = dto.getDias().stream().map(d -> {
			var dia = new PlanDia();
			dia.setPlan(plan);
			dia.setDia(d.getDia());
			dia.setTipoComida(d.getTipoComida());
			dia.setComida(getComidaDia(d));
			return dia;
		}).toList();

		plan.setAlias(dto.getAlias());
		plan.getDias().clear();
		plan.getDias().addAll(dias);

		var guardado = planRepository.save(plan);
		return PlanSemanalService.mapToDto(guardado);
	}

	private Nutricionista getNutricionista(final PlanSemanalDTO dto) {
		if (null == dto || null == dto.getIdNutricionista()) {
			throw new IllegalArgumentException("Nutricionista inválido");
		}

		var idNutricionista = dto.getIdNutricionista();
		return nutricionistaRepository.findById(idNutricionista)
				.orElseThrow(() -> new IllegalArgumentException("Nutricionista no encontrado: " + idNutricionista));
	}

	private Comida getComidaDia(final PlanDiaDTO planDia) {
		if (null == planDia.getComida() || null == planDia.getComida().getId()) {
			throw new IllegalArgumentException("Comida inválida");
		}

		var idComida = planDia.getComida().getId();
		return comidaRepository.findById(idComida).orElseThrow(() -> {
			return new IllegalArgumentException("Comida no encontrada: " + idComida);
		});
	}

	@Override
	public void eliminar(final Long idNutricionista) {
		planRepository.deleteById(idNutricionista);
	}
}
