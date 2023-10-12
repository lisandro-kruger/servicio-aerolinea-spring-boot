package com.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Pasaje;

@Repository
public interface PasajeRepository extends JpaRepository<Pasaje, Long>{

}
