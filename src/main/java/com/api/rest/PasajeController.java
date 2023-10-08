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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.domain.Cliente;
import com.api.domain.Pasaje;
import com.api.domain.Vuelo;
import com.api.excepcion.Excepcion;
import com.api.request.PasajeRequest;
import com.api.response.CostoDePasajeResponse;
import com.api.response.CotizacionDolar;
import com.api.response.PasajeResponse;
import com.api.rest.errors.ErrorHandler;
import com.api.service.ClienteService;
import com.api.service.CostoDePasajeService;
import com.api.service.PasajeService;
import com.api.service.VueloService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("pasaje")
public class PasajeController {
	
	@Autowired
	private PasajeService pasajeService;
	
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private VueloService vueloService;
	
	@Autowired
	private CostoDePasajeService costoDePasajeService;
	
	@GetMapping(value = "/{dni}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PasajeResponse> obtenerPasaje(@PathVariable Long id) throws Excepcion {
		
		Optional<Pasaje> pasajeRta = pasajeService.obtenerPasaje(id);
		if (pasajeRta.isPresent()) {
			Pasaje pasaje = pasajeRta.get();
			return new ResponseEntity<PasajeResponse>(pasajeResponse(pasaje), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@PostMapping
	public ResponseEntity<Object> insertarPasaje(@Valid @RequestBody PasajeRequest pasajeRequest,
			BindingResult result) throws Exception {

		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorHandler.formatearErrors(result));
		}
		
		Optional<Cliente> clienteRta = clienteService.obtenerClientebyDNI(pasajeRequest.getDni());
		if (!clienteRta.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO SE PUDO ENCONTRAR EL CLIENTE.");
		}

		Optional<Vuelo> vueloRta = vueloService.obtenerVueloOptional(pasajeRequest.getNro_vuelo());
		if (!vueloRta.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO SE PUDO ENCONTRAR EL VUELO.");
		}
		
		Pasaje pasaje = new Pasaje();
		
		List<CotizacionDolar> cotizaciones = costoDePasajeService.getCotizaciones();

		for (CotizacionDolar c : cotizaciones) {

			if (c.getNombre().equals("Dolar Oficial")) {
				pasaje.setImporte(Double.valueOf(c.getCompra()) * 500);
			}

		}
		
		pasaje.setCliente(clienteRta.get());
		pasaje.setVuelo(vueloRta.get());
		
		Pasaje newPasaje = pasajeService.insertarPasaje(pasaje);
		return new ResponseEntity<Object>(pasajeResponse(newPasaje, clienteRta.get(), vueloRta.get()), HttpStatus.OK);
	}
	
	
	private PasajeResponse pasajeResponse(Pasaje pasaje) throws Excepcion {
		try {
			PasajeResponse pasajeResponse = new PasajeResponse(pasaje);

			return pasajeResponse;
		} catch (Exception e) {
			throw new Excepcion(e.getMessage(), 500);
		}
	}
	
	
	private CostoDePasajeResponse pasajeResponse(Pasaje pasaje, Cliente cliente, Vuelo vuelo)
			throws Excepcion {

		try {

			PasajeResponse pasajeResponse = new PasajeResponse(pasaje);

			// Self link
			Link selfLink = WebMvcLinkBuilder.linkTo(PasajeController.class)
					.slash(pasaje.getNro_pasaje()).withSelfRel();

			// Method link: Link al servicio que permitirá navegar hacia la ciudad
			// relacionada a la persona
			Link clienteLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ClienteController.class)
							.obtenerCliente(cliente.getDni()))
					.withRel("Cliente");

			// Method link: Link al servicio que permitirá navegar hacia la ciudad
			// relacionada a la persona
			Link vueloLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VueloController.class)
					.obtenerVuelo(vuelo.getNro_vuelo())).withRel("Vuelo");

			Link dolarLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(CostoDePasajeController.class).getCotizacionActual())
					.withRel("Dolar");

			pasajeResponse.add(selfLink);
			pasajeResponse.add(clienteLink);
			pasajeResponse.add(vueloLink);
			pasajeResponse.add(dolarLink);

			return null;
		} catch (Exception e) {
			throw new Excepcion(e.getMessage(), 500);
		}
	}

}
