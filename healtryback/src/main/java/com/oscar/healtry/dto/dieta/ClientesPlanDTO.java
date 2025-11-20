package com.oscar.healtry.dto.dieta;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class ClientesPlanDTO {
	private Long idDieta;
	private Long idNutricionista;
	private Set<Long> clientes;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
}