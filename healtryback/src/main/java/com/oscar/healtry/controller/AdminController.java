package com.oscar.healtry.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.healtry.dto.admin.RolDTO;
import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.service.RolService;
import com.oscar.healtry.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Validated
public class AdminController {

	private final UsuarioService usuarioService;
	private final RolService rolService;

	/** Listar todos los usuarios */
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
		return ResponseEntity.ok(usuarioService.listarTodos());
	}

	/** Crear o editar usuario */
	@RequestMapping(value = "/usuarios", method = {
			RequestMethod.POST, RequestMethod.PUT
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody @Validated UsuarioDTO request) {
		UsuarioDTO resultado = usuarioService.guardar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
	}

	/** parchear usuario */
	@PatchMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioDTO> parchearUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuario) {
		UsuarioDTO resultado = usuarioService.parchear(id, usuario);
		return ResponseEntity.ok(resultado);
	}

	/** Listar todos los roles */
	@GetMapping("/roles")
	public ResponseEntity<List<RolDTO>> listarRoles() {
		return ResponseEntity.ok(rolService.listarTodos());
	}

	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarUsuario(@PathVariable Integer id) {
		usuarioService.eliminar(id);
	}
}
