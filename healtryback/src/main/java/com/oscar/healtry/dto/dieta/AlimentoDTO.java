package com.oscar.healtry.dto.dieta;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
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
public class AlimentoDTO {

	private Integer id;

	@NotBlank
	private String nombre;

	@PositiveOrZero
	private BigDecimal proteinas;

	@PositiveOrZero
	private BigDecimal grasas;

	@PositiveOrZero
	private BigDecimal carbohidratos;

	public BigDecimal getKcal() {
		if (proteinas == null || grasas == null || carbohidratos == null)
			return BigDecimal.ZERO;
		return proteinas.multiply(BigDecimal.valueOf(4))
				.add(grasas.multiply(BigDecimal.valueOf(9)))
				.add(carbohidratos.multiply(BigDecimal.valueOf(4)));
	}
}
