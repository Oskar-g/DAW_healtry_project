package com.oscar.healtry.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "alimentos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Accessors(chain = true)
public class Alimento {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, length = 100)
	    private String nombre;

	    @Column(precision = 6, scale = 2)
	    private BigDecimal proteinas;

	    @Column(precision = 6, scale = 2)
	    private BigDecimal grasas;

	    @Column(precision = 6, scale = 2)
	    private BigDecimal carbohidratos;

}
