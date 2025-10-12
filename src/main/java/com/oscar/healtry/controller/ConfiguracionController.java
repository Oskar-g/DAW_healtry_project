package com.oscar.healtry.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	/** Listar todas las configuraciones */
	@GetMapping("/admin/configuraciones")
	public ResponseEntity<List<ConfiguracionDTO>> listarConfiguraciones() {
		return ResponseEntity.ok(configuracionService.listarTodasDTO());
	}

	/** Obtener una configuración por clave */
	@GetMapping("/admin/configuraciones/{clave}")
	public ResponseEntity<ConfiguracionDTO> obtenerConfiguracion(@PathVariable String clave) {
		return ResponseEntity.ok(configuracionService.obtenerPorClaveDTO(clave));
	}

	/** Crear o actualizar configuración */
	@PostMapping("/admin/configuraciones")
	@ResponseStatus(HttpStatus.CREATED)
	public ConfiguracionDTO guardarConfiguracion(@RequestBody @Validated ConfiguracionCreateDTO request) {
		return configuracionService.guardarDTO(request);
	}

	/** Eliminar configuración */
	@DeleteMapping("/admin/configuraciones/{clave}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarConfiguracion(@PathVariable String clave) {
		configuracionService.eliminarPorClave(clave);
	}
}
