package com.rodrigo.rodriservices.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigo.rodriservices.Repository.ItemPedidoRepository;
import com.rodrigo.rodriservices.Repository.PagamentoRepository;
import com.rodrigo.rodriservices.Repository.PedidoRepository;
import com.rodrigo.rodriservices.domain.Cliente;
import com.rodrigo.rodriservices.domain.ItemPedido;
import com.rodrigo.rodriservices.domain.PagamentoComBoleto;
import com.rodrigo.rodriservices.domain.Pedido;
import com.rodrigo.rodriservices.domain.enums.EstadoPagamento;
import com.rodrigo.rodriservices.security.UserSpringSecurity;
import com.rodrigo.rodriservices.services.exceptions.AuthorizationException;
import com.rodrigo.rodriservices.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	@Autowired
	private PedidoRepository repo;
	

	public Pedido findIdPedido(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.findIdCliente(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		obj = repo.save(obj);
		pagamentoRepo.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findIdProduto(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepo.saveAll(obj.getItens());
		//System.out.println(obj);
		//emailService.sendOrderConfirmationHtmlEmail(obj);
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSpringSecurity user = UserService.authenticated();
		if(user ==null) {
			throw new AuthorizationException("Acesso Negado!");
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente = clienteService.findIdCliente(user.getId());
		return repo.findByCliente(cliente, pageRequest);
	}

}














