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

import com.oscar.healtry.dto.ingrediente.IngredienteDTO;
import com.oscar.healtry.dto.ingrediente.IngredienteResumenDTO;
import com.oscar.healtry.service.IngredienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ingredientes")
@RequiredArgsConstructor
@Validated
public class IngredienteController {

    private final IngredienteService ingredienteService;

    /**
     * Crear un nuevo ingrediente
     */
    @PostMapping
    public ResponseEntity<IngredienteDTO> crearIngrediente(@RequestBody @Validated IngredienteDTO ingredienteDTO) {
        return ResponseEntity.ok(ingredienteService.crearIngrediente(ingredienteDTO));
    }

    /**
     * Actualizar un ingrediente existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<IngredienteDTO> actualizarIngrediente(
            @PathVariable Long id,
            @RequestBody @Validated IngredienteDTO ingredienteDTO) {
        return ResponseEntity.ok(ingredienteService.actualizarIngrediente(id, ingredienteDTO));
    }

    /**
     * Eliminar un ingrediente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarIngrediente(@PathVariable Long id) {
        ingredienteService.eliminarIngrediente(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Listar todos los ingredientes
     */
    @GetMapping
    public ResponseEntity<List<IngredienteResumenDTO>> listarIngredientes() {
        return ResponseEntity.ok(ingredienteService.listarIngredientes());
    }

    /**
     * Obtener detalle de un ingrediente
     */
    @GetMapping("/{id}")
    public ResponseEntity<IngredienteDTO> obtenerIngrediente(@PathVariable Long id) {
        return ResponseEntity.ok(ingredienteService.obtenerIngrediente(id));
    }
}
