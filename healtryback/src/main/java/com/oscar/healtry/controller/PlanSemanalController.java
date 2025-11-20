package com.oscar.healtry.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.healtry.dto.dieta.PlanSemanalDTO;
import com.oscar.healtry.service.PlanSemanalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/planes-semanales")
@RequiredArgsConstructor
public class PlanSemanalController {

	private final PlanSemanalService planService;

	@PostMapping
	public ResponseEntity<PlanSemanalDTO> crear(@RequestBody final PlanSemanalDTO plan) {
		return ResponseEntity.ok(planService.crear(plan));
	}

	@GetMapping("/nutricionistas/{idNutrionista}")
	public ResponseEntity<List<PlanSemanalDTO>> listarPorNutricionista(@PathVariable final Long idNutrionista) {
		return ResponseEntity.ok(planService.listarPorNutricionista(idNutrionista));
	}

	@GetMapping("/actual/clientes/{idCliente}")
	public ResponseEntity<PlanSemanalDTO> obtenerActivaPorCliente(@PathVariable final Long idCliente) {
		return ResponseEntity.ok(planService.obtenerActualPorCliente(idCliente));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PlanSemanalDTO> editar(@PathVariable final Long id, @RequestBody final PlanSemanalDTO plan) {
		return ResponseEntity.ok(planService.editar(id, plan));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable final Long id) {
		planService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
