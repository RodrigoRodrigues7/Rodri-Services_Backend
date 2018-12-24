package com.rodrigo.rodriservices;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rodrigo.rodriservices.Repository.CategoriaRepository;
import com.rodrigo.rodriservices.domain.Categoria;

@SpringBootApplication
public class RodriServicesApplication implements CommandLineRunner {

	@Autowired /* Annotation usada para instanciar o objeto automaticamente */
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(RodriServicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}

