package com.oscar.healtry_front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oscar.healtry_front.dto.LoginResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController extends AdviceController{

	@GetMapping({
			"/", "/index", "/home"
	})
	public String home(HttpSession session) {
		LoginResponseDTO usuario = (LoginResponseDTO) session.getAttribute("usuarioLogueado");
		if (null == usuario) {
			return "redirect:/login";
		}

		return switch (usuario.getRol()) {
		case "ADMINISTRACION" -> "redirect:/admin/";
		case "NUTRICIONISTA" -> "redirect:/nutri/";
		case "USUARIO" -> "redirect:/user/";
		default -> "redirect:/login";
		};
	}

	@GetMapping({
			"/admin/"
	})
	public String adminHome(Model model, HttpSession session, HttpServletRequest request) {
	    model.addAttribute("title", "Healtry - Administración:Configuracion");
//	    model.addAttribute("requestURI", request.getRequestURI());
	    return "admin";
	}

	@GetMapping({
			"/nutri/"
	})
	public String nutriHome(HttpSession session) {
		return "nutri";
	}

	@GetMapping({
			"/user/"
	})
	public String userHome(HttpSession session) {
		return "user";
	}
}
