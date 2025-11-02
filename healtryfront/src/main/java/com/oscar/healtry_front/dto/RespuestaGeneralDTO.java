package com.oscar.healtry_front.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class RespuestaGeneralDTO<T> {
	private HttpStatus status;
	private String message;
	private T data;

	public int getCodigo() {
		return status.value();
	}

	public boolean isError() {
		return status.isError();
	}
	
    public static <T> Builder builder() {
        return new Builder();
    }

    public static class Builder{
        private HttpStatus status;
        private String message;

        public Builder status(HttpStatus estado) {
            this.status = estado;
            return this;
        }

        public Builder message(String mensaje) {
            this.message = mensaje;
            return this;
        }


        public <T> RespuestaGeneralDTO<T> build() {
            return build(null);
        }
        
        public <T> RespuestaGeneralDTO<T> build(T data) {        	
            return new RespuestaGeneralDTO<>(status, message, data);
        }
    }
}
