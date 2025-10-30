package com.oscar.healtry.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oscar.healtry.dto.auth.ConfirmarRecuperacionDTO;
import com.oscar.healtry.dto.auth.LoginRequestDTO;
import com.oscar.healtry.dto.auth.LoginResponseDTO;
import com.oscar.healtry.dto.auth.RecuperarPasswordRequestDTO;
import com.oscar.healtry.model.Usuario;
import com.oscar.healtry.service.AuthService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.xml.bind.DataBindingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
//@Validated
public class AuthController {

	private final AuthService authService;

	@GetMapping("/login")
	public String mostrarFormularioLogin(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}

	@PostMapping("/login")
	public String procesarLogin(@ModelAttribute @Valid LoginRequestDTO request, BindingResult bindingResult,
			Model model, HttpSession session) {
		log.debug("ENTRADA procesarLogin({})", request);

		try {
			model.addAttribute("usuario", request);

			if (bindingResult.hasErrors()) {
				// TODO Aquí ya veré como gestiono los errores cuando lo maneje todo por REST
				String errors = bindingResult.getFieldErrors().stream()
						.map(e -> String.format("%s: %s", e.getField(), e.getDefaultMessage()))
						.collect(Collectors.joining(","));

				log.debug("Errores de validación: {}", errors);
				throw new DataBindingException(errors, null);
			}


			LoginResponseDTO response = authService.login(request);
			session.setAttribute("usuarioLogueado", response);
			log.debug("SALIDA procesarLogin({}) -> {}", request, response);
			return "redirect:/";

		} catch (DataBindingException | EntityNotFoundException e) {
			log.debug("SALIDA procesarLogin -> error de autenticación");
			model.addAttribute("error", e.getMessage());
			return "login";
		}
	}

	@GetMapping("/logout")
	public String cerrarSesion(HttpSession session) {
		log.debug("ENTRADA logout()");
		session.invalidate();
		log.debug("SALIDA logout() -> sesión invalidada");
		return "redirect:/auth/login";
	}

	/**
	 * Cierra sesión del usuario actual (invalida token o sesión).
	 */
	@PostMapping("/logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout(@RequestHeader("Authorization") String token) {
		authService.logout(token);
	}

	/**
	 * Envía un correo con código de recuperación de contraseña.
	 */
	@PostMapping("/recuperar")
	@ResponseStatus(HttpStatus.OK)
	public void solicitarRecuperacion(@RequestBody @Validated RecuperarPasswordRequestDTO request) {
		authService.generarCodigoRecuperacionPassword(request.getCorreo());
	}

	/**
	 * Confirma el código de recuperación y establece una nueva contraseña.
	 */
	@PostMapping("/confirmar-recuperar")
	@ResponseStatus(HttpStatus.OK)
	public void confirmarRecuperacion(@RequestBody @Validated ConfirmarRecuperacionDTO request) {
		authService.confirmarCodigoYRestablecer(request);
	}

	/**
	 * Opción para refrescar token JWT o renovar sesión.
	 */
	@PostMapping("/refresh")
	public ResponseEntity<LoginResponseDTO> refresh(@RequestHeader("Authorization") String refreshToken) {
		return ResponseEntity.ok(authService.refreshToken(refreshToken));
	}
}
