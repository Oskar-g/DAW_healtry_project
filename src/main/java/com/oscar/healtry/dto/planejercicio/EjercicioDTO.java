package com.oscar.healtry.dto.planejercicio;

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
public class EjercicioDTO {
    private Long id;

    @NotBlank
    private String nombre;

    private String descripcion;

    private Integer duracionMinutos; // duración estimada
}
