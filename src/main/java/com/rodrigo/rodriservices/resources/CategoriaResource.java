package com.rodrigo.rodriservices.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.rodriservices.domain.Categoria;

/* Essa classe será um controlador REST */
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria(1, "Informatica");
		Categoria cat2 = new Categoria(2, "Escritorio");
		
		List<Categoria> listaCat = new ArrayList<Categoria>();
		listaCat.add(cat1);
		listaCat.add(cat2);
		
		return listaCat;
	}
	
}
