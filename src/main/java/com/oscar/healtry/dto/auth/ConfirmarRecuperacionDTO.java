package com.oscar.healtry.dto.auth;

import jakarta.validation.constraints.Email;
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
public class ConfirmarRecuperacionDTO {
    @Email @NotBlank
    private String correo;

    @NotBlank
    private String codigo;

    @NotBlank
    private String nuevaContrasena;
}
