package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dao.ClienteRepository;
import com.api.domain.Cliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> obtenerClientes() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> obtenerClientebyDNI(Long dni) {
		return clienteRepository.findById(dni);
	}

	public Cliente insertarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente actualizarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

//	private boolean existeCliente (Long dni) {
//		boolean band = false;
//		Long dniCliente = clienteRepository.findBydni(dni);
//		if (dniCliente != null)
//			band = true;
//		return band;
//	}

	public void elminarCliente(long dni) {
		clienteRepository.deleteById(dni);
	}

}
