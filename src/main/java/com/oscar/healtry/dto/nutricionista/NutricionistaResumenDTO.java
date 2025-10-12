package com.oscar.healtry.dto.nutricionista;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
public class NutricionistaResumenDTO {
	private Long id;
	private String nombreCompleto;
	private List<String> clientes;
	private List<String> dietas;
	private List<String> planesEjercicio;
}
