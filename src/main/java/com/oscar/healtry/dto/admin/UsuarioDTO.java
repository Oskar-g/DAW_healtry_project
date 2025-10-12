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
public class UsuarioDTO {
    private Integer idUsuario;
    private String nombre;
    private String correo;
    private String rol;
    private Boolean activo;
}
