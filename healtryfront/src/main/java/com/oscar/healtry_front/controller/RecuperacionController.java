package com.oscar.healtry_front.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.oscar.healtry_front.dto.UsuarioDTO;
import com.oscar.healtry_front.service.EmailService;
import com.oscar.healtry_front.service.PasswordResetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RecuperacionController {

	private final WebClient apiWebClient;
	private final PasswordResetService resetService;
	private final EmailService emailService;

	/**
	 * Aquí se debería de enviar un correo con el código para autenticar pero como
	 * no voy a montar un servidor de correo pues me limitaré a devolver este código
	 * 
	 * @param email correo que deseamos reestablecer
	 */

	@PostMapping("/renovar-codigo")
	@ResponseBody
	public String generarCodigo(@RequestParam final String email) {

		var usuario = apiWebClient.get().uri("usuarios/email/" + email).retrieve().bodyToMono(UsuarioDTO.class).block();

		if (usuario == null) {
			return "KO";
		}

		var codigo = resetService.generarCodigo(email, usuario.getId());

		// enviar email
		emailService
				.enviarEmail(email, "Código para restablecer contraseña", "Tu código de recuperación es: " + codigo);

		return "OK";
	}

	@PostMapping("/renovar-pass")
	@ResponseBody
	public String cambiarPassword(@RequestParam final String email, @RequestParam final String codigo,
			@RequestParam final String nuevaPass) {

		var info = resetService.validarCodigo(email, codigo);

		if (info == null) {
			return "Código incorrecto o caducado.";
		}

		var dto = new UsuarioDTO();
		dto.setId(info.idUsuario());
		dto.setContrasena(nuevaPass);

		apiWebClient.patch()
				.uri("usuarios/" + info.idUsuario())
				.bodyValue(dto)
				.retrieve()
				.bodyToMono(Void.class)
				.block();

		resetService.limpiarCodigo(email);
		return "OK";
	}
}
