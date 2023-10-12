package com.api.response;

import org.springframework.hateoas.RepresentationModel;

import com.api.domain.PresupuestosEntregados;

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
public class CostoDePasajeResponse extends RepresentationModel<CostoDePasajeResponse>{
	
	private Double importe;
	
	public CostoDePasajeResponse(PresupuestosEntregados presupuestosEntregados) {
		this.importe = presupuestosEntregados.getImporte();
	}
	
}
