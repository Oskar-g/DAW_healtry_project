package com.oscar.healtry.controller;

import java.util.List;

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
import com.oscar.healtry.dto.dieta.DietaDTO;
import com.oscar.healtry.dto.dieta.DietaResumenDTO;
import com.oscar.healtry.dto.dieta.IngredienteEnComidaDTO;
import com.oscar.healtry.service.DietaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dietas")
@RequiredArgsConstructor
@Validated
public class DietaController {

    private final DietaService dietaService;

    /**
     * Crear nueva dieta
     */
    @PostMapping
    public ResponseEntity<DietaDTO> crearDieta(@RequestBody @Validated DietaDTO dietaDTO) {
        return ResponseEntity.ok(dietaService.crearDieta(dietaDTO));
    }

    /**
     * Actualizar dieta existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<DietaDTO> actualizarDieta(
            @PathVariable Long id,
            @RequestBody @Validated DietaDTO dietaDTO) {
        return ResponseEntity.ok(dietaService.actualizarDieta(id, dietaDTO));
    }

    /**
     * Eliminar dieta
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDieta(@PathVariable Long id) {
        dietaService.eliminarDieta(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Asignar dieta a un cliente
     */
    @PostMapping("/{id}/asignar/{clienteId}")
    public ResponseEntity<Void> asignarDieta(
            @PathVariable Long id,
            @PathVariable Long clienteId) {
        dietaService.asignarDietaACliente(id, clienteId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Listar dietas de un cliente
     */
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<DietaResumenDTO>> listarDietasPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(dietaService.listarDietasPorCliente(clienteId));
    }

    /**
     * Registrar comida en una dieta
     */
    @PostMapping("/{dietaId}/comidas")
    public ResponseEntity<ComidaDTO> registrarComida(
            @PathVariable Long dietaId,
            @RequestBody @Validated ComidaDTO comidaDTO) {
        return ResponseEntity.ok(dietaService.registrarComida(dietaId, comidaDTO));
    }

    /**
     * Agregar ingrediente a una comida
     */
    @PostMapping("/comidas/{comidaId}/ingredientes")
    public ResponseEntity<Void> agregarIngredienteAComida(
            @PathVariable Long comidaId,
            @RequestBody @Validated IngredienteEnComidaDTO ingredienteDTO) {
        dietaService.agregarIngredienteAComida(comidaId, ingredienteDTO);
        return ResponseEntity.noContent().build();
    }
}
