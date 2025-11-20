package com.oscar.healtry.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
public class ComidaAlimentoId implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long idComida;
	private Long idAlimento;
}
