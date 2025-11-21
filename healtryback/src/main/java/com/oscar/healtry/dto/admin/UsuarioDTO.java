package com.oscar.healtry.dto.admin;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.oscar.healtry.model.Cliente.Sexo;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
@Accessors(chain = true)
public class UsuarioDTO {
	private Long id;
	@NotBlank
	private String nombre;
	@NotBlank
	private String apellidos;
	@NotBlank
	private String correo;
	private String contrasena;
	private RolDTO rol;
	private Boolean activo;
	private NutricionistaInfo nutricionistaInfo;
	private ClienteInfo clienteInfo;

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@SuperBuilder(toBuilder = true)
	@Accessors(chain = true)
	public static class NutricionistaInfo {
		@Nonnull
		private String especialidad;

		@PositiveOrZero
		@Builder.Default
		private Integer experienciaAnios = 0;
	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@SuperBuilder(toBuilder = true)
	@Accessors(chain = true)
	public static class ClienteInfo {
		@Nonnull
		private LocalDate fechaNacimiento;
		@Nonnull
		private Sexo sexo;
		@Positive
		private BigDecimal alturaCm;
		@Positive
		private BigDecimal pesoKg;
		private Long idNutricionista;

		public int getEdad() {
			return LocalDate.now().getYear() - fechaNacimiento.getYear();
		}
	}
}
