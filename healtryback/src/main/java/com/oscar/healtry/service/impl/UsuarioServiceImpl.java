package com.oscar.healtry.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.dto.admin.UsuarioUpdateDTO;
import com.oscar.healtry.model.Rol;
import com.oscar.healtry.model.Usuario;
import com.oscar.healtry.repository.RolRepository;
import com.oscar.healtry.repository.UsuarioRepository;
import com.oscar.healtry.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final RolRepository rolRepository;

	@Override
	public UsuarioDTO guardar(UsuarioDTO dto) {
		log.debug("ENTRADA crearUsuarioDTO({})", dto);

		Usuario usuario = UsuarioService.mapToEntity(dto);
		usuario = usuarioRepository.save(usuario);

		UsuarioDTO response = UsuarioService.mapToDto(usuario);
		log.debug("SALIDA crearUsuarioDTO -> {}", response);
		return response;
	}

	@Override
	public UsuarioDTO actualizar(Integer id, UsuarioUpdateDTO dto) {
		log.debug("ENTRADA actualizarUsuarioDTO(id={}, dto={})", id, dto);

		Usuario usuario = assertUser(id);

		usuario.setNombre(dto.getNombre());
		usuario.setApellidos(dto.getApellidos());
		usuario.setCorreo(dto.getCorreo());

		if (dto.getContrasena() != null && !dto.getContrasena().isEmpty()) {
			usuario.setContrasena(dto.getContrasena());
		}

		usuario = usuarioRepository.save(usuario);

		UsuarioDTO response = UsuarioService.mapToDto(usuario);
		log.debug("SALIDA actualizarUsuarioDTO -> {}", response);
		return response;
	}

	@Override
	public UsuarioDTO parchear(Integer id, UsuarioDTO usuario) {
		log.debug("ENTRADA parchearUsuario(id={}, usuario={})", id, usuario);

		Usuario existente = assertUser(id);

		if (null != usuario.getNombre()) {
			existente.setActivo(usuario.getActivo());
		}
		if (null != usuario.getApellidos()) {
			existente.setActivo(usuario.getActivo());
		}
		if (null != usuario.getCorreo()) {
			existente.setActivo(usuario.getActivo());
		}
		if (null != usuario.getActivo()) {
			existente.setActivo(usuario.getActivo());
		}

		existente = usuarioRepository.save(existente);

		UsuarioDTO response = UsuarioService.mapToDto(existente);
		log.debug("SALIDA cambiarEstadoDTO -> {}", response);
		return response;
	}

	@Override
	public UsuarioDTO asignarRol(Integer id, Integer idRol) {
		log.debug("ENTRADA asignarRol(id={}, idRol={})", id, idRol);

		Usuario usuario = assertUser(id);
		Rol rol = rolRepository.findById(idRol)
				.orElseThrow(() -> new IllegalArgumentException("Rol no encontrado: " + idRol));

		usuario.setRol(rol);
		usuario = usuarioRepository.save(usuario);

		UsuarioDTO response = UsuarioService.mapToDto(usuario);
		log.debug("SALIDA asignarRolDTO -> {}", response);
		return response;
	}

	@Override
	public UsuarioDTO buscarPorId(Integer id) {
		log.debug("ENTRADA buscarDTOPorId({})", id);

		Usuario usuario = assertUser(id);

		UsuarioDTO response = UsuarioService.mapToDto(usuario);
		log.debug("SALIDA buscarDTOPorId -> {}", response);
		return response;
	}

	@Override
	public List<UsuarioDTO> listarTodos() {
		log.debug("ENTRADA listarTodosDTO()");

		List<UsuarioDTO> lista = usuarioRepository.findAll().stream().map(UsuarioService::mapToDto).toList();

		log.debug("SALIDA listarTodosDTO -> {}", lista);
		return lista;
	}

	private Usuario assertUser(Integer id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + id));
	}
	
	@Override
	public void eliminar(Integer id) {
		log.debug("ENTRADA eliminar({})", id);
		
		usuarioRepository.deleteById(id);
		
		log.debug("SALIDA eliminar");
	}


}
