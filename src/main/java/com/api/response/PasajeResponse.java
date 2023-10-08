package com.api.response;

import org.springframework.hateoas.RepresentationModel;

import com.api.domain.Cliente;
import com.api.domain.Pasaje;
import com.api.domain.Vuelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasajeResponse extends RepresentationModel<PasajeResponse>{
	
	private Long nro_pasaje;
	private Integer nro_asiento;
	private Double importe;
	private Cliente cliente;
	private Vuelo vuelo;
	
	public PasajeResponse(Pasaje pasaje) {
		super();
		this.nro_pasaje = pasaje.getNro_pasaje();
		this.nro_asiento = pasaje.getNro_asiento();
		this.cliente = pasaje.getCliente();
		this.vuelo = pasaje.getVuelo();
	}
}
