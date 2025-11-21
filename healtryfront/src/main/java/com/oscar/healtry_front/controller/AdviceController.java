package com.oscar.healtry_front.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

public class AdviceController {

	@ModelAttribute("request")
	public void añadirRequestModel(Model model, HttpServletRequest request) {
		model.addAttribute("request", request);
	}
}
