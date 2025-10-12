package com.oscar.healtry.dto.dieta;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
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
public class DietaDTO {
	private Long id;

	@NotBlank
	private String nombre;

	@NotNull
	private LocalDate fechaInicio;

	@NotNull
	private LocalDate fechaFin;

	private Long clienteId; // Cliente al que se asigna la dieta
}
