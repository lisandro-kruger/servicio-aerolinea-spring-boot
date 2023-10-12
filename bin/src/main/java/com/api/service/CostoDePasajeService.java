package com.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.dao.CostoDePasajeRepository;
import com.api.domain.PresupuestosEntregados;
import com.api.response.CotizacionDolar;
import com.api.response.TipoCambioDolar;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class CostoDePasajeService {

	@Autowired
	private CostoDePasajeRepository costoDePasajeRepository;

	private Map<String, Object> venta;

	/**
	 * Devuelve la lista de cotizaciones del día
	 * 
	 * @return Lista de cotizaciones
	 */
	public List<CotizacionDolar> getCotizaciones() {

		List<CotizacionDolar> lista = new ArrayList<CotizacionDolar>();

		ResponseEntity<TipoCambioDolar[]> resp = new RestTemplate()
				.getForEntity("https://www.dolarsi.com/api/api.php?type=valoresprincipales", TipoCambioDolar[].class);
		for (TipoCambioDolar tipoCambio : resp.getBody()) {
			CotizacionDolar cotizacion = tipoCambio.getCasa();

			for (JsonNode node : cotizacion.getVenta()) {
				venta = new ObjectMapper().convertValue(node, new TypeReference<Map<String, Object>>() {
				});
			}

			lista.add(cotizacion);
		}
		return lista;
	}

	public PresupuestosEntregados guardarPresupuestosEntregados(PresupuestosEntregados presupuestosEntregados) {

		return costoDePasajeRepository.save(presupuestosEntregados);
	}
}
