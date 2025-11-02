package com.oscar.healtry.service;

import java.util.List;

import com.oscar.healtry.dto.cliente.ClienteDTO;
import com.oscar.healtry.dto.cliente.ClienteResumenDTO;

public interface ClienteGestionService {
    ClienteDTO crearCliente(ClienteDTO clienteDTO);
    ClienteDTO actualizarCliente(Long clienteId, ClienteDTO clienteDTO);
    void eliminarCliente(Long clienteId);
    List<ClienteResumenDTO> listarClientesPorNutricionista(Long nutricionistaId);
}
