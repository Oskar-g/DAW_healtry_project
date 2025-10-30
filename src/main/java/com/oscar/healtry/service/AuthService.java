package com.oscar.healtry.service;

import com.oscar.healtry.dto.auth.ConfirmarRecuperacionDTO;
import com.oscar.healtry.dto.auth.LoginRequestDTO;
import com.oscar.healtry.dto.auth.LoginResponseDTO;

public interface AuthService {
	LoginResponseDTO login(LoginRequestDTO request);

	void logout(String token);

	/**
	 * Aquí se debería de enviar un correo con el código para autenticar pero como
	 * no voy a montar un servidor de correo pues me limitaré a devolver este código
	 * 
	 * @param correo correo que deseamos reestablecer
	 * @return Codigo generado 
	 */
	String generarCodigoRecuperacionPassword(String correo);

	void confirmarCodigoYRestablecer(ConfirmarRecuperacionDTO request);

	LoginResponseDTO refreshToken(String refreshToken);
}
