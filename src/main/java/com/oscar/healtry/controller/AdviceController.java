package com.oscar.healtry.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.oscar.healtry.dto.ErrorDTO;
import com.oscar.healtry.exception.ExcepcionGeneral;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Hidden
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class AdviceController {

	@ExceptionHandler(ExcepcionGeneral.class)
	public ResponseEntity<ErrorDTO> manejarExcepcionGeneral(ExcepcionGeneral ex) {
		log.error("manejarExcepcionGeneral", ex);
		return ResponseEntity.status(ex.getStatus())
				.body(ErrorDTO.builder().estado(ex.getStatus()).mensaje(ex.getMessage()).build());
	}
}
