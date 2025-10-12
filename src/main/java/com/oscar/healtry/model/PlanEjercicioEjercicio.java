package com.oscar.healtry.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "planes_ejercicio_ejercicios")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
@IdClass(PlanEjercicioEjercicioId.class)
public class PlanEjercicioEjercicio {
	@Id
	@ManyToOne
	@JoinColumn(name = "id_plan")
	private PlanEjercicio plan;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_ejercicio")
	private Ejercicio ejercicio;

	@Id
	@Column(name = "dia_semana", length = 15)
	private String diaSemana;

	@Column(name = "orden")
	private Integer orden;
}
