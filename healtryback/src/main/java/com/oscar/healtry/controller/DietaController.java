package com.oscar.healtry.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.healtry.dto.dieta.DietasClientesDTO;
import com.oscar.healtry.service.impl.DietaServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dietas")
@RequiredArgsConstructor
public class DietaController {

	private final DietaServiceImpl dietaService;

	@PostMapping
	public ResponseEntity<DietasClientesDTO> crear(@RequestBody final DietasClientesDTO dto) {
		var dieta = dietaService.crear(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(dieta);
	}

	@GetMapping("/nutricionistas/{idNutricionista}")
	public ResponseEntity<List<DietasClientesDTO>> obtenerPorNutricionista(@PathVariable final Long idNutricionista) {
		return ResponseEntity.ok(dietaService.obtenerPorNutricionista(idNutricionista));
	}

	@PutMapping("/{id}")
	public ResponseEntity<DietasClientesDTO> editar(@PathVariable final Long id,
			@RequestBody final DietasClientesDTO dto) {
		var dieta = dietaService.editar(id, dto);
		return ResponseEntity.ok(dieta);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable final Long id) {
		dietaService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
