package com.oscar.healtry_front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NutriController extends AdviceController {

	@GetMapping("/nutri/comidas")
	public String comidas(Model model, HttpSession session) {
		return "nutri/comidas";
	}

	@GetMapping("/nutri/alimentos")
	public String alimentos(Model model, HttpSession session) {
		return "nutri/alimentos";
	}
	
	@GetMapping("/nutri/planes-semanales")
	public String planesSemanales(Model model, HttpSession session) {
		return "nutri/planes-semanales";
	}
	@GetMapping("/nutri/dietas")
	public String dietas(Model model, HttpSession session) {
		return "nutri/dietas";
	}
}
