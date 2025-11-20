package com.oscar.healtry.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oscar.healtry.dto.dieta.AlimentoDTO;
import com.oscar.healtry.model.Alimento;
import com.oscar.healtry.repository.AlimentoRepository;
import com.oscar.healtry.service.AlimentoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlimentoServiceImpl implements AlimentoService {

	private final AlimentoRepository alimentoRepository;

	@Override
	public AlimentoDTO guardar(final AlimentoDTO alimentoDTO) {
		log.debug("ENTRADA guardar({})", alimentoDTO);

		var alimento = mapDtoToEntity(alimentoDTO);
		alimento = alimentoRepository.save(alimento);

		var response = mapEntityToDto(alimento);
		log.debug("SALIDA guardar -> {}", response);
		return response;
	}

	@Override
	public void eliminar(final Long id) {
		log.debug("ENTRADA eliminar({})", id);
		alimentoRepository.deleteById(id);
		log.debug("SALIDA eliminar");
	}

	@Override
	public List<AlimentoDTO> listarTodos() {
		log.debug("ENTRADA listarTodos()");

		var lista = alimentoRepository.findAll().stream().map(this::mapEntityToDto).toList();

		log.debug("SALIDA listarTodos -> {}", lista);
		return lista;
	}

	@Override
	public AlimentoDTO obtener(final Long id) {
		log.debug("ENTRADA obtenerAlimento({})", id);

		var alimento = alimentoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Alimento no encontrado: " + id));

		var response = mapEntityToDto(alimento);
		log.debug("SALIDA obtenerAlimento -> {}", response);
		return response;
	}

	// =========================
	// Métodos de mapeo manual con builder
	// =========================

	private Alimento mapDtoToEntity(final AlimentoDTO dto) {
		return Alimento.builder().id(dto.getId()).nombre(dto.getNombre()).proteinas(dto.getProteinas())
				.grasas(dto.getGrasas()).carbohidratos(dto.getCarbohidratos()).build();
	}

	private AlimentoDTO mapEntityToDto(final Alimento entidad) {
		return AlimentoDTO.builder().id(entidad.getId()).nombre(entidad.getNombre()).proteinas(entidad.getProteinas())
				.grasas(entidad.getGrasas()).carbohidratos(entidad.getCarbohidratos()).build();
	}

}
