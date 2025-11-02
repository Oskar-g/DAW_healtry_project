package com.oscar.healtry.dto.nutricionista;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class NutricionistaPerfilDTO {
	private Long id;

	@NotBlank
	private String nombre;

	@NotBlank
	private String apellido;

	@Email
	@NotBlank
	private String email;

	private String especialidad;
}
