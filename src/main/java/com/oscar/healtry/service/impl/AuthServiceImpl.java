package com.oscar.healtry.service.impl;

import org.springframework.stereotype.Service;

import com.oscar.healtry.dto.auth.ConfirmarRecuperacionDTO;
import com.oscar.healtry.dto.auth.LoginRequestDTO;
import com.oscar.healtry.dto.auth.LoginResponseDTO;
import com.oscar.healtry.model.Usuario;
import com.oscar.healtry.repository.UsuarioRepository;
import com.oscar.healtry.service.AuthService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UsuarioRepository usuarioRepository;

	@Override
	public LoginResponseDTO login(LoginRequestDTO request) {
		log.debug("ENTRADA login({})", request);

		LoginResponseDTO response = usuarioRepository.findByCorreo(request.getCorreo())
				.filter(existente -> existente.getContrasena().equals(request.getContrasena()))
				.filter(Usuario::getActivo).map(LoginResponseDTO::de)
				.orElseThrow(() -> new EntityNotFoundException("Usuario o contraseña incorrectos"));

		log.debug("SALIDA login -> {}", response);
		return response;
	}

	@Override
	public void logout(String token) {
		log.debug("ENTRADA logout({})", token);

		// TODO: invalidar token o sesión

		log.debug("SALIDA logout");
	}

	@Override
	public void enviarCodigoRecuperacion(String correo) {
		log.debug("ENTRADA enviarCodigoRecuperacion({})", correo);

		// TODO: generar código y enviarlo por correo

		log.debug("SALIDA enviarCodigoRecuperacion");
	}

	@Override
	public void confirmarCodigoYRestablecer(ConfirmarRecuperacionDTO request) {
		log.debug("ENTRADA confirmarCodigoYRestablecer({})", request);

		// TODO: validar código y actualizar contraseña

		log.debug("SALIDA confirmarCodigoYRestablecer");
	}

	@Override
	public LoginResponseDTO refreshToken(String refreshToken) {
		log.debug("ENTRADA refreshToken({})", refreshToken);

		// TODO: validar refresh token y generar nuevo JWT
		LoginResponseDTO response = new LoginResponseDTO();
		response.setToken("NUEVO_TOKEN_DE_PRUEBA");
		response.setRefreshToken("NUEVO_REFRESH_TOKEN_DE_PRUEBA");

		log.debug("SALIDA refreshToken -> {}", response);
		return response;
	}
}
