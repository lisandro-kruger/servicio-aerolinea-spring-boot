package com.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.domain.Vuelo;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long>{
	
	/*
	 * CONSULTA SQL PARA VERIFICAR SI YA EXISTE UN NRO DE VUELO REGISTRADO.
	 */
	@Query(value = "SELECT v.nro FROM vuelo v WHERE v.nro = ?1", nativeQuery = true)
	Long findByNroVuelo(Long nro);
	
	@Query(value = "SELECT * FROM vuelo v WHERE v.estado = ?1", nativeQuery = true)
	public List<Vuelo>findByEstado(String estado);
	
	@Query(value = "SELECT v.estado FROM vuelo v WHERE v.nro = ?1", nativeQuery = true)
	public String findByVueloEstado(Long nro);
}
