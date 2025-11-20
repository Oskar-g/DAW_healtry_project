package com.oscar.healtry.dto.admin;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
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
public class UsuarioCrearDTO {
	@NotBlank
	private String nombre;
	@NotBlank
	private String apellidos;
	@Email
	@NotBlank
	private String correo;
	@Nullable
	private String contrasena;
	@NotNull
	private Long idRol;
}
