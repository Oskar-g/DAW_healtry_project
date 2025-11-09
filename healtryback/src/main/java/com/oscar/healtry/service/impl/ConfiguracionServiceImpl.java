package com.oscar.healtry.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oscar.healtry.dto.admin.ConfiguracionCreateDTO;
import com.oscar.healtry.dto.admin.ConfiguracionDTO;
import com.oscar.healtry.exception.ExcepcionGeneral;
import com.oscar.healtry.model.Configuracion;
import com.oscar.healtry.repository.ConfiguracionRepository;
import com.oscar.healtry.service.ConfiguracionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfiguracionServiceImpl implements ConfiguracionService {

	private final ConfiguracionRepository configuracionRepository;

	@Override
	public ConfiguracionDTO guardar(ConfiguracionCreateDTO dto) {
		log.debug("ENTRADA guardarDTO({})", dto);

		Configuracion configuracion = mapCreateDtoToEntity(dto);
		configuracion = configuracionRepository.save(configuracion);

		ConfiguracionDTO response = mapEntityToDto(configuracion);
		log.debug("SALIDA guardarDTO -> {}", response);
		return response;
	}

	@Override
	public void eliminarPorClave(String clave) {
		log.debug("ENTRADA eliminarPorClave({})", clave);

		configuracionRepository.deleteById(clave);

		log.debug("SALIDA eliminarPorClave");
	}

	@Override
	public ConfiguracionDTO obtenerPorClaveDTO(String clave) {
		log.debug("ENTRADA obtenerPorClaveDTO({})", clave);

		Configuracion configuracion = configuracionRepository.findById(clave)
				.orElseThrow(() -> ExcepcionGeneral.of(HttpStatus.NOT_FOUND, "Configuración no encontrada {0}", clave));

		ConfiguracionDTO response = mapEntityToDto(configuracion);
		log.debug("SALIDA obtenerPorClaveDTO -> {}", response);
		return response;
	}

	@Override
	public List<ConfiguracionDTO> listarTodasDTO() {
		log.debug("ENTRADA listarTodasDTO()");

		List<ConfiguracionDTO> lista = configuracionRepository.findAll().stream().map(this::mapEntityToDto).toList();

		log.debug("SALIDA listarTodasDTO -> {}", lista);
		return lista;
	}

	// =========================
	// MAPEO
	// =========================

	private Configuracion mapCreateDtoToEntity(ConfiguracionCreateDTO dto) {
		return Configuracion.builder()
				.clave(dto.getClave())
				.valor(dto.getValor())
				.tipo(dto.getTipo())
				.descripcion(dto.getDescripcion())
				.build();
	}

	private ConfiguracionDTO mapEntityToDto(Configuracion entidad) {
		return ConfiguracionDTO.builder()
				.clave(entidad.getClave())
				.valor(entidad.getValor())
				.tipo(entidad.getTipo())
				.descripcion(entidad.getDescripcion())
				.build();
	}
}
