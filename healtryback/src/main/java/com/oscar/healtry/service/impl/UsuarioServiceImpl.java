package com.oscar.healtry.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.model.Usuario;
import com.oscar.healtry.repository.ClienteRepository;
import com.oscar.healtry.repository.NutricionistaRepository;
import com.oscar.healtry.repository.UsuarioRepository;
import com.oscar.healtry.service.ClienteService;
import com.oscar.healtry.service.NutricionistaService;
import com.oscar.healtry.service.UsuarioService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final NutricionistaRepository nutricionistaRepository;
	private final ClienteRepository clienteRepository;

	@Override
	@Transactional
	public UsuarioDTO guardar(final UsuarioDTO dto) {
		log.debug("ENTRADA crearUsuarioDTO({})", dto);

		var usuario = UsuarioService.mapToEntity(dto);
		usuario = usuarioRepository.save(usuario);

		var response = UsuarioService.mapToDto(usuario);
		log.debug("SALIDA crearUsuarioDTO -> {}", response);
		return response;
	}

	@Override
	public UsuarioDTO obtener(final Long id) {
		log.debug("ENTRADA buscarDTOPorId({})", id);

		var usuario = assertUser(id);

		var response = UsuarioService.mapToDto(usuario);
		log.debug("SALIDA buscarDTOPorId -> {}", response);
		return response;
	}

	@Override
	public UsuarioDTO obtenerPorEmail(final String email) {
		return usuarioRepository.findByCorreo(email).map(UsuarioService::mapToDto).orElse(null);
	}

	@Override
	public List<UsuarioDTO> listarTodos() {
		log.debug("ENTRADA listarTodosDTO()");

		var lista = usuarioRepository.findAll().stream().map(usuario -> {

			Optional<UsuarioDTO> nutricionista = nutricionistaRepository.findById(usuario.getId())
					.map(NutricionistaService::mapToDto);

			Optional<UsuarioDTO> cliente = clienteRepository.findById(usuario.getId()).map(ClienteService::mapToDto);

			return nutricionista.or(() -> cliente).orElse(UsuarioService.mapToDto(usuario));

		}).toList();

		log.debug("SALIDA listarTodosDTO -> {}", lista);
		return lista;
	}

	private Usuario assertUser(final Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + id));
	}

	@Override
	@Transactional
	public UsuarioDTO parchear(final Long id, final UsuarioDTO usuario) {
		log.debug("ENTRADA parchearUsuario(id={}, usuario={})", id, usuario);

		var existente = assertUser(id);

		parchear(usuario, existente);

		existente = usuarioRepository.save(existente);

		var response = UsuarioService.mapToDto(existente);
		log.debug("SALIDA cambiarEstadoDTO -> {}", response);
		return response;
	}

	@Override
	public void parchear(final UsuarioDTO usuario, final Usuario existente) {
		if (null != usuario.getNombre()) {
			existente.setNombre(usuario.getNombre());
		}
		if (null != usuario.getApellidos()) {
			existente.setApellidos(usuario.getApellidos());
		}
		if (null != usuario.getCorreo()) {
			existente.setCorreo(usuario.getCorreo());
		}
		if (null != usuario.getContrasena()) {
			existente.setContrasena(usuario.getContrasena());
		}
		if (null != usuario.getActivo()) {
			existente.setActivo(usuario.getActivo());
		}
	}

	@Override
	@Transactional
	public void eliminar(final Long id) {
		log.debug("ENTRADA eliminar({})", id);

		usuarioRepository.deleteById(id);

		log.debug("SALIDA eliminar");
	}

}
