package com.oscar.healtry_front.dto;

import jakarta.validation.constraints.NotBlank;
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
public class UsuarioDTO {
	private Long id;
	@NotBlank
	private String nombre;
	@NotBlank
	private String apellidos;
	@NotBlank
	private String correo;
	private String contrasena;
	private Boolean activo;
}
