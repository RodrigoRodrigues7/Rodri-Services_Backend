package com.rodrigo.rodriservices;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rodrigo.rodriservices.Repository.CategoriaRepository;
import com.rodrigo.rodriservices.Repository.CidadeRepository;
import com.rodrigo.rodriservices.Repository.ClienteRepository;
import com.rodrigo.rodriservices.Repository.EnderecoRepository;
import com.rodrigo.rodriservices.Repository.EstadoRepository;
import com.rodrigo.rodriservices.Repository.ItemPedidoRepository;
import com.rodrigo.rodriservices.Repository.PagamentoRepository;
import com.rodrigo.rodriservices.Repository.PedidoRepository;
import com.rodrigo.rodriservices.Repository.ProdutoRepository;
import com.rodrigo.rodriservices.domain.Categoria;
import com.rodrigo.rodriservices.domain.Cidade;
import com.rodrigo.rodriservices.domain.Cliente;
import com.rodrigo.rodriservices.domain.Endereco;
import com.rodrigo.rodriservices.domain.Estado;
import com.rodrigo.rodriservices.domain.ItemPedido;
import com.rodrigo.rodriservices.domain.Pagamento;
import com.rodrigo.rodriservices.domain.PagamentoComBoleto;
import com.rodrigo.rodriservices.domain.PagamentoComCartao;
import com.rodrigo.rodriservices.domain.Pedido;
import com.rodrigo.rodriservices.domain.Produto;
import com.rodrigo.rodriservices.domain.enums.EstadoPagamento;
import com.rodrigo.rodriservices.domain.enums.TipoCliente;

@SpringBootApplication
public class RodriServicesApplication implements CommandLineRunner {

	@Autowired /* Annotation usada para instanciar o objeto automaticamente */
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(RodriServicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Aparecida", "mariaSilva@gmail.com", "066.643.864-22",
				TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("3338-2381", "3574-2344"));

		Endereco end1 = new Endereco(null, "Rua Flores", "123", "Apto 101", "Jardim Brasil", "60.780-540", cli1, c1);
		Endereco end2 = new Endereco(null, "Rua Matos", "1023", "Sala 836", "Centro", "70.890-222", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:44"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/11/2017 14:35"), cli1, end2);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pag2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
