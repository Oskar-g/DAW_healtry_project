package com.oscar.healtry.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "planes_dias")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
public class PlanDia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_plan", nullable = false)
	private PlanSemanal plan;

	@Column(name = "dia", nullable = false, length = 20)
	private String dia;

	@Column(name = "tipo_comida", nullable = false, length = 30)
	private String tipoComida;

	@ManyToOne
	@JoinColumn(name = "id_comida")
	private Comida comida;
}
