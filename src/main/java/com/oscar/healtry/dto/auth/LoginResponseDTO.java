package com.oscar.healtry.dto.auth;

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
	private String token;
	private String refreshToken;
	private Integer idUsuario;
	private String nombre;
	private String rol;
}
