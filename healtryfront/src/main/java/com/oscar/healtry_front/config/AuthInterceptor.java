package com.oscar.healtry_front.config;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.oscar.healtry_front.dto.LoginResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	private static final String RUTA_RAIZ = "/";
	private static final Set<String> RUTAS_COMUNES_EXCLUIDAS = Set.of("/", "/logout");
	private static final Map<String, String> ROL_PREFIX = Map
			.of("ADMINISTRACION", "/admin/", "NUTRICIONISTA", "/nutri/", "USUARIO", "/user/");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);

		if (null == session || null == session.getAttribute("usuarioLogueado")) {
			response.sendRedirect("/login");
			return false;
		}

		String path = request.getRequestURI();
		if (RUTAS_COMUNES_EXCLUIDAS.stream().anyMatch(path::equals)) {
			return true;
		}

		LoginResponseDTO usuario = (LoginResponseDTO) session.getAttribute("usuarioLogueado");
		String rol = usuario.getRol();

		String permitido = ROL_PREFIX.get(rol);
		if (null == permitido || !path.startsWith(permitido)) {
			response.sendRedirect(RUTA_RAIZ);
			return false;
		}

		return true;
	}
}