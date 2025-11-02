package com.oscar.healtry_front.dto;

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
public class LoginResponseDTO {
	private Integer id;
	private String nombre;
	private String apellidos;
	private String correo;
	private String rol;
	private boolean activo;
}
