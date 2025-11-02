package com.oscar.healtry.exception;

import java.text.MessageFormat;
import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class ExcepcionGeneral extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final HttpStatus status;

	public ExcepcionGeneral(String message) {
		super(message);
		this.status = HttpStatus.BAD_REQUEST;
	}

	public ExcepcionGeneral(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

	public Supplier<ExcepcionGeneral> throwing() {
		return () -> this;
	}

	public static ExcepcionGeneral de(String message, Object... arguments) {
		return new ExcepcionGeneral(MessageFormat.format(message, arguments));
	}

	public static ExcepcionGeneral of(HttpStatus status, String message, Object... arguments) {
		return new ExcepcionGeneral(status, MessageFormat.format(message, arguments));
	}
	
	public static Supplier<ExcepcionGeneral>  throwing(HttpStatus status, String message, Object... arguments) {
		return new ExcepcionGeneral(status, MessageFormat.format(message, arguments)).throwing();
	}
	
}
