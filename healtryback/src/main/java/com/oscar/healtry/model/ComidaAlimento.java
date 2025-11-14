package com.oscar.healtry.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "comidas_alimentos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
public class ComidaAlimento {

	@EmbeddedId
	private ComidaAlimentoId id;

	@ManyToOne
	@MapsId("comidaId")
	@JoinColumn(name = "id_comida")
	private Comida comida;

	@ManyToOne
	@MapsId("alimentoId")
	@JoinColumn(name = "id_alimento")
	private Alimento alimento;

	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal gramos;
}
