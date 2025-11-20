package com.oscar.healtry.service;

import java.util.List;
import java.util.Optional;

import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.dto.admin.UsuarioDTO.ClienteInfo;
import com.oscar.healtry.model.Cliente;
import com.oscar.healtry.model.Nutricionista;

public interface ClienteService {
	UsuarioDTO crear(UsuarioDTO cliente);

	UsuarioDTO buscar(Long id);

	List<UsuarioDTO> listarPorNutricionista(Long idNutricionista);

	UsuarioDTO parchear(Long id, UsuarioDTO cliente);

	void parchear(UsuarioDTO cliente, Cliente entidad);

	public static UsuarioDTO mapToDto(Cliente entidad) {
		if (null == entidad || null == entidad.getUsuario()) {
			return null;
		}

		Long idNutricionista = Optional.ofNullable(entidad.getNutricionista()).map(e -> e.getId()).orElse(null);
		ClienteInfo clienteInfo = ClienteInfo.builder()
				.idNutricionista(idNutricionista)
				.alturaCm(entidad.getAlturaCm())
				.fechaNacimiento(entidad.getFechaNacimiento())
				.pesoKg(entidad.getPesoKg())
				.sexo(entidad.getSexo())
				.build();

		return UsuarioService.mapToDto(entidad.getUsuario()).toBuilder()
				.clienteInfo(clienteInfo).build();
	}

	public static Cliente mapToEntity(UsuarioDTO dto) {
		if (null == dto || null == dto.getClienteInfo()) {
			return null;
		}

		ClienteInfo clienteInfo = dto.getClienteInfo();

		return Cliente.builder()
				.nutricionista(Nutricionista.builder().id(clienteInfo.getIdNutricionista()).build())
				.usuario(UsuarioService.mapToEntity(dto))
				.alturaCm(clienteInfo.getAlturaCm())
				.fechaNacimiento(clienteInfo.getFechaNacimiento())
				.pesoKg(clienteInfo.getPesoKg())
				.sexo(clienteInfo.getSexo())
				.build();

	}

}
