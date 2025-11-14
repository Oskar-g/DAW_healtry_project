//package com.oscar.healtry.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.oscar.healtry.dto.planejercicio.EjercicioDTO;
//import com.oscar.healtry.dto.planejercicio.PlanEjercicioDTO;
//import com.oscar.healtry.service.PlanEjercicioService;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/planes-ejercicio")
//@RequiredArgsConstructor
//@Validated
//public class PlanEjercicioController {
//
//	private final PlanEjercicioService planEjercicioService;
//
//	/**
//	 * Crear nuevo plan de ejercicio
//	 */
//	@PostMapping
//	public ResponseEntity<PlanEjercicioDTO> crearPlan(@RequestBody @Validated PlanEjercicioDTO planDTO) {
//		return ResponseEntity.ok(planEjercicioService.crearPlan(planDTO));
//	}
//
//	/**
//	 * Actualizar plan existente
//	 */
//	@PutMapping("/{id}")
//	public ResponseEntity<PlanEjercicioDTO> actualizarPlan(@PathVariable Long id,
//			@RequestBody @Validated PlanEjercicioDTO planDTO) {
//		return ResponseEntity.ok(planEjercicioService.actualizarPlan(id, planDTO));
//	}
//
//	/**
//	 * Eliminar plan
//	 */
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> eliminarPlan(@PathVariable Long id) {
//		planEjercicioService.eliminarPlan(id);
//		return ResponseEntity.noContent().build();
//	}
//
//	/**
//	 * Asignar ejercicio a un plan
//	 */
//	@PostMapping("/{planId}/ejercicios")
//	public ResponseEntity<Void> asignarEjercicio(@PathVariable Long planId,
//			@RequestBody @Validated EjercicioDTO ejercicioDTO) {
//		planEjercicioService.asignarEjercicioAPlan(planId, ejercicioDTO);
//		return ResponseEntity.noContent().build();
//	}
//
////	/**
////	 * Listar planes de un cliente
////	 */
////	@GetMapping("/cliente/{clienteId}")
////	public ResponseEntity<List<PlanResumenDTO>> listarPlanesPorCliente(@PathVariable Long clienteId) {
////		return ResponseEntity.ok(planEjercicioService.listarPlanesPorCliente(clienteId));
////	}
////
////	/**
////	 * Listar planes de un nutricionista
////	 */
////	@GetMapping("/nutricionista/{nutricionistaId}")
////	public ResponseEntity<List<PlanResumenDTO>> listarPlanesPorNutricionista(@PathVariable Long nutricionistaId) {
////		return ResponseEntity.ok(planEjercicioService.listarPlanesPorNutricionista(nutricionistaId));
////	}
//}
