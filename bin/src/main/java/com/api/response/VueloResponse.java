package com.api.response;

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
public class VueloResponse{

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

	public VueloResponse(Vuelo vuelo) {
		super();
		this.nro_vuelo = vuelo.getNro_vuelo();
		this.fecha_hora = vuelo.getFecha_hora();
		this.nro_fila = vuelo.getNro_fila();
		this.nro_asiento_fila = vuelo.getNro_asiento_fila();
		this.tipo = vuelo.getTipo_vuelo();
		this.destino = vuelo.getDestino();
		this.origen = vuelo.getOrigen();
		this.estado = vuelo.getEstado();
	}
}
