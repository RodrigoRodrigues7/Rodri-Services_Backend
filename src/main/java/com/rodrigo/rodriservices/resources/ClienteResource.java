package com.rodrigo.rodriservices.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.rodriservices.domain.Cliente;
import com.rodrigo.rodriservices.services.ClienteService;

/* Essa classe será um controlador REST */
@RestController
@RequestMapping(value="/clientes") // Nome to EndPoint
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente obj = service.findIdCliente(id);
		return ResponseEntity.ok().body(obj);
	}

}
