package com.oscar.healtry.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
public class Cliente {
	@Id
	@Column(name = "id")
	private Long id;

	@OneToOne
	@MapsId
	@JoinColumn(name = "id", referencedColumnName = "id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_nutricionista", nullable = true)
	private Nutricionista nutricionista;

	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;

	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@Column(name = "altura_cm", precision = 5, scale = 2)
	private BigDecimal alturaCm;

	@Column(name = "peso_kg", precision = 5, scale = 2)
	private BigDecimal pesoKg;

	public enum Sexo {
		H, M
	}
}
