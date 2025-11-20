package com.oscar.healtry.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.oscar.healtry.dto.dieta.DietasClientesDTO;
import com.oscar.healtry.model.Cliente;
import com.oscar.healtry.model.Dieta;
import com.oscar.healtry.model.DietaCliente;

public interface DietaService {

	DietasClientesDTO crear(DietasClientesDTO dto);

	List<DietasClientesDTO> obtenerPorNutricionista(Long idDieta);

	DietasClientesDTO editar(Long idDieta, DietasClientesDTO dto);

	void eliminar(Long idDieta);

	public static DietasClientesDTO mapToDto(final Dieta entity) {
		if (null == entity) {
			return null;
		}

		
		Set<Long> clientes = entity.getDietaClientes()
				.stream()
				.map(DietaCliente::getCliente)
				.map(Cliente::getId)
				.collect(Collectors.toSet());
		
		return DietasClientesDTO.builder()
				.id(entity.getId())
				.fechaFin(entity.getFechaFin())
				.fechaInicio(entity.getFechaInicio())
				.idPlan(entity.getPlan().getId())
				.idNutricionista(entity.getPlan().getNutricionista().getId())
				.clientes(clientes).build();
	}

}
