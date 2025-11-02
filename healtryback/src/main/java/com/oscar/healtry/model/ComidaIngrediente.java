package com.oscar.healtry.model;

import java.math.BigDecimal;

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
@Table(name = "comidas_ingredientes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
@IdClass(ComidaIngredienteId.class)
public class ComidaIngrediente {

	@Id
	@ManyToOne
	@JoinColumn(name = "id_comida")
	private Comida comida;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_ingrediente")
	private Ingrediente ingrediente;

	@Column(precision = 6, scale = 2)
	private BigDecimal cantidad;
}
