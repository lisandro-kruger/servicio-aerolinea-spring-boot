package com.api.request;

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
public class ClienteRequest {
	
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
	
	public Cliente toEntidad() {		
		Cliente newCliente = new Cliente();
		newCliente.setDni(this.dni);
		newCliente.setApellido(this.apellido);
		newCliente.setNombre(this.nombre);
		newCliente.setDomicilio(this.domicilio);
		newCliente.setEmail(this.email);
		newCliente.setFecha_nacimiento(this.fecha_nacimiento);
		newCliente.setNumero_pasaporte(this.numero_pasaporte);
		newCliente.setVecimiento_pasaporte(this.vecimiento_pasaporte);
		
		return newCliente;
	}
}
