package com.api.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.Cliente;
import com.api.domain.PresupuestosEntregados;
import com.api.domain.Vuelo;
import com.api.excepcion.Excepcion;
import com.api.request.CostoDePasajeRequest;
import com.api.response.CostoDePasajeResponse;
import com.api.response.CotizacionDolar;
import com.api.rest.errors.ErrorHandler;
import com.api.service.ClienteService;
import com.api.service.CostoDePasajeService;
import com.api.service.VueloService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/valor")
public class CostoDePasajeController {

	@Autowired
	private CostoDePasajeService costoDePasajeService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private VueloService vueloService;

	@GetMapping(value = "/dolar", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<CotizacionDolar>> getCotizacionActual() throws Excepcion {
		List<CotizacionDolar> cotizaciones = costoDePasajeService.getCotizaciones();
		if (cotizaciones.size() > 0) {
			return new ResponseEntity<List<CotizacionDolar>>(cotizaciones, HttpStatus.OK);
		} else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@PostMapping(value = "/pasaje", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> obtenerCostoPasaje(@Valid @RequestBody CostoDePasajeRequest costoDePasajeRequest,
			BindingResult result) throws Exception {

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(ErrorHandler.formatearErrors(result), HttpStatus.BAD_REQUEST);
		}

		Optional<Cliente> clienteRta = clienteService.obtenerClientebyDNI(costoDePasajeRequest.getDni());
		if (!clienteRta.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO SE PUDO ENCONTRAR EL CLIENTE.");
		}

		Optional<Vuelo> vueloRta = vueloService.obtenerVueloOptional(costoDePasajeRequest.getNro_vuelo());
		if (!vueloRta.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO SE PUDO ENCONTRAR EL VUELO.");
		}
		
		PresupuestosEntregados newPresupuestosEntregados = new PresupuestosEntregados();
		
		List<CotizacionDolar> cotizaciones = costoDePasajeService.getCotizaciones();

		for (CotizacionDolar c : cotizaciones) {

			if (c.getNombre().equals("Dolar Oficial")) {
				
				c.setCompra(c.getCompra().replace(',', '.'));
				newPresupuestosEntregados.setImporte((Double.parseDouble(c.getCompra())) * 500);
			}

		}
		
		newPresupuestosEntregados.setVuelo(vueloRta.get());
		newPresupuestosEntregados.setCliente(clienteRta.get());
		
		PresupuestosEntregados presupuestosEntregados = costoDePasajeService.guardarPresupuestosEntregados(newPresupuestosEntregados);

		return new ResponseEntity<Object>(costoDePasajeResponse(presupuestosEntregados, clienteRta.get(), vueloRta.get()), HttpStatus.CREATED);
	}

	private CostoDePasajeResponse costoDePasajeResponse(PresupuestosEntregados presupuestosEntregados, Cliente cliente, Vuelo vuelo)
			throws Excepcion {

		try {

			CostoDePasajeResponse costoDePasajeResponse = new CostoDePasajeResponse(presupuestosEntregados);

			// Self link
			Link selfLink = WebMvcLinkBuilder.linkTo(CostoDePasajeController.class)
					.slash(presupuestosEntregados.getId()).withSelfRel();

			// Method link: Link al servicio que permitirá navegar hacia la ciudad
			// relacionada a la persona
			Link clienteLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class)
							.obtenerCliente(Long.valueOf(cliente.getDni())))
					.withRel("Cliente");

			// Method link: Link al servicio que permitirá navegar hacia la ciudad
			// relacionada a la persona
			Link vueloLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VueloController.class)
					.obtenerVuelo(vuelo.getNro_vuelo())).withRel("Vuelo");

			Link dolarLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(CostoDePasajeController.class).getCotizacionActual())
					.withRel("Dolar");

			costoDePasajeResponse.add(selfLink);
			costoDePasajeResponse.add(clienteLink);
			costoDePasajeResponse.add(vueloLink);
			costoDePasajeResponse.add(dolarLink);

			return costoDePasajeResponse;
		} catch (Exception e) {
			throw new Excepcion(e.getMessage(), 500);
		}
	}

}
