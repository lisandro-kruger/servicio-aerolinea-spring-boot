package com.api.request;

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
public class PasajeRequest {
	
	private Long dni;
	private Long nro_vuelo;
	private Integer nro_asiento;

}
