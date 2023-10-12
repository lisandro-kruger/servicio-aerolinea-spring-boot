package com.api.excepcion;

public class Excepcion extends Exception {

	private static final long serialVersionUID = 3941221036411842318L;

	private int statusCode;
	private String mensaje;

	public Excepcion() {
		super();
	}

	public Excepcion(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public Excepcion(int statusCode, String mensaje) {
		super();
		this.statusCode = statusCode;
		this.mensaje = mensaje;
	}

	public Excepcion(String message, int i) {
		// TODO Auto-generated constructor stub
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
