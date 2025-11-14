//package com.oscar.healtry.service.impl;
//
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.oscar.healtry.dto.planejercicio.EjercicioDTO;
//import com.oscar.healtry.dto.planejercicio.PlanEjercicioDTO;
//import com.oscar.healtry.dto.planejercicio.PlanResumenDTO;
//import com.oscar.healtry.model.Cliente;
//import com.oscar.healtry.model.PlanEjercicio;
//import com.oscar.healtry.repository.ClienteRepository;
//import com.oscar.healtry.repository.PlanEjercicioRepository;
//import com.oscar.healtry.service.PlanEjercicioService;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class PlanEjercicioServiceImpl implements PlanEjercicioService {
//
//    private final PlanEjercicioRepository planEjercicioRepository;
//    private final ClienteRepository clienteRepository;
//
//    @Override
//    public PlanEjercicioDTO crearPlan(PlanEjercicioDTO planDTO) {
//        log.debug("ENTRADA crearPlan({})", planDTO);
//
//        PlanEjercicio plan = mapDtoToEntity(planDTO);
//        plan = planEjercicioRepository.save(plan);
//
//        PlanEjercicioDTO response = mapEntityToDto(plan);
//        log.debug("SALIDA crearPlan -> {}", response);
//        return response;
//    }
//
//    @Override
//    public PlanEjercicioDTO actualizarPlan(Long id, PlanEjercicioDTO planDTO) {
//        log.debug("ENTRADA actualizarPlan(id={}, planDTO={})", id, planDTO);
//
//        PlanEjercicio plan = planEjercicioRepository.findById(id.intValue())
//                .orElseThrow(() -> new IllegalArgumentException("Plan no encontrado: " + id));
//
//        plan.setFechaInicio(planDTO.getFechaInicio());
//        plan.setFechaFin(planDTO.getFechaFin());
//        // TODO: actualizar cliente si se proporciona un clienteId diferente
//        plan = planEjercicioRepository.save(plan);
//
//        PlanEjercicioDTO response = mapEntityToDto(plan);
//        log.debug("SALIDA actualizarPlan -> {}", response);
//        return response;
//    }
//
//    @Override
//    public void eliminarPlan(Long id) {
//        log.debug("ENTRADA eliminarPlan({})", id);
//        planEjercicioRepository.deleteById(id.intValue());
//        log.debug("SALIDA eliminarPlan");
//    }
//
//    @Override
//    public void asignarEjercicioAPlan(Long planId, EjercicioDTO ejercicioDTO) {
//        log.debug("ENTRADA asignarEjercicioAPlan(planId={}, ejercicioDTO={})", planId, ejercicioDTO);
//        // TODO: implementar asignación real de ejercicio a plan
//        log.debug("SALIDA asignarEjercicioAPlan");
//    }
//
//    @Override
//    public List<PlanResumenDTO> listarPlanesPorCliente(Long clienteId) {
//        log.debug("ENTRADA listarPlanesPorCliente({})", clienteId);
//
//        List<PlanResumenDTO> lista = planEjercicioRepository.findByClienteId(clienteId.intValue()).stream()
//                .map(this::mapEntityToResumenDto)
//                .toList();
//
//        log.debug("SALIDA listarPlanesPorCliente -> {}", lista);
//        return lista;
//    }
//
//    @Override
//    public List<PlanResumenDTO> listarPlanesPorNutricionista(Long nutricionistaId) {
//        log.debug("ENTRADA listarPlanesPorNutricionista({})", nutricionistaId);
//
//        // TODO: filtrar planes por nutricionista si existe relación
//        List<PlanResumenDTO> lista = planEjercicioRepository.findAll().stream()
//                .map(this::mapEntityToResumenDto)
//                .toList();
//
//        log.debug("SALIDA listarPlanesPorNutricionista -> {}", lista);
//        return lista;
//    }
//
//    // =========================
//    // Métodos de mapeo manual con builder
//    // =========================
//
//    private PlanEjercicio mapDtoToEntity(PlanEjercicioDTO dto) {
//        Cliente cliente = null;
//        if (dto.getClienteId() != null) {
//            cliente = clienteRepository.findById(dto.getClienteId().intValue())
//                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + dto.getClienteId()));
//        }
//
//        return PlanEjercicio.builder()
//                .cliente(cliente)
//                .fechaInicio(dto.getFechaInicio())
//                .fechaFin(dto.getFechaFin())
//                .build();
//    }
//
//    private PlanEjercicioDTO mapEntityToDto(PlanEjercicio entidad) {
//        return PlanEjercicioDTO.builder()
//                .id(entidad.getId() != null ? entidad.getId().longValue() : null)
//                .nombre(entidad.getCliente() != null ? "Plan de " + entidad.getCliente().getUsuario().getNombre() : null) // placeholder
//                .fechaInicio(entidad.getFechaInicio())
//                .fechaFin(entidad.getFechaFin())
//                .clienteId(entidad.getCliente() != null ? entidad.getCliente().getId().longValue() : null)
//                .build();
//    }
//
//    private PlanResumenDTO mapEntityToResumenDto(PlanEjercicio entidad) {
//        return PlanResumenDTO.builder()
//                .id(entidad.getId() != null ? entidad.getId().longValue() : null)
//                .nombre(entidad.getCliente() != null ? "Plan de " + entidad.getCliente().getUsuario().getNombre() : null) // placeholder
//                .fechaInicio(entidad.getFechaInicio())
//                .fechaFin(entidad.getFechaFin())
//                .clienteId(entidad.getCliente() != null ? entidad.getCliente().getId().longValue() : null)
//                .build();
//    }
//}
