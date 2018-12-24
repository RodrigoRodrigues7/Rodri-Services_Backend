package com.rodrigo.rodriservices.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.rodriservices.Repository.CategoriaRepository;
import com.rodrigo.rodriservices.domain.Categoria;
import com.rodrigo.rodriservices.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscarIdCategoria(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não Encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}
