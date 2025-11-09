package com.oscar.healtry.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oscar.healtry.dto.dieta.ComidaDTO;
import com.oscar.healtry.model.Comida;
import com.oscar.healtry.model.ComidaAlimento;
import com.oscar.healtry.repository.ComidaAlimentoRepository;
import com.oscar.healtry.repository.ComidaRepository;
import com.oscar.healtry.service.ComidaService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComidaServiceImpl implements ComidaService {

	private final ComidaRepository comidaRepository;
	private final ComidaAlimentoRepository comidaAlimentoRepository;

	@Override
	@Transactional
	public ComidaDTO crear(ComidaDTO comidaDTO) {
		log.debug("ENTRADA crear({})", comidaDTO);
		ComidaDTO response = guardar(comidaDTO);
		log.debug("SALIDA crear -> {}", response);
		return response;
	}

	@Override
	@Transactional
	public ComidaDTO editar(ComidaDTO comidaDTO) {
		log.debug("ENTRADA editar({})", comidaDTO);

		comidaAlimentoRepository.deleteByIdComidaId(comidaDTO.getId());

		ComidaDTO response = guardar(comidaDTO);

		log.debug("SALIDA editar -> {}", response);
		return response;
	}

	public ComidaDTO guardar(ComidaDTO comidaDTO) {

		Comida comida = ComidaService.mapToEntity(comidaDTO);
		List<ComidaAlimento> alimentos = comida.getAlimentos();

		comida.setAlimentos(Collections.emptyList());
		Comida comidaGuardada = comidaRepository.save(comida);

		alimentos.stream().map(alimento -> alimento.setComida(comidaGuardada)).forEach(comidaAlimentoRepository::save);

		return obtener(comidaGuardada.getId());
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		log.debug("ENTRADA eliminar({})", id);
		comidaRepository.deleteById(id.intValue());
		log.debug("SALIDA eliminar");
	}

	@Override
	@Transactional
	public List<ComidaDTO> listarTodos() {
		log.debug("ENTRADA listarTodos()");

		List<ComidaDTO> lista = comidaRepository.findAll().stream().map(ComidaService::mapToDto).toList();

		log.debug("SALIDA listarTodos -> {}", lista);
		return lista;
	}

	@Override
	@Transactional
	public ComidaDTO obtener(Integer id) {
		log.debug("ENTRADA obtenerComida({})", id);

		ComidaDTO response = comidaRepository.findById(id)
				.map(ComidaService::mapToDto)
				.orElseThrow(() -> new IllegalArgumentException("Comida no encontrado: " + id));

		log.debug("SALIDA obtenerComida -> {}", response);
		return response;
	}

}
