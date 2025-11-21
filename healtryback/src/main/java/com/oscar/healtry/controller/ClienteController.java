package com.oscar.healtry.controller;

import java.util.List;

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
import com.oscar.healtry.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteService clienteService;

	@PostMapping
	public ResponseEntity<UsuarioDTO> crear(@RequestBody @Valid final UsuarioDTO cliente) {
		return ResponseEntity.ok(clienteService.crear(cliente));
	}

	@GetMapping("/nutricionistas/{idNutricionista}")
	public ResponseEntity<List<UsuarioDTO>> obtenerPerfil(@PathVariable final Long idNutricionista) {
		return ResponseEntity.ok(clienteService.listarPorNutricionista(idNutricionista));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UsuarioDTO> parchear(@PathVariable final Long id, @RequestBody final UsuarioDTO cliente) {
		return ResponseEntity.ok(clienteService.parchear(id, cliente));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable final Long id) {
		clienteService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
