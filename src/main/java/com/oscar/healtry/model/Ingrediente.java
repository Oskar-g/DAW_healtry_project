package com.oscar.healtry.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "ingredientes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
public class Ingrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ingrediente")
	private Integer idIngrediente;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(name = "calorias_por_racion", precision = 6, scale = 2)
	private BigDecimal caloriasPorRacion;

	@Column(name = "gramos_por_racion", precision = 6, scale = 2)
	private BigDecimal gramosPorRacion;
}
