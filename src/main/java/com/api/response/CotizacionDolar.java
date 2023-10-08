package com.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class CotizacionDolar {
	
	@JsonProperty("compra")
	private String compra;
	@JsonProperty("venta")
    private JsonNode venta;
	@JsonProperty("agencia")
	private String agencia;
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("variacion")
	private String variacion;
	@JsonProperty("ventaCero")
	private String ventaCero;
	@JsonProperty("decimales")
	private String decimales;
	 @JsonProperty("mejor_compra")
	private String mejor_compra;
	 @JsonProperty("mejor_venta")
	private String mejor_venta;
	@JsonProperty("fecha")
	private String fecha;
	@JsonProperty("recorrido")
	private String recorrido;
	
	public String getCompra() {
		return compra;
	}
	public void setCompra(String compra) {
		this.compra = compra;
	}
	public JsonNode getVenta() {
		return venta;
	}
	public void setVenta(JsonNode venta) {
		this.venta = venta;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getVariacion() {
		return variacion;
	}
	public void setVariacion(String variacion) {
		this.variacion = variacion;
	}
	public String getVentaCero() {
		return ventaCero;
	}
	public void setVentaCero(String ventaCero) {
		this.ventaCero = ventaCero;
	}
	public String getDecimales() {
		return decimales;
	}
	public void setDecimales(String decimales) {
		this.decimales = decimales;
	}
	public String getMejor_compra() {
		return mejor_compra;
	}
	public void setMejor_compra(String mejor_compra) {
		this.mejor_compra = mejor_compra;
	}
	public String getMejor_venta() {
		return mejor_venta;
	}
	public void setMejor_venta(String mejor_venta) {
		this.mejor_venta = mejor_venta;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getRecorrido() {
		return recorrido;
	}
	public void setRecorrido(String recorrido) {
		this.recorrido = recorrido;
	}
}
