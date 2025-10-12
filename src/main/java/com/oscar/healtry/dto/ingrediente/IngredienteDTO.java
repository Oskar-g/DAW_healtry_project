package com.oscar.healtry.dto.ingrediente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
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
public class IngredienteDTO {
	private Long id;

	@NotBlank
	private String nombre;

	@PositiveOrZero
	private Double calorias;

	@PositiveOrZero
	private Double proteinas;

	@PositiveOrZero
	private Double carbohidratos;

	@PositiveOrZero
	private Double grasas;
}
