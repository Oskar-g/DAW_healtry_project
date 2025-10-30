package com.oscar.healtry.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.healtry.dto.admin.AsignarRolDTO;
import com.oscar.healtry.dto.admin.UsuarioCrearDTO;
import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.dto.admin.UsuarioUpdateDTO;
import com.oscar.healtry.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Validated
public class AdminController {

	private final UsuarioService usuarioService;

	/** Listar todos los usuarios */
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
		return ResponseEntity.ok(usuarioService.listarTodos());
	}

	/** Obtener usuario por ID */
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Integer id) {
		return ResponseEntity.ok(usuarioService.buscarPorId(id));
	}

	/** Crear nuevo usuario */
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDTO crearUsuario(@RequestBody @Validated UsuarioCrearDTO request) {
		return usuarioService.crearUsuarioDTO(request);
	}

	/** Actualizar usuario */
	@PutMapping("/usuarios/{id}")
	public UsuarioDTO actualizarUsuario(@PathVariable Integer id, @RequestBody @Validated UsuarioUpdateDTO request) {
		return usuarioService.actualizarUsuarioDTO(id, request);
	}

	/** Activar / desactivar usuario */
	@PatchMapping("/usuarios/{id}/activo")
	public UsuarioDTO cambiarEstado(@PathVariable Integer id, @RequestParam boolean activo) {
		return usuarioService.cambiarEstadoDTO(id, activo);
	}

	/** Asignar rol a usuario */
	@PatchMapping("/usuarios/{id}/rol")
	public UsuarioDTO asignarRol(@PathVariable Integer id, @RequestBody @Validated AsignarRolDTO request) {
		return usuarioService.asignarRol(id, request.getIdRol());
	}
}
