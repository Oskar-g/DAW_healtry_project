package com.oscar.healtry.dto.admin;

import jakarta.validation.constraints.NotBlank;
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
public class ConfiguracionCreateDTO {
    @NotBlank private String clave;
    @NotBlank private String valor;
    private String tipo;
    private String descripcion;
}
