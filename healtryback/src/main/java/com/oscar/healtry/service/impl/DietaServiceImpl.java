//package com.oscar.healtry.service.impl;
//
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.oscar.healtry.dto.dieta.ComidaDTO;
//import com.oscar.healtry.dto.dieta.DietaDTO;
//import com.oscar.healtry.dto.dieta.DietaResumenDTO;
//import com.oscar.healtry.dto.dieta.IngredienteEnComidaDTO;
//import com.oscar.healtry.model.Cliente;
//import com.oscar.healtry.model.ComidaDieta;
//import com.oscar.healtry.model.Dieta;
//import com.oscar.healtry.repository.ComidaRepository;
//import com.oscar.healtry.repository.DietaRepository;
//import com.oscar.healtry.service.DietaService;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class DietaServiceImpl implements DietaService {
//
//    private final DietaRepository dietaRepository;
//    private final ComidaRepository comidaRepository;
//    // TODO: inyectar ClienteRepository y NutricionistaRepository si necesitas validar/obtener entidades
//
//    @Override
//    public DietaDTO crearDieta(DietaDTO dietaDTO) {
//        log.debug("ENTRADA crearDieta({})", dietaDTO);
//
//        Dieta dieta = mapDtoToEntity(dietaDTO);
//        dieta = dietaRepository.save(dieta);
//
//        DietaDTO response = mapEntityToDto(dieta);
//        log.debug("SALIDA crearDieta -> {}", response);
//        return response;
//    }
//
//    @Override
//    public DietaDTO actualizarDieta(Long id, DietaDTO dietaDTO) {
//        log.debug("ENTRADA actualizarDieta(id={}, dietaDTO={})", id, dietaDTO);
//
//        Dieta dieta = dietaRepository.findById(id.intValue())
//                .orElseThrow(() -> new IllegalArgumentException("Dieta no encontrada: " + id));
//
//        dieta.setNombre(dietaDTO.getNombre());
//        dieta.setFechaInicio(dietaDTO.getFechaInicio());
//        dieta.setFechaFin(dietaDTO.getFechaFin());
//        // TODO: actualizar cliente/nutricionista si se requiere
//
//        dieta = dietaRepository.save(dieta);
//
//        DietaDTO response = mapEntityToDto(dieta);
//        log.debug("SALIDA actualizarDieta -> {}", response);
//        return response;
//    }
//
//    @Override
//    public void eliminarDieta(Long id) {
//        log.debug("ENTRADA eliminarDieta({})", id);
//        dietaRepository.deleteById(id.intValue());
//        log.debug("SALIDA eliminarDieta");
//    }
//
//    @Override
//    public void asignarDietaACliente(Long dietaId, Long clienteId) {
//        log.debug("ENTRADA asignarDietaACliente(dietaId={}, clienteId={})", dietaId, clienteId);
//
//        Dieta dieta = dietaRepository.findById(dietaId.intValue())
//                .orElseThrow(() -> new IllegalArgumentException("Dieta no encontrada: " + dietaId));
//        // TODO: obtener entidad Cliente real desde clienteId
//        Cliente cliente = new Cliente(); // placeholder
//        dieta.setCliente(cliente);
//
//        dietaRepository.save(dieta);
//
//        log.debug("SALIDA asignarDietaACliente");
//    }
//
//    @Override
//    public List<DietaResumenDTO> listarDietasPorCliente(Long clienteId) {
//        log.debug("ENTRADA listarDietasPorCliente({})", clienteId);
//
//        List<DietaResumenDTO> lista = dietaRepository.findByClienteId(clienteId.intValue()).stream()
//                .map(this::mapEntityToResumenDto)
//                .toList();
//
//        log.debug("SALIDA listarDietasPorCliente -> {}", lista);
//        return lista;
//    }
//
//    @Override
//    public ComidaDTO registrarComida(Long dietaId, ComidaDTO comidaDTO) {
//        log.debug("ENTRADA registrarComida(dietaId={}, comidaDTO={})", dietaId, comidaDTO);
//
//        Dieta dieta = dietaRepository.findById(dietaId.intValue())
//                .orElseThrow(() -> new IllegalArgumentException("Dieta no encontrada: " + dietaId));
//
//        ComidaDieta comida = mapDtoToEntity(comidaDTO, dieta);
//        comida = comidaRepository.save(comida);
//
//        ComidaDTO response = mapEntityToDto(comida);
//        log.debug("SALIDA registrarComida -> {}", response);
//        return response;
//    }
//
//
//    // =========================
//    // Métodos de mapeo manual
//    // =========================
//
//    private Dieta mapDtoToEntity(DietaDTO dto) {
//        return Dieta.builder()
//                .nombre(dto.getNombre())
//                .fechaInicio(dto.getFechaInicio())
//                .fechaFin(dto.getFechaFin())
//                // TODO: mapear cliente y nutricionista reales
//                .build();
//    }
//
//    private DietaDTO mapEntityToDto(Dieta entidad) {
//        return DietaDTO.builder()
//                .id(entidad.getId() != null ? entidad.getId().longValue() : null)
//                .nombre(entidad.getNombre())
//                .fechaInicio(entidad.getFechaInicio())
//                .fechaFin(entidad.getFechaFin())
//                .clienteId(entidad.getCliente() != null ? entidad.getCliente().getId().longValue() : null)
//                .build();
//    }
//
//    private DietaResumenDTO mapEntityToResumenDto(Dieta entidad) {
//        return DietaResumenDTO.builder()
//                .id(entidad.getId() != null ? entidad.getId().longValue() : null)
//                .nombre(entidad.getNombre())
//                .fechaInicio(entidad.getFechaInicio())
//                .fechaFin(entidad.getFechaFin())
//                .clienteId(entidad.getCliente() != null ? entidad.getCliente().getId().longValue() : null)
//                .build();
//    }
//
//    private ComidaDieta mapDtoToEntity(ComidaDTO comidaDto, Dieta dieta) {
//        return ComidaDieta.builder()
//                .nombre(comidaDto.getNombre())
//                .dieta(dieta)
//                .diaSemana(comidaDto.getDiaSemana())
//                .hora(comidaDto.getHoraDia())
//                .build();
//    }
//
//    private ComidaDTO mapEntityToDto(ComidaDieta entidad) {
//        return ComidaDTO.builder()
//                .id(entidad.getId() != null ? entidad.getId().longValue() : null)
//                .nombre(entidad.getNombre())
//                .diaSemana(entidad.getDiaSemana())
//                .horaDia(entidad.getHora())
//                .dietaId(entidad.getDieta() != null ? entidad.getDieta().getId().longValue() : null)
//                .build();
//    }
//
//}
