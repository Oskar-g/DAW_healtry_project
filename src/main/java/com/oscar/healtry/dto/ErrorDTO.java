package com.oscar.healtry.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
public class ErrorDTO {
	HttpStatus estado;
	String mensaje;

	public int getCodigo() {
		return estado.value();
	}
}
