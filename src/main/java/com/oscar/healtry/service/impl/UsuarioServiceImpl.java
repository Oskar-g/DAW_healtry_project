package com.oscar.healtry.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oscar.healtry.dto.admin.UsuarioCrearDTO;
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
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final RolRepository rolRepository;

	@Override
	public UsuarioDTO crearUsuarioDTO(UsuarioCrearDTO dto) {
		log.debug("ENTRADA crearUsuarioDTO({})", dto);

		Usuario usuario = mapToEntity(dto);
		usuario = usuarioRepository.save(usuario);

		UsuarioDTO response = mapToDto(usuario);
		log.debug("SALIDA crearUsuarioDTO -> {}", response);
		return response;
	}

	@Override
	public UsuarioDTO actualizarUsuarioDTO(Integer id, UsuarioUpdateDTO dto) {
		log.debug("ENTRADA actualizarUsuarioDTO(id={}, dto={})", id, dto);

		Usuario usuario = assertUser(id);

		usuario.setNombre(dto.getNombre());
		usuario.setApellidos(dto.getApellidos());
		usuario.setCorreo(dto.getCorreo());

		if (dto.getContrasena() != null && !dto.getContrasena().isEmpty()) {
			usuario.setContrasena(dto.getContrasena());
		}

		usuario = usuarioRepository.save(usuario);

		UsuarioDTO response = mapToDto(usuario);
		log.debug("SALIDA actualizarUsuarioDTO -> {}", response);
		return response;
	}

	@Override
	public UsuarioDTO cambiarEstadoDTO(Integer id, boolean activo) {
		log.debug("ENTRADA cambiarEstadoDTO(id={}, activo={})", id, activo);

		Usuario usuario = assertUser(id);

		usuario.setActivo(activo);
		usuario = usuarioRepository.save(usuario);

		UsuarioDTO response = mapToDto(usuario);
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

		UsuarioDTO response = mapToDto(usuario);
		log.debug("SALIDA asignarRolDTO -> {}", response);
		return response;
	}

	@Override
	public UsuarioDTO buscarPorId(Integer id) {
		log.debug("ENTRADA buscarDTOPorId({})", id);

		Usuario usuario = assertUser(id);

		UsuarioDTO response = mapToDto(usuario);
		log.debug("SALIDA buscarDTOPorId -> {}", response);
		return response;
	}

	@Override
	public List<UsuarioDTO> listarTodos() {
		log.debug("ENTRADA listarTodosDTO()");

		List<UsuarioDTO> lista = usuarioRepository.findAll().stream().map(this::mapToDto).toList();

		log.debug("SALIDA listarTodosDTO -> {}", lista);
		return lista;
	}

	private Usuario assertUser(Integer id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + id));
	}

	// =========================
	// Mapeo
	// =========================

	private Usuario mapToEntity(UsuarioCrearDTO dto) {
		Rol rol = dto.getIdRol() != null ? Rol.builder().idRol(dto.getIdRol()).build() : null;
		return Usuario.builder()
				.nombre(dto.getNombre())
				.apellidos(dto.getApellidos())
				.correo(dto.getCorreo())
				.contrasena(dto.getContrasena())
				.rol(rol)
				.activo(true)
				.build();
	}

	private UsuarioDTO mapToDto(Usuario entity) {
		return UsuarioDTO.builder()
				.idUsuario(entity.getIdUsuario())
				.nombre(entity.getNombre())
				.correo(entity.getCorreo())
				.rol(entity.getRol() != null ? entity.getRol().getNombre() : null)
				.activo(entity.getActivo())
				.build();
	}
}
