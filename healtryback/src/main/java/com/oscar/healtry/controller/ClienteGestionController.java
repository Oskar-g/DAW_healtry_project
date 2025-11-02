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

import com.oscar.healtry.dto.cliente.ClienteDTO;
import com.oscar.healtry.dto.cliente.ClienteResumenDTO;
import com.oscar.healtry.service.ClienteGestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/nutricionista/clientes")
@RequiredArgsConstructor
@Validated
public class ClienteGestionController {

    private final ClienteGestionService clienteGestionService;

    /**
     * Dar de alta un nuevo cliente
     */
    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody @Validated ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteGestionService.crearCliente(clienteDTO));
    }

    /**
     * Actualizar los datos de un cliente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(
            @PathVariable Long id,
            @RequestBody @Validated ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteGestionService.actualizarCliente(id, clienteDTO));
    }

    /**
     * Eliminar un cliente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteGestionService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Listar todos los clientes asignados a un nutricionista
     */
    @GetMapping("/nutricionista/{nutricionistaId}")
    public ResponseEntity<List<ClienteResumenDTO>> listarClientes(@PathVariable Long nutricionistaId) {
        return ResponseEntity.ok(clienteGestionService.listarClientesPorNutricionista(nutricionistaId));
    }
}
