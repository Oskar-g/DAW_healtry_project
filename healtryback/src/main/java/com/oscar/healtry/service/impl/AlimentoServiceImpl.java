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
	public AlimentoDTO guardar(AlimentoDTO alimentoDTO) {
		log.debug("ENTRADA guardar({})", alimentoDTO);

		Alimento alimento = mapDtoToEntity(alimentoDTO);
		alimento = alimentoRepository.save(alimento);

		AlimentoDTO response = mapEntityToDto(alimento);
		log.debug("SALIDA guardar -> {}", response);
		return response;
	}

	@Override
	public void eliminar(Long id) {
		log.debug("ENTRADA eliminar({})", id);
		alimentoRepository.deleteById(id.intValue());
		log.debug("SALIDA eliminar");
	}

	@Override
	public List<AlimentoDTO> listarTodos() {
		log.debug("ENTRADA listarTodos()");

		List<AlimentoDTO> lista = alimentoRepository.findAll().stream().map(this::mapEntityToDto).toList();

		log.debug("SALIDA listarTodos -> {}", lista);
		return lista;
	}

	@Override
	public AlimentoDTO obtener(Long id) {
		log.debug("ENTRADA obtenerAlimento({})", id);

		Alimento alimento = alimentoRepository.findById(id.intValue())
				.orElseThrow(() -> new IllegalArgumentException("Alimento no encontrado: " + id));

		AlimentoDTO response = mapEntityToDto(alimento);
		log.debug("SALIDA obtenerAlimento -> {}", response);
		return response;
	}

	// =========================
	// Métodos de mapeo manual con builder
	// =========================

	private Alimento mapDtoToEntity(AlimentoDTO dto) {
		return Alimento.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.proteinas(dto.getProteinas())
				.grasas(dto.getGrasas())
				.carbohidratos(dto.getCarbohidratos())
				.build();
	}

	private AlimentoDTO mapEntityToDto(Alimento entidad) {
		return AlimentoDTO.builder()
				.id(entidad.getId())
				.nombre(entidad.getNombre())
				.proteinas(entidad.getProteinas())
				.grasas(entidad.getGrasas())
				.carbohidratos(entidad.getCarbohidratos())
				.build();
	}

}
