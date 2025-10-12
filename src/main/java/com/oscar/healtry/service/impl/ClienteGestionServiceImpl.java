package com.oscar.healtry.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oscar.healtry.dto.cliente.ClienteDTO;
import com.oscar.healtry.dto.cliente.ClienteResumenDTO;
import com.oscar.healtry.model.Cliente;
import com.oscar.healtry.model.Usuario;
import com.oscar.healtry.repository.ClienteRepository;
import com.oscar.healtry.service.ClienteGestionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteGestionServiceImpl implements ClienteGestionService {

	private final ClienteRepository clienteRepository;

	@Override
	public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
		log.debug("ENTRADA crearCliente({})", clienteDTO);

		Cliente cliente = mapDtoToEntity(clienteDTO);
		cliente = clienteRepository.save(cliente);

		ClienteDTO response = mapEntityToDto(cliente);
		log.debug("SALIDA crearCliente -> {}", response);
		return response;
	}

	@Override
	public ClienteDTO actualizarCliente(Long clienteId, ClienteDTO clienteDTO) {
		log.debug("ENTRADA actualizarCliente(clienteId={}, clienteDTO={})", clienteId, clienteDTO);

		Cliente cliente = clienteRepository.findById(clienteId.intValue())
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + clienteId));

		// Actualizar campos
		cliente.setAlturaCm(
				clienteDTO.getAltura() != null ? BigDecimal.valueOf(clienteDTO.getAltura()) : cliente.getAlturaCm());
		cliente.setPesoKg(
				clienteDTO.getPeso() != null ? BigDecimal.valueOf(clienteDTO.getPeso()) : cliente.getPesoKg());
		cliente.setFechaNacimiento(clienteDTO.getEdad() != null ? LocalDate.now().minusYears(clienteDTO.getEdad())
				: cliente.getFechaNacimiento());
		// Otros campos como nombre/apellido/email podrían ir en Usuario si lo usas

		cliente = clienteRepository.save(cliente);

		ClienteDTO response = mapEntityToDto(cliente);
		log.debug("SALIDA actualizarCliente -> {}", response);
		return response;
	}

	@Override
	public void eliminarCliente(Long clienteId) {
		log.debug("ENTRADA eliminarCliente({})", clienteId);
		clienteRepository.deleteById(clienteId.intValue());
		log.debug("SALIDA eliminarCliente");
	}

	@Override
	public List<ClienteResumenDTO> listarClientesPorNutricionista(Long nutricionistaId) {
		log.debug("ENTRADA listarClientesPorNutricionista({})", nutricionistaId);

		// TODO: Filtrar por nutricionista si la relación existe
		List<ClienteResumenDTO> clientes = clienteRepository.findAll().stream().map(this::mapEntityToResumenDto)
				.toList();

		log.debug("SALIDA listarClientesPorNutricionista -> {}", clientes);
		return clientes;
	}

	// =========================
	// MAPEO
	// =========================

	private Cliente mapDtoToEntity(ClienteDTO dto) {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(dto.getId() != null ? dto.getId().intValue() : null);
		cliente.setAlturaCm(dto.getAltura() != null ? BigDecimal.valueOf(dto.getAltura()) : null);
		cliente.setPesoKg(dto.getPeso() != null ? BigDecimal.valueOf(dto.getPeso()) : null);
		cliente.setFechaNacimiento(dto.getEdad() != null ? LocalDate.now().minusYears(dto.getEdad()) : null);
		// TODO Usuario y demás campos se mapearían aquí si existieran
		return cliente;
	}

	private ClienteDTO mapEntityToDto(Cliente entity) {
		Usuario usuario = entity.getUsuario();
		return ClienteDTO.builder().id(entity.getIdCliente() != null ? entity.getIdCliente().longValue() : null)
				.nombre(usuario.getNombre()).apellido(usuario.getApellidos()).email(usuario.getCorreo())
				.altura(entity.getAlturaCm() != null ? entity.getAlturaCm().doubleValue() : null)
				.peso(entity.getPesoKg() != null ? entity.getPesoKg().doubleValue() : null)
				.edad(entity.getFechaNacimiento() != null
						? LocalDate.now().getYear() - entity.getFechaNacimiento().getYear()
						: null)
				.build();
	}

	private ClienteResumenDTO mapEntityToResumenDto(Cliente cliente) {
		Usuario usuario = cliente.getUsuario();
		return ClienteResumenDTO.builder()
				.id(cliente.getIdCliente() != null ? cliente.getIdCliente().longValue() : null)
				.nombreCompleto(String.format("%s %s", usuario.getNombre(), usuario.getApellidos()))
				.build();
	}
}
