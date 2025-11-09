package com.oscar.healtry.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Validated
public class UsuarioController {

	private final UsuarioService usuarioService;

	/** Crear o editar usuario */
	@RequestMapping(method = {
			RequestMethod.POST, RequestMethod.PUT
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UsuarioDTO> crear(@RequestBody UsuarioDTO request) {
		UsuarioDTO resultado = usuarioService.guardar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
	}

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		return ResponseEntity.ok(usuarioService.listarTodos());
	}

	/** parchear usuario */
	@PatchMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioDTO> parchear(@PathVariable Integer id, @RequestBody UsuarioDTO usuario) {
		UsuarioDTO resultado = usuarioService.parchear(id, usuario);
		return ResponseEntity.ok(resultado);
	}

	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Integer id) {
		usuarioService.eliminar(id);
	}
}
