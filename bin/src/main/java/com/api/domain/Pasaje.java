package com.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "pasaje")
public class Pasaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nro_pasaje;
	private Integer nro_asiento;
	private Double importe;
	@ManyToOne
	private Cliente cliente;
	@ManyToOne
	private Vuelo vuelo;
	

}
