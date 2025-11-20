package com.oscar.healtry.service;

import com.oscar.healtry.dto.auth.ConfirmarRecuperacionDTO;
import com.oscar.healtry.dto.auth.LoginRequestDTO;
import com.oscar.healtry.dto.auth.LoginResponseDTO;

public interface AuthService {
	LoginResponseDTO login(LoginRequestDTO request);

	void logout(String token);

	void confirmarCodigoYRestablecer(ConfirmarRecuperacionDTO request);

	LoginResponseDTO refreshToken(String refreshToken);
}
