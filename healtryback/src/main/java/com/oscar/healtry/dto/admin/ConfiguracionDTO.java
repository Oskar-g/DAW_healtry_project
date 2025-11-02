package com.oscar.healtry.dto.admin;

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
public class ConfiguracionDTO {
    private String clave;
    private String valor;
    private String tipo;
    private String descripcion;
}
