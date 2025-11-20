package com.oscar.healtry_front.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

	private static final int CODE_SIZE = 8;
	private static final String READABLE_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
	private final Map<String, ResetInfo> codigos = new ConcurrentHashMap<>();

	public record ResetInfo(String codigo, LocalDateTime expiracion, Long idUsuario) {
	}

	public String generarCodigo(final String email, final Long idUsuario) {
		var codigo = generarCodigo();
		var duracion = LocalDateTime.now().plusHours(2);
		var info = new ResetInfo(codigo, duracion, idUsuario);

		codigos.put(email, info);
		return info.codigo();
	}

	public ResetInfo validarCodigo(final String email, final String codigo) {
		var info = codigos.get(email);

		if (info == null) {
			return null;
		}

		if (LocalDateTime.now().isAfter(info.expiracion())) {
			codigos.remove(email);
			return null;
		}

		if (!codigo.equals(info.codigo())) {
			return null;
		}

		return info;
	}

	public void limpiarCodigo(final String email) {
		codigos.remove(email);
	}

	public static String generarCodigo() {
		return RandomStringUtils.secure().next(CODE_SIZE, READABLE_CHARS);
	}
}
