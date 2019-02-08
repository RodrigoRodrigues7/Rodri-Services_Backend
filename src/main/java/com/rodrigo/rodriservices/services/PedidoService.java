package com.rodrigo.rodriservices.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.rodriservices.Repository.ItemPedidoRepository;
import com.rodrigo.rodriservices.Repository.PagamentoRepository;
import com.rodrigo.rodriservices.Repository.PedidoRepository;
import com.rodrigo.rodriservices.domain.ItemPedido;
import com.rodrigo.rodriservices.domain.PagamentoComBoleto;
import com.rodrigo.rodriservices.domain.Pedido;
import com.rodrigo.rodriservices.domain.enums.EstadoPagamento;
import com.rodrigo.rodriservices.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;	
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	

	public Pedido findIdPedido(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
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
			ip.setPreco(produtoService.findIdProduto(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepo.saveAll(obj.getItens());
		return obj;
	}
	

}














