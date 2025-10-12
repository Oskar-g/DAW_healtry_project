package com.oscar.healtry.dto.dieta;

import java.time.DayOfWeek;
import java.time.LocalTime;

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
public class ComidaDTO {
	private Long id;

	@NotBlank
	private String nombre;

	private DayOfWeek diaSemana;
	@NotNull
	private LocalTime horaDia;

	private Long dietaId; // Dieta a la que pertenece
}
