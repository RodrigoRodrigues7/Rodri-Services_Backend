package com.rodrigo.rodriservices.services;

import org.springframework.mail.SimpleMailMessage;

import com.rodrigo.rodriservices.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
