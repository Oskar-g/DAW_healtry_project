package com.oscar.healtry.service;

import java.util.List;

import com.oscar.healtry.dto.dieta.ComidaDTO;
import com.oscar.healtry.dto.dieta.DietaDTO;
import com.oscar.healtry.dto.dieta.DietaResumenDTO;
import com.oscar.healtry.dto.dieta.IngredienteEnComidaDTO;

public interface DietaService {
    DietaDTO crearDieta(DietaDTO dietaDTO);
    DietaDTO actualizarDieta(Long id, DietaDTO dietaDTO);
    void eliminarDieta(Long id);
    void asignarDietaACliente(Long dietaId, Long clienteId);
    List<DietaResumenDTO> listarDietasPorCliente(Long clienteId);
    ComidaDTO registrarComida(Long dietaId, ComidaDTO comidaDTO);
    void agregarIngredienteAComida(Long comidaId, IngredienteEnComidaDTO ingredienteDTO);
}
