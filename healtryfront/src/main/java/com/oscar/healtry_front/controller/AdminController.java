package com.oscar.healtry_front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController extends AdviceController {

	@GetMapping("/admin/configuraciones")
	public String configuraciones(Model model, HttpSession session) {
		return "admin/configuraciones";
	}

	@GetMapping("/admin/usuarios")
	public String usuarios(Model model, HttpSession session) {
		return "admin/usuarios";
	}
}
