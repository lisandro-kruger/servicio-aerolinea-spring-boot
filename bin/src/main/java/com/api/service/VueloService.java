package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dao.VueloRepository;
import com.api.domain.Vuelo;
import com.api.excepcion.Excepcion;

@Service
public class VueloService {
	
	@Autowired
	private VueloRepository vueloRepository;
	
	// OBTENER LA LISTA DE VUELOS.
	public List<Vuelo> obtenerVuelos() {
		return vueloRepository.findAll();
	}
	
	// OBTENER LA LISTA POR ESTADO
	public List<Vuelo> filtroEstado(String estado) {
		
		return vueloRepository.findByEstado(estado);
	}
	
	//OBTENER EL ESTADO DE UN VUELO A PARTIR DE SU ID
	public String findByVueloEstado(Long nro) {
		
		return vueloRepository.findByVueloEstado(nro);
	}
	
	// OBTENER UN VUELO OPTIONAL A PARTIR DE SU ID.
	public Optional<Vuelo> obtenerVueloOptional(Long nro){
		return vueloRepository.findById(nro);
	}

	// DAR DE ALTA UN VUELO.
	public Vuelo guardarVuelo(Vuelo vuelo){
		
		return vueloRepository.save(vuelo);
	}
	
	// ACTUALIZAR O ELIMINAR UN VUELO.
	public Vuelo actualizarVuelo(Vuelo vuelo) throws Excepcion{
		
		if(findByVueloEstado(vuelo.getNro_vuelo()).equals("cancelado")) {
			throw new Excepcion("NO SE PUEDE ACTUALIZAR, PORQUE EL VUELO FUE CANCELADO.");
		}
		
		return vueloRepository.save(vuelo);
	}
}
