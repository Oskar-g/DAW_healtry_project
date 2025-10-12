package com.oscar.healtry.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "nutricionistas")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
public class Nutricionista {
	@Id
	@Column(name = "id_nutricionista")
	private Long idNutricionista;

	@OneToOne
	@JoinColumn(name = "id_nutricionista", referencedColumnName = "id_usuario")
	private Usuario usuario;

	@Column(length = 100)
	private String especialidad;

	@Column(name = "experiencia_anios")
	private Integer experienciaAnios;
}
