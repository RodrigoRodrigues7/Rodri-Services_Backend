package com.rodrigo.rodriservices.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.rodriservices.Repository.PedidoRepository;
import com.rodrigo.rodriservices.domain.Pedido;
import com.rodrigo.rodriservices.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido findIdPedido(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não Encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
