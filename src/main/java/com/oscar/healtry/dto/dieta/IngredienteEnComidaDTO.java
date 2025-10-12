
package com.oscar.healtry.dto.dieta;

import jakarta.validation.constraints.NotNull;
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
public class IngredienteEnComidaDTO {
	@NotNull
	private Long ingredienteId;

	@NotNull
	private Double cantidad; // gramos o unidades
}
