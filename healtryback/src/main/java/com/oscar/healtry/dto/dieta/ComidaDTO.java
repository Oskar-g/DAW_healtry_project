package com.oscar.healtry.dto.dieta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
public class ComidaDTO {

	private Long id;

	@NotBlank
	private String nombre;

	@NotEmpty
	@Builder.Default
	private List<ComidaAlimentoDTO> alimentos = new ArrayList<>();

	public BigDecimal getKcal() {
		return alimentos.stream()
				.filter(Objects::nonNull)
				.map(ComidaAlimentoDTO::getKcal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
