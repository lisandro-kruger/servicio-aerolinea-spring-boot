package com.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.PresupuestosEntregados;

@Repository
public interface CostoDePasajeRepository extends JpaRepository<PresupuestosEntregados, Long>{

}
