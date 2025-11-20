package com.oscar.healtry.model;

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
@Table(name = "dietas_clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class DietaCliente {

	@EmbeddedId
	private DietaClienteId id;

	@ManyToOne
	@MapsId("idDieta")
	@JoinColumn(name = "id_dieta")
	private Dieta dieta;

	@ManyToOne
	@MapsId("idCliente")
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
}
