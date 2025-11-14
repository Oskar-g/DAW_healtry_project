package com.oscar.healtry.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<PlanSemanalDTO> guardar(@RequestBody PlanSemanalDTO plan) {
        return ResponseEntity.ok(planService.guardar(plan));
    }

    @GetMapping
    public ResponseEntity<List<PlanSemanalDTO>> listar() {
        return ResponseEntity.ok(planService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanSemanalDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(planService.obtener(id));
    }
}
