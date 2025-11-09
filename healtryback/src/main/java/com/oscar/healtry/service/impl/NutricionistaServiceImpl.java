package com.oscar.healtry.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oscar.healtry.dto.nutricionista.NutricionistaPerfilDTO;
import com.oscar.healtry.dto.nutricionista.NutricionistaResumenDTO;
import com.oscar.healtry.model.Nutricionista;
import com.oscar.healtry.model.Usuario;
import com.oscar.healtry.repository.NutricionistaRepository;
import com.oscar.healtry.service.NutricionistaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NutricionistaServiceImpl implements NutricionistaService {

    private final NutricionistaRepository nutricionistaRepository;
    // TODO: inyectar repositorios de Cliente, Dieta y PlanEjercicio si se quiere obtener info completa del resumen

    @Override
    public NutricionistaPerfilDTO obtenerPerfil(Long nutricionistaId) {
        log.debug("ENTRADA obtenerPerfil({})", nutricionistaId);

        Nutricionista nutricionista = nutricionistaRepository.findById(nutricionistaId.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Nutricionista no encontrado: " + nutricionistaId));

        NutricionistaPerfilDTO response = mapEntityToPerfilDto(nutricionista);
        log.debug("SALIDA obtenerPerfil -> {}", response);
        return response;
    }

    @Override
    public NutricionistaPerfilDTO actualizarPerfil(Long nutricionistaId, NutricionistaPerfilDTO perfilDTO) {
        log.debug("ENTRADA actualizarPerfil(nutricionistaId={}, perfilDTO={})", nutricionistaId, perfilDTO);

        Nutricionista nutricionista = nutricionistaRepository.findById(nutricionistaId.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Nutricionista no encontrado: " + nutricionistaId));

        // Actualizar campos del usuario
        if (nutricionista.getUsuario() != null) {
            nutricionista.getUsuario()
                    .setNombre(perfilDTO.getNombre())
                    .setApellidos(perfilDTO.getApellido())
                    .setCorreo(perfilDTO.getEmail());
        }
        nutricionista.setEspecialidad(perfilDTO.getEspecialidad());

        nutricionista = nutricionistaRepository.save(nutricionista);

        NutricionistaPerfilDTO response = mapEntityToPerfilDto(nutricionista);
        log.debug("SALIDA actualizarPerfil -> {}", response);
        return response;
    }

    @Override
    public NutricionistaResumenDTO obtenerResumen(Long nutricionistaId) {
        log.debug("ENTRADA obtenerResumen({})", nutricionistaId);

        Nutricionista nutricionista = nutricionistaRepository.findById(nutricionistaId.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Nutricionista no encontrado: " + nutricionistaId));

        NutricionistaResumenDTO response = mapEntityToResumenDto(nutricionista);
        log.debug("SALIDA obtenerResumen -> {}", response);
        return response;
    }

    // =========================
    // Métodos de mapeo manual con builder
    // =========================

    private NutricionistaPerfilDTO mapEntityToPerfilDto(Nutricionista entidad) {
    	Usuario usuario = entidad.getUsuario();
        return NutricionistaPerfilDTO.builder()
                .id(entidad.getId() != null ? entidad.getId().longValue() : null)
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellidos())
                .email(usuario.getCorreo())
                .especialidad(entidad.getEspecialidad())
                .build();
    }

    private NutricionistaResumenDTO mapEntityToResumenDto(Nutricionista entidad) {
    	Usuario usuario = entidad.getUsuario();
        // TODO: agregar listas reales de clientes, dietas y planes
        List<String> clientes = new ArrayList<>();
        List<String> dietas = new ArrayList<>();
        List<String> planesEjercicio = new ArrayList<>();

        return NutricionistaResumenDTO.builder()
                .id(entidad.getId())
                .nombreCompleto(String.format("%s %s", usuario.getNombre(), usuario.getApellidos()))
                .clientes(clientes)
                .dietas(dietas)
                .planesEjercicio(planesEjercicio)
                .build();
    }
}
