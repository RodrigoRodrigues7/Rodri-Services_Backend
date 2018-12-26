package com.rodrigo.rodriservices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.rodriservices.domain.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	
	
}
