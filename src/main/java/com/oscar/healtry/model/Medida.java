package com.oscar.healtry.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name = "medidas")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
public class Medida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_medida")
	private Integer idMedida;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@Column(nullable = false)
	private LocalDate fecha;

	@Column(precision = 5, scale = 2)
	private BigDecimal peso;

	@Column(name = "cintura_cm", precision = 5, scale = 2)
	private BigDecimal cinturaCm;

	@Column(name = "cadera_cm", precision = 5, scale = 2)
	private BigDecimal caderaCm;

	@Column(precision = 4, scale = 2)
	private BigDecimal imc;
}
