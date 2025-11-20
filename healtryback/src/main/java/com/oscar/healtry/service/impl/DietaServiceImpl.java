package com.oscar.healtry.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oscar.healtry.dto.dieta.DietasClientesDTO;
import com.oscar.healtry.model.Dieta;
import com.oscar.healtry.model.DietaCliente;
import com.oscar.healtry.model.DietaClienteId;
import com.oscar.healtry.repository.ClienteRepository;
import com.oscar.healtry.repository.DietaRepository;
import com.oscar.healtry.repository.PlanSemanalRepository;
import com.oscar.healtry.service.DietaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DietaServiceImpl implements DietaService {

	private final PlanSemanalRepository planSemanalRepository;
	private final DietaRepository dietaRepository;
	private final ClienteRepository clienteRepository;

	@Override
	@Transactional
	public DietasClientesDTO crear(final DietasClientesDTO dto) {

		var idPlan = dto.getIdPlan();
		var plan = planSemanalRepository.findById(idPlan)
				.orElseThrow(() -> new IllegalArgumentException("Plan no encontrado: " + idPlan));

		var dieta = new Dieta();

		var clientes = dto.getClientes().stream().map(idCliente -> buildDietaCliente(dieta, idCliente)).toList();

		dieta.setPlan(plan)
				.setFechaInicio(dto.getFechaInicio())
				.setFechaFin(dto.getFechaFin())
				.setDietaClientes(new ArrayList<>(clientes));

		var dietaGuardada = dietaRepository.save(dieta);
		return DietaService.mapToDto(dietaGuardada);
	}

	private DietaCliente buildDietaCliente(final Dieta dieta, final Long idCliente) {
		var id = new DietaClienteId(dieta.getId(), idCliente);
		return DietaCliente.builder()
				.id(id)
				.dieta(dieta)
				.cliente(clienteRepository.getReferenceById(idCliente))
				.build();
	}

	@Override
	public List<DietasClientesDTO> obtenerPorNutricionista(final Long idNutricionista) {
		var result = dietaRepository.findByPlanNutricionistaId(idNutricionista)
				.stream()
				.map(DietaService::mapToDto)
				.toList();
		return result;
	}

	@Override
	@Transactional
	public DietasClientesDTO editar(final Long idDieta, final DietasClientesDTO dto) {

		var dieta = dietaRepository.getReferenceById(idDieta);

		var clientes = dto.getClientes().stream().map(idCliente -> buildDietaCliente(dieta, idCliente)).toList();

		dieta.getDietaClientes().clear();
		dieta.getDietaClientes().addAll(clientes);

		var dietaGuardada = dietaRepository.save(dieta);
		return DietaService.mapToDto(dietaGuardada);
	}

	@Override
	public void eliminar(final Long idDieta) {
		dietaRepository.deleteById(idDieta);
	}
}
