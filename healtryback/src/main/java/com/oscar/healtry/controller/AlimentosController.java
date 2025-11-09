package com.oscar.healtry.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.healtry.dto.dieta.AlimentoDTO;
import com.oscar.healtry.service.AlimentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/alimentos")
@RequiredArgsConstructor
@Validated
public class AlimentosController {

	private final AlimentoService alimentoService;

	@RequestMapping(method = {
			RequestMethod.POST, RequestMethod.PUT
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AlimentoDTO> guardar(@RequestBody @Validated AlimentoDTO alimentoDTO) {
		return ResponseEntity.ok(alimentoService.guardar(alimentoDTO));
	}

	@GetMapping
	public ResponseEntity<List<AlimentoDTO>> listar() {
		return ResponseEntity.ok(alimentoService.listarTodos());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		alimentoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
