package com.oscar.healtry.service;

import java.util.List;

import com.oscar.healtry.dto.ingrediente.IngredienteDTO;
import com.oscar.healtry.dto.ingrediente.IngredienteResumenDTO;

public interface IngredienteService {
    IngredienteDTO crearIngrediente(IngredienteDTO ingredienteDTO);
    IngredienteDTO actualizarIngrediente(Long id, IngredienteDTO ingredienteDTO);
    void eliminarIngrediente(Long id);
    List<IngredienteResumenDTO> listarIngredientes();
    IngredienteDTO obtenerIngrediente(Long id);
}
