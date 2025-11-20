package com.oscar.healtry_front.config;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.oscar.healtry_front.dto.LoginResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	private static final String RUTA_RAIZ = "/";
	private static final Set<String> RUTAS_COMUNES_EXCLUIDAS = Set
			.of(RUTA_RAIZ, "/logout", "/renovar-codigo", "/renovar-pass");
	private static final Map<String, String> ROL_PREFIX = Map
			.of("ADMINISTRACION", "/admin/", "NUTRICIONISTA", "/nutri/", "CLIENTE", "/cliente/");

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		var session = request.getSession(false);
		var path = request.getRequestURI();

		if (RUTA_RAIZ.equals(path) && (null == session || null == session.getAttribute("usuarioLogueado"))) {
			response.sendRedirect("/login");
			return false;
		}

		if (RUTAS_COMUNES_EXCLUIDAS.stream().anyMatch(path::equals)) {
			return true;
		}

		var usuario = (LoginResponseDTO) session.getAttribute("usuarioLogueado");
		var rol = usuario.getRol();

		var permitido = ROL_PREFIX.get(rol);
		if (null == permitido || !path.startsWith(permitido)) {
			response.sendRedirect(RUTA_RAIZ);
			return false;
		}

		return true;
	}
}