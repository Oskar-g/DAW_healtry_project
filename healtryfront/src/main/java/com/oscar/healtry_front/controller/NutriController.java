package com.oscar.healtry_front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NutriController extends AdviceController {

	@GetMapping("/nutri/clientes")
	public String clientes(final Model model, final HttpSession session) {
		return "nutri/clientes";
	}

	@GetMapping("/nutri/comidas")
	public String comidas(final Model model, final HttpSession session) {
		return "nutri/comidas";
	}

	@GetMapping("/nutri/alimentos")
	public String alimentos(final Model model, final HttpSession session) {
		return "nutri/alimentos";
	}

	@GetMapping("/nutri/planes-semanales")
	public String planesSemanales(final Model model, final HttpSession session) {
		return "nutri/planes-semanales";
	}

	@GetMapping("/nutri/dietas")
	public String dietas(final Model model, final HttpSession session) {
		return "nutri/dietas";
	}
}
