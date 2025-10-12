package com.oscar.healtry.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "configuraciones")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
public class Configuracion {
	@Id
	@Column(length = 100)
	private String clave;

	@Column(columnDefinition = "text", nullable = false)
	private String valor;

	@Column(length = 50)
	private String tipo;

	@Column(columnDefinition = "text")
	private String descripcion;
}
