package com.api.response;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import com.api.domain.Cliente;
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
public class ClienteResponse{
	
	private Long dni;
	private String apellido;
	private String nombre;
	private String domicilio;
	private String email;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fecha_nacimiento;
	private Long numero_pasaporte;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	LocalDate vecimiento_pasaporte;
	
	public ClienteResponse (Cliente cliente) {
		super();
		this.dni = cliente.getDni();
		this.apellido = cliente.getApellido();
		this.nombre = cliente.getNombre();
		this.domicilio = cliente.getDomicilio();
		this.fecha_nacimiento = cliente.getFecha_nacimiento();
		this.email = cliente.getEmail();
		this.numero_pasaporte = cliente.getNumero_pasaporte();
		this.vecimiento_pasaporte = cliente.getVecimiento_pasaporte();
	}
	
}
