package com.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dao.PasajeRepository;
import com.api.domain.Pasaje;

@Service
public class PasajeService {

	@Autowired
	private PasajeRepository pasajeRepository;
	
	public Optional<Pasaje> obtenerPasaje(Long dni) {
		return pasajeRepository.findById(dni);
	}

	public Pasaje insertarPasaje(Pasaje pasaje) {
		return pasajeRepository.save(pasaje);
	}
}
