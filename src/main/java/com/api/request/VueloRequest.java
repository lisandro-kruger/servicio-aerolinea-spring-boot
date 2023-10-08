package com.api.request;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.api.domain.Vuelo;
import com.fasterxml.jackson.annotation.JsonFormat;

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
public class VueloRequest {

	private Long nro_vuelo;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime fecha_hora;
	private Integer nro_fila;
	private Integer nro_asiento_fila; // NRO DE ASIENTOS POR FILA
	private String tipo;
	private String destino;
	private String origen;
	private String estado;

	// ENTIDAD VUELO
	public Vuelo toEntidad() {

		Vuelo newVuelo = new Vuelo();
		newVuelo.setNro_vuelo(this.nro_vuelo);
		newVuelo.setFecha_hora(this.fecha_hora);
		newVuelo.setNro_fila(this.nro_fila);
		newVuelo.setNro_asiento_fila(this.nro_asiento_fila);
		newVuelo.setTipo_vuelo(this.tipo);
		newVuelo.setDestino(this.destino);
		newVuelo.setOrigen("sauce_viejo");
		newVuelo.setEstado("registrado");
		return newVuelo;
	}
}
