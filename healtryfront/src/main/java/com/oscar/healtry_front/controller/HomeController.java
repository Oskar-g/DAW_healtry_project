package com.oscar.healtry_front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oscar.healtry_front.dto.LoginResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController extends AdviceController {

	@GetMapping({
			"/", "/index", "/home"
	})
	public String home(final HttpSession session) {
		var usuario = (LoginResponseDTO) session.getAttribute("usuarioLogueado");
		if (null == usuario) {
			return "redirect:/login";
		}

		return switch (usuario.getRol()) {
		case "ADMINISTRACION" -> "redirect:/admin/";
		case "NUTRICIONISTA" -> "redirect:/nutri/";
		case "CLIENTE" -> "redirect:/cliente/";
		default -> "redirect:/login";
		};
	}

	@GetMapping({
			"/admin/"
	})
	public String adminHome(final Model model, final HttpSession session, final HttpServletRequest request) {
		return "admin/home-admin";
	}

	@GetMapping({
			"/nutri/"
	})
	public String nutriHome(final HttpSession session) {
		return "nutri/home-nutri";
	}

	@GetMapping({
			"/cliente/"
	})
	public String clienteHome(final HttpSession session) {
		return "cliente/home-cliente";
	}
}
