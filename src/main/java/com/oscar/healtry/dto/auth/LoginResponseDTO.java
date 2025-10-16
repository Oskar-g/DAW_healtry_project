package com.oscar.healtry.dto.auth;

import com.oscar.healtry.model.Usuario;

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
public class LoginResponseDTO {
	private String token;
	private String refreshToken;
	private Integer idUsuario;
	private String nombre;
	private String apellidos;
	private String correo;
	private String rol;
	
	public static LoginResponseDTO de(@NotNull Usuario usuario) {
		return LoginResponseDTO .builder()
				.idUsuario(usuario.getIdUsuario())
				.nombre(usuario.getNombre())
				.apellidos(usuario.getApellidos())
				.correo(usuario.getCorreo())
				.rol(usuario.getRol().getNombre())
				.build();
		
	}
}
