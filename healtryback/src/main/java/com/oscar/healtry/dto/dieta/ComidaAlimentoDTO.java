package com.oscar.healtry.dto.dieta;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ComidaAlimentoDTO extends AlimentoDTO {
	private BigDecimal gramos;
	
	public BigDecimal getKcal() {
		return super.getKcal().multiply(this.gramos).divide(BigDecimal.valueOf(100));
	}
}