package com.oscar.healtry.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietaClienteId implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "id_dieta")
	private Long idDieta;
	@Column(name = "id_cliente")
	private Long idCliente;
}