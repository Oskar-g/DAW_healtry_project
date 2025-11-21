package com.oscar.healtry.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.model.Cliente;
import com.oscar.healtry.repository.ClienteRepository;
import com.oscar.healtry.repository.NutricionistaRepository;
import com.oscar.healtry.service.ClienteService;
import com.oscar.healtry.service.UsuarioService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository clienteRepository;
	private final UsuarioService usuarioService;
	private final NutricionistaRepository nutricionistaRepository;

	@Override
	public UsuarioDTO buscar(final Long id) {
		log.debug("ENTRADA buscar({})", id);

		var result = clienteRepository.findById(id).map(ClienteService::mapToDto).orElse(null);

		log.debug("SALIDA buscar -> {}", result);
		return result;
	}

	@Override
	public List<UsuarioDTO> listarPorNutricionista(final Long idNutricionista) {
		log.debug("ENTRADA listarPorNutricionista({})", idNutricionista);

		var listado = clienteRepository.findByNutricionistaId(idNutricionista)
				.stream()
				.map(ClienteService::mapToDto)
				.toList();

		log.debug("SALIDA listarPorNutricionista -> {}", listado);
		return listado;
	}

	@Override
	@Transactional
	public UsuarioDTO crear(final UsuarioDTO cliente) {
		log.debug("ENTRADA crear({})", cliente);

		var entidad = ClienteService.mapToEntity(cliente);

		var idNutricionista = cliente.getClienteInfo().getIdNutricionista();
		var nutricionista = nutricionistaRepository.findById(idNutricionista)
				.orElseThrow(() -> new IllegalArgumentException("Nutricionista no encontrado: " + idNutricionista));
		entidad.setNutricionista(nutricionista);
		entidad = clienteRepository.save(entidad);

		var response = ClienteService.mapToDto(entidad);
		log.debug("SALIDA crear -> {}", response);
		return response;
	}

	@Override
	@Transactional
	public UsuarioDTO parchear(final Long id, final UsuarioDTO cliente) {
		log.debug("ENTRADA editar({}, {})", id, cliente);

		if (null == cliente || null == cliente.getClienteInfo()) {
			throw new IllegalArgumentException("Cliente sin datos");
		}

		var entidad = clienteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + id));

		parchear(cliente, entidad);

		entidad = clienteRepository.save(entidad);
		var response = ClienteService.mapToDto(entidad);

		log.debug("SALIDA actualizarPerfil -> {}", response);
		return response;
	}

	@Override
	public void parchear(final UsuarioDTO cliente, final Cliente entidad) {
		var usuarioExistente = entidad.getUsuario();
		usuarioService.parchear(cliente, usuarioExistente);

		var clienteInfo = cliente.getClienteInfo();
		if (null != clienteInfo.getIdNutricionista()) {
			var nutricionista = nutricionistaRepository.findById(clienteInfo.getIdNutricionista()).orElse(null);
			entidad.setNutricionista(nutricionista);
		}
		if (null != clienteInfo.getAlturaCm()) {
			entidad.setAlturaCm(clienteInfo.getAlturaCm());
		}
		if (null != clienteInfo.getFechaNacimiento()) {
			entidad.setFechaNacimiento(clienteInfo.getFechaNacimiento());
		}
		if (null != clienteInfo.getPesoKg()) {
			entidad.setPesoKg(clienteInfo.getPesoKg());
		}
		if (null != clienteInfo.getSexo()) {
			entidad.setSexo(clienteInfo.getSexo());
		}
	}

	@Override
	public void eliminar(final Long id) {
		usuarioService.eliminar(id);

	}

}
