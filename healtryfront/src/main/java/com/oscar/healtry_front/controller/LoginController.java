package com.oscar.healtry_front.controller;

import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

import com.oscar.healtry_front.dto.LoginRequestDTO;
import com.oscar.healtry_front.dto.LoginResponseDTO;
import com.oscar.healtry_front.dto.RespuestaGeneralDTO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Controller

@RequiredArgsConstructor
public class LoginController {

	private final WebClient apiWebClient;

	@GetMapping("/login")
	public String mostrarFormularioLogin(Model model) {
		model.addAttribute("login", new LoginRequestDTO());
		return "login";
	}

	@PostMapping("/login")
	public String procesarLogin(@ModelAttribute LoginRequestDTO request, BindingResult bindingResult, Model model,
			HttpSession session) {
		log.debug("ENTRADA procesarLogin({})", request);
		model.addAttribute("login", request);

		if (bindingResult.hasErrors()) {
			String errors = bindingResult.getFieldErrors()
					.stream()
					.map(e -> String.format("%s: %s", e.getField(), e.getDefaultMessage()))
					.collect(Collectors.joining(","));

			log.debug("SALIDA procesarLogin -> error de autenticación");
			model.addAttribute("error", errors);
			return "login";
		}

		try {

			ParameterizedTypeReference<RespuestaGeneralDTO<LoginResponseDTO>> elementTypeRef = new ParameterizedTypeReference<RespuestaGeneralDTO<LoginResponseDTO>>() {
			};

			RespuestaGeneralDTO<LoginResponseDTO> respuesta = apiWebClient.post()
					.uri("auth/login")
					.bodyValue(request)
					.retrieve()
					.onStatus(
							HttpStatusCode::isError,
							clientResponse -> clientResponse.bodyToMono(elementTypeRef)
									.flatMap(errorBody -> Mono.error(new RuntimeException(errorBody.getMessage()))))
					.bodyToMono(elementTypeRef)
					.block();

			if (respuesta != null && respuesta.isError()) {
				log.debug("SALIDA procesarLogin -> error de autenticación");
				model.addAttribute("error", respuesta.getMessage());
				return "login";
			}

			log.debug("Usuario autenticado{}", respuesta.getData());

			session.setAttribute("usuarioLogueado", respuesta.getData());

			log.debug("SALIDA procesarLogin({}) -> {}", request, respuesta);
			return "redirect:/";
		} catch (Exception e) {
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
		return "redirect:/login";
	}
}
