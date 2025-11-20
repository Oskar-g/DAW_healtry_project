package com.oscar.healtry.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService usuarioService;

	@GetMapping("/email/{email}")
	public ResponseEntity<UsuarioDTO> getByEmail(@PathVariable final String email) {
		return ResponseEntity.ok(usuarioService.obtenerPorEmail(email));
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> crear(@RequestBody final UsuarioDTO request) {
		var resultado = usuarioService.guardar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
	}

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		return ResponseEntity.ok(usuarioService.listarTodos());
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UsuarioDTO> parchear(@PathVariable final Long id, @RequestBody final UsuarioDTO usuario) {
		var resultado = usuarioService.parchear(id, usuario);
		return ResponseEntity.ok(resultado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable final Long id) {
		usuarioService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
