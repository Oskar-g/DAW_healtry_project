package com.oscar.healtry.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.dto.admin.UsuarioDTO.NutricionistaInfo;
import com.oscar.healtry.model.Nutricionista;
import com.oscar.healtry.model.Usuario;
import com.oscar.healtry.repository.NutricionistaRepository;
import com.oscar.healtry.service.NutricionistaService;
import com.oscar.healtry.service.UsuarioService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NutricionistaServiceImpl implements NutricionistaService {

	private final NutricionistaRepository nutricionistaRepository;
	private final UsuarioService usuarioService;

	@Override
	@Transactional
	public UsuarioDTO crear(UsuarioDTO nutricionista) {
		log.debug("ENTRADA crear({})", nutricionista);

		Nutricionista entidad = NutricionistaService.mapToEntity(nutricionista);
		entidad = nutricionistaRepository.save(entidad);

		UsuarioDTO response = NutricionistaService.mapToDto(entidad);
		log.debug("SALIDA crear -> {}", response);
		return response;
	}

	@Override
	public List<UsuarioDTO> listarTodos() {
		log.debug("ENTRADA listarTodosDTO()");

		List<UsuarioDTO> lista = nutricionistaRepository.findAll().stream().map(NutricionistaService::mapToDto)
				.toList();

		log.debug("SALIDA listarTodosDTO -> {}", lista);
		return lista;
	}

	@Override
	public UsuarioDTO buscar(Long id) {
		log.debug("ENTRADA buscar({})", id);

		UsuarioDTO result = nutricionistaRepository.findById(id).map(NutricionistaService::mapToDto).orElse(null);

		log.debug("SALIDA buscar -> {}", result);
		return result;
	}

	@Override
	public UsuarioDTO obtener(Long id) {
		log.debug("ENTRADA obtener({})", id);

		UsuarioDTO result = nutricionistaRepository.findById(id).map(NutricionistaService::mapToDto)
				.orElseThrow(() -> new IllegalArgumentException("Nutricionista no encontrado: " + id));

		log.debug("SALIDA obtener -> {}", result);
		return result;
	}

	@Override
	@Transactional
	public UsuarioDTO parchear(Long id, UsuarioDTO nutricionista) {
		log.debug("ENTRADA editar({}, {})", id, nutricionista);

		if (null == nutricionista || null == nutricionista.getNutricionistaInfo()) {
			throw new IllegalArgumentException("Nutricionista sin datos");
		}

		Nutricionista entidad = nutricionistaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Nutricionista no encontrado: " + id));

		parchear(nutricionista, entidad);

		entidad = nutricionistaRepository.save(entidad);
		UsuarioDTO response = NutricionistaService.mapToDto(entidad);

		log.debug("SALIDA actualizarPerfil -> {}", response);
		return response;
	}

	@Override
	public void parchear(UsuarioDTO nutricionista, Nutricionista entidad) {
		Usuario usuarioExistente = entidad.getUsuario();
		usuarioService.parchear(nutricionista, usuarioExistente);

		NutricionistaInfo nutricionistaInfo = nutricionista.getNutricionistaInfo();
		if (null != nutricionistaInfo.getEspecialidad()) {
			entidad.setEspecialidad(nutricionistaInfo.getEspecialidad());
		}

		if (null != nutricionistaInfo.getExperienciaAnios()) {
			entidad.setExperienciaAnios(nutricionistaInfo.getExperienciaAnios());
		}
	}

}
