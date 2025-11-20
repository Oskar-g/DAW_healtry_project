package com.oscar.healtry.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.healtry.dto.admin.UsuarioDTO;
import com.oscar.healtry.service.NutricionistaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/nutricionistas")
@RequiredArgsConstructor
@Validated
public class NutricionistaController {

	private final NutricionistaService nutricionistaService;

	@PostMapping
	public ResponseEntity<UsuarioDTO> crear(@RequestBody @Validated UsuarioDTO nutricionista) {
		return ResponseEntity.ok(nutricionistaService.crear(nutricionista));
	}

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar() {
		return ResponseEntity.ok(nutricionistaService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> obtenerPerfil(@PathVariable Long id) {
		return ResponseEntity.ok(nutricionistaService.obtener(id));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UsuarioDTO> oarchear(@PathVariable Long id,
			@RequestBody @Validated UsuarioDTO nutricionista) {
		return ResponseEntity.ok(nutricionistaService.parchear(id, nutricionista));
	}

}
