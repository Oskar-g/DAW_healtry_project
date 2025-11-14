package com.oscar.healtry.dto.dieta;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class PlanSemanalDTO {
	private Long id;
	private String alias;
	private List<PlanDiaDTO> dias;
	private Integer nutriId;
}