package com.oscar.healtry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.oscar.healtry.dto.auth.LoginResponseDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String dashboard(HttpSession session) {
    	LoginResponseDTO usuarioLogueado = (LoginResponseDTO) session.getAttribute("usuarioLogueado");
    	
        if (usuarioLogueado == null) {
            return "redirect:auth/login";
        }
        
        
        return "dashboard";
    }
}
