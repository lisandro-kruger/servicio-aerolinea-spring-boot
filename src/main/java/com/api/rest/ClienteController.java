package com.api.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.domain.Cliente;
import com.api.excepcion.Excepcion;
import com.api.request.ClienteRequest;

import com.api.response.ClienteResponse;
import com.api.rest.errors.ErrorHandler;
import com.api.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	/**
	 * Metodo Get que devuelve Listado de Clientes
	 * 
	 * @return
	 * @throws Excepcion
	 */
	@GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ClienteResponse>> obtenerClientes() throws Excepcion {
		List<Cliente> clientesList = clienteService.obtenerClientes();
		if (clientesList != null && !clientesList.isEmpty()) {
			return new ResponseEntity<List<ClienteResponse>>(clientesResponseList(clientesList), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	/**
	 * Metodo get que devuelve datos del dni enviado en el path
	 * 
	 * @param dni
	 * @return
	 * @throws Excepcion
	 */
	@GetMapping(value = "/{dni}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClienteResponse> obtenerCliente(@PathVariable Long dni) throws Excepcion {

		Optional<Cliente> clienteRta = clienteService.obtenerClientebyDNI(dni);
		if (clienteRta.isPresent()) {
			Cliente cliente = clienteRta.get();
			return new ResponseEntity<ClienteResponse>(clienteResponse(cliente), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	/**
	 * Metodo Delete que elimina el mail enviado en el path
	 */
	@DeleteMapping("/{dni}")
	public ResponseEntity<Object> eliminarCliente(@PathVariable Long dni) throws Excepcion {

		Optional<Cliente> clienteRta = clienteService.obtenerClientebyDNI(dni);
		if (!clienteRta.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO SE PUDO ENCONTRAR EL CLIENTE.");
		}
		clienteService.elminarCliente(dni);
		return ResponseEntity.ok().build();
	}

	/**
	 * metodo post para agregar un cliente en la bd
	 * 
	 * @param clienteRequest
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Object> insertarCliente(@Valid @RequestBody ClienteRequest clienteRequest,
			BindingResult result) throws Exception {

		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorHandler.formatearErrors(result));
		}
		Cliente newCliente = clienteRequest.toEntidad();
		clienteService.insertarCliente(newCliente);
		return new ResponseEntity<Object>(clienteResponse(newCliente), HttpStatus.OK);
	}

	/**
	 * Metodo PUT para actualizar datos de un cliente
	 * 
	 * @param clienteRequest
	 * @param dni            No se permite modificar el dni de la persona
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/{dni}")
	public ResponseEntity<Object> actualizarCliente(@RequestBody ClienteRequest clienteRequest, @PathVariable long dni)
			throws Exception {
		Optional<Cliente> clienteRta = clienteService.obtenerClientebyDNI(dni);
		if (!clienteRta.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NO SE PUDO ENCONTRAR EL CLIENTE");
		} else {
			Cliente cliente = clienteRequest.toEntidad();
			if (!cliente.getDni().equals(clienteRta.get().getDni())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede modificar el dni.");
			}
			clienteService.actualizarCliente(cliente);
			return new ResponseEntity<Object>(clienteResponse(cliente), HttpStatus.OK);

		}
	}

	/**
	 * Se utiliza para implementar HATEOAS
	 * 
	 * @param pojo
	 * @return
	 * @throws Excepcion
	 */
	private ClienteResponse clienteResponse(Cliente cliente) throws Excepcion {
		try {
			ClienteResponse clienteResponse = new ClienteResponse(cliente);

			return clienteResponse;
		} catch (Exception e) {
			throw new Excepcion(e.getMessage(), 500);
		}
	}

	private List<ClienteResponse> clientesResponseList(List<Cliente> clientes) throws Excepcion {
		try {
			List<ClienteResponse> clientesResponseList = new ArrayList<ClienteResponse>();
			for (Cliente c : clientes) {
				ClienteResponse clienteResponse = new ClienteResponse(c);
				clientesResponseList.add(clienteResponse);
			}

			return clientesResponseList;
		} catch (Exception e) {
			throw new Excepcion(e.getMessage(), 500);
		}
	}

}
