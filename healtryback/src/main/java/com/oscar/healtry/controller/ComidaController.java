package com.oscar.healtry.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.healtry.dto.dieta.ComidaDTO;
import com.oscar.healtry.service.ComidaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comidas")
@RequiredArgsConstructor
@Validated
public class ComidaController {

	private final ComidaService comidaService;

	@PostMapping
	public ResponseEntity<ComidaDTO> crear(@RequestBody @Validated ComidaDTO alimentoDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(comidaService.crear(alimentoDTO));
	}
	
	@GetMapping
	public ResponseEntity<List<ComidaDTO>> listar() {
		return ResponseEntity.ok(comidaService.listarTodos());
	}
	
	@PutMapping
	public ResponseEntity<ComidaDTO> modificar(@RequestBody @Validated ComidaDTO alimentoDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(comidaService.editar(alimentoDTO));
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		comidaService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
