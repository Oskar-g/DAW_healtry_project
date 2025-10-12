package com.oscar.healtry.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.healtry.dto.nutricionista.NutricionistaPerfilDTO;
import com.oscar.healtry.dto.nutricionista.NutricionistaResumenDTO;
import com.oscar.healtry.service.NutricionistaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/nutricionista")
@RequiredArgsConstructor
@Validated
public class NutricionistaController {

	private final NutricionistaService nutricionistaService;

	/**
	 * Obtiene el perfil del nutricionista
	 */
	@GetMapping("/perfil/{id}")
	public ResponseEntity<NutricionistaPerfilDTO> obtenerPerfil(@PathVariable Long id) {
		return ResponseEntity.ok(nutricionistaService.obtenerPerfil(id));
	}

	/**
	 * Actualiza el perfil del nutricionista
	 */
	@PutMapping("/perfil/{id}")
	public ResponseEntity<NutricionistaPerfilDTO> actualizarPerfil(@PathVariable Long id,
			@RequestBody @Validated NutricionistaPerfilDTO perfilDTO) {
		return ResponseEntity.ok(nutricionistaService.actualizarPerfil(id, perfilDTO));
	}

	/**
	 * Obtiene un resumen con clientes, dietas y planes
	 */
	@GetMapping("/resumen/{id}")
	public ResponseEntity<NutricionistaResumenDTO> obtenerResumen(@PathVariable Long id) {
		return ResponseEntity.ok(nutricionistaService.obtenerResumen(id));
	}
}
