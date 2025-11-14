package com.oscar.healtry.dto.dieta;

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
public class PlanDiaDTO {
    private Long id;
    private String dia;
    private String tipoComida;
    private ComidaDTO comida;
}
