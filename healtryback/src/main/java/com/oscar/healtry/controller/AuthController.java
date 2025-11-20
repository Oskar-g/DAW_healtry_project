package com.oscar.healtry.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oscar.healtry.dto.RespuestaGeneralDTO;
import com.oscar.healtry.dto.auth.ConfirmarRecuperacionDTO;
import com.oscar.healtry.dto.auth.LoginRequestDTO;
import com.oscar.healtry.dto.auth.LoginResponseDTO;
import com.oscar.healtry.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<RespuestaGeneralDTO<LoginResponseDTO>> procesarLogin(
			@RequestBody final LoginRequestDTO request) {
		log.debug("ENTRADA procesarLogin({})", request);

		var data = authService.login(request);
		RespuestaGeneralDTO<LoginResponseDTO> response = RespuestaGeneralDTO.builder()
				.status(HttpStatus.OK)
				.build(data);

		log.debug("SALIDA procesarLogin({}) -> {}", request, response);
		return ResponseEntity.ok(response);
	}

	/**
	 * Cierra sesión del usuario actual (invalida token o sesión).
	 */
	@PostMapping("/logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout(@RequestHeader("Authorization") final String token) {
		authService.logout(token);
	}

	/**
	 * Confirma el código de recuperación y establece una nueva contraseña.
	 */
	@PostMapping("/confirmar-recuperar")
	@ResponseStatus(HttpStatus.OK)
	public void confirmarRecuperacion(@RequestBody @Validated final ConfirmarRecuperacionDTO request) {
		authService.confirmarCodigoYRestablecer(request);
	}

	/**
	 * Opción para refrescar token JWT o renovar sesión.
	 */
	@PostMapping("/refresh")
	public ResponseEntity<LoginResponseDTO> refresh(@RequestHeader("Authorization") final String refreshToken) {
		return ResponseEntity.ok(authService.refreshToken(refreshToken));
	}
}
