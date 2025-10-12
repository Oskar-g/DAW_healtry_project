package com.oscar.healtry.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oscar.healtry.dto.ingrediente.IngredienteDTO;
import com.oscar.healtry.dto.ingrediente.IngredienteResumenDTO;
import com.oscar.healtry.model.Ingrediente;
import com.oscar.healtry.repository.IngredienteRepository;
import com.oscar.healtry.service.IngredienteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredienteServiceImpl implements IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    @Override
    public IngredienteDTO crearIngrediente(IngredienteDTO ingredienteDTO) {
        log.debug("ENTRADA crearIngrediente({})", ingredienteDTO);

        Ingrediente ingrediente = mapDtoToEntity(ingredienteDTO);
        ingrediente = ingredienteRepository.save(ingrediente);

        IngredienteDTO response = mapEntityToDto(ingrediente);
        log.debug("SALIDA crearIngrediente -> {}", response);
        return response;
    }

    @Override
    public IngredienteDTO actualizarIngrediente(Long id, IngredienteDTO ingredienteDTO) {
        log.debug("ENTRADA actualizarIngrediente(id={}, ingredienteDTO={})", id, ingredienteDTO);

        Ingrediente ingrediente = ingredienteRepository.findById(id.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Ingrediente no encontrado: " + id));

        ingrediente.setNombre(ingredienteDTO.getNombre());
        ingrediente.setCaloriasPorRacion(ingredienteDTO.getCalorias() != null ? BigDecimal.valueOf(ingredienteDTO.getCalorias()) : null);
        ingrediente.setGramosPorRacion(
                ingredienteDTO.getProteinas() != null ? BigDecimal.valueOf(ingredienteDTO.getProteinas()) : null
        ); 
        // TODO: mapear carbohidratos y grasas si están en la entidad

        ingrediente = ingredienteRepository.save(ingrediente);

        IngredienteDTO response = mapEntityToDto(ingrediente);
        log.debug("SALIDA actualizarIngrediente -> {}", response);
        return response;
    }

    @Override
    public void eliminarIngrediente(Long id) {
        log.debug("ENTRADA eliminarIngrediente({})", id);
        ingredienteRepository.deleteById(id.intValue());
        log.debug("SALIDA eliminarIngrediente");
    }

    @Override
    public List<IngredienteResumenDTO> listarIngredientes() {
        log.debug("ENTRADA listarIngredientes()");

        List<IngredienteResumenDTO> lista = ingredienteRepository.findAll().stream()
                .map(this::mapEntityToResumenDto)
                .toList();

        log.debug("SALIDA listarIngredientes -> {}", lista);
        return lista;
    }

    @Override
    public IngredienteDTO obtenerIngrediente(Long id) {
        log.debug("ENTRADA obtenerIngrediente({})", id);

        Ingrediente ingrediente = ingredienteRepository.findById(id.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Ingrediente no encontrado: " + id));

        IngredienteDTO response = mapEntityToDto(ingrediente);
        log.debug("SALIDA obtenerIngrediente -> {}", response);
        return response;
    }

    // =========================
    // Métodos de mapeo manual con builder
    // =========================

    private Ingrediente mapDtoToEntity(IngredienteDTO dto) {
        return Ingrediente.builder()
                .nombre(dto.getNombre())
                .caloriasPorRacion(dto.getCalorias() != null ? BigDecimal.valueOf(dto.getCalorias()) : null)
                .gramosPorRacion(dto.getProteinas() != null ? BigDecimal.valueOf(dto.getProteinas()) : null)
                // TODO: mapear carbohidratos y grasas
                .build();
    }

    private IngredienteDTO mapEntityToDto(Ingrediente entity) {
        return IngredienteDTO.builder()
                .id(entity.getIdIngrediente() != null ? entity.getIdIngrediente().longValue() : null)
                .nombre(entity.getNombre())
                .calorias(entity.getCaloriasPorRacion() != null ? entity.getCaloriasPorRacion().doubleValue() : null)
                .proteinas(entity.getGramosPorRacion() != null ? entity.getGramosPorRacion().doubleValue() : null)
                // TODO: mapear carbohidratos y grasas
                .build();
    }

    private IngredienteResumenDTO mapEntityToResumenDto(Ingrediente entity) {
        return IngredienteResumenDTO.builder()
                .id(entity.getIdIngrediente() != null ? entity.getIdIngrediente().longValue() : null)
                .nombre(entity.getNombre())
                .build();
    }
}
