package com.rodrigo.rodriservices.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rodrigo.rodriservices.Repository.ClienteRepository;
import com.rodrigo.rodriservices.domain.Cliente;
import com.rodrigo.rodriservices.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();
	
	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepo.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado!");
		}
		
		String newPass = newPassword();
		cliente.setSenha(encoder.encode(newPass));
		
		clienteRepo.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if(opt == 0) { //Gera um digito
			return (char) (random.nextInt(10) + 48);
		} else if(opt == 1) { //Gera letra maiuscula
			return (char) (random.nextInt(26) + 65);
		} else { //Gera letra minuscula
			return (char) (random.nextInt(26) + 97);
		}
	}
	
}
