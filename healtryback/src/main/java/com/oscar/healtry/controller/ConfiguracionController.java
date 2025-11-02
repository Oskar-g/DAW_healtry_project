package com.oscar.healtry.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.healtry.dto.admin.ConfiguracionCreateDTO;
import com.oscar.healtry.dto.admin.ConfiguracionDTO;
import com.oscar.healtry.service.ConfiguracionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class ConfiguracionController {

	private final ConfiguracionService configuracionService;

	// TODO mirar si usar token para gestionar roles o hacer un filter o si hago
	// chequeo manual de rol de acceso
//	@RolesAllowed(value = {"ADMIN"})
	@GetMapping("/admin/configuraciones")
	public ResponseEntity<List<ConfiguracionDTO>> listarConfiguraciones() {
		List<ConfiguracionDTO> resultado = configuracionService.listarTodasDTO();
		return ResponseEntity.ok(resultado);
	}

	@GetMapping("/admin/configuraciones/{clave}")
	public ResponseEntity<ConfiguracionDTO> obtenerConfiguracion(@PathVariable String clave) {
		ConfiguracionDTO resultado = configuracionService.obtenerPorClaveDTO(clave);
		return ResponseEntity.ok(resultado);
	}

	@PostMapping("/admin/configuraciones")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ConfiguracionDTO> guardarConfiguracion(
			@RequestBody @Validated ConfiguracionCreateDTO request) {
		ConfiguracionDTO resultado = configuracionService.guardar(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
	}

	@PutMapping("/admin/configuraciones")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ConfiguracionDTO> editarConfiguracion(
			@RequestBody @Validated ConfiguracionCreateDTO request) {
		ConfiguracionDTO resultado = configuracionService.guardar(request);
		return ResponseEntity.ok(resultado);
	}

	@DeleteMapping("/admin/configuraciones/{clave}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarConfiguracion(@PathVariable String clave) {
		configuracionService.eliminarPorClave(clave);
	}
}
