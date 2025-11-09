package com.oscar.healtry.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.healtry.dto.admin.RolDTO;
import com.oscar.healtry.service.RolService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Validated
public class RolController {

	private final RolService rolService;

	@GetMapping
	public ResponseEntity<List<RolDTO>> listar() {
		return ResponseEntity.ok(rolService.listarTodos());
	}
}
