package com.oscar.healtry.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanEjercicioEjercicioId implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer plan;
	private Integer ejercicio;
	private String diaSemana;
}
