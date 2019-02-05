package com.rodrigo.rodriservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigo.rodriservices.Repository.CategoriaRepository;
import com.rodrigo.rodriservices.Repository.ProdutoRepository;
import com.rodrigo.rodriservices.domain.Categoria;
import com.rodrigo.rodriservices.domain.Produto;
import com.rodrigo.rodriservices.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public Produto findIdProduto(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não Encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		
		return repo.search(nome, categorias, pageRequest);
	}
	
}
