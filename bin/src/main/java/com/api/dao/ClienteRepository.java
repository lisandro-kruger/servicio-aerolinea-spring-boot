package com.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query(value = "SELECT v.dni FROM cliente v WHERE v.dni = ?1", nativeQuery = true)
	Long findBydni (Long dni);
}
