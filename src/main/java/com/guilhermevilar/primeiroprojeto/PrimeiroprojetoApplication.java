package com.guilhermevilar.primeiroprojeto;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.guilhermevilar.primeiroprojeto.domain.Categoria;
import com.guilhermevilar.primeiroprojeto.domain.Cidade;
import com.guilhermevilar.primeiroprojeto.domain.Cliente;
import com.guilhermevilar.primeiroprojeto.domain.Endereco;
import com.guilhermevilar.primeiroprojeto.domain.Estado;
import com.guilhermevilar.primeiroprojeto.domain.ItemPedido;
import com.guilhermevilar.primeiroprojeto.domain.Pagamento;
import com.guilhermevilar.primeiroprojeto.domain.PagamentoComBoleto;
import com.guilhermevilar.primeiroprojeto.domain.PagamentoComCartao;
import com.guilhermevilar.primeiroprojeto.domain.Pedido;
import com.guilhermevilar.primeiroprojeto.domain.Produto;
import com.guilhermevilar.primeiroprojeto.domain.enums.EstadoPagamento;
import com.guilhermevilar.primeiroprojeto.domain.enums.TipoCliente;
import com.guilhermevilar.primeiroprojeto.repositories.CategoriaRepository;
import com.guilhermevilar.primeiroprojeto.repositories.CidadeRepository;
import com.guilhermevilar.primeiroprojeto.repositories.ClienteRepository;
import com.guilhermevilar.primeiroprojeto.repositories.EnderecoRepository;
import com.guilhermevilar.primeiroprojeto.repositories.EstadoRepository;
import com.guilhermevilar.primeiroprojeto.repositories.ItemPedidoRepository;
import com.guilhermevilar.primeiroprojeto.repositories.PagamentoRepository;
import com.guilhermevilar.primeiroprojeto.repositories.PedidoRepository;
import com.guilhermevilar.primeiroprojeto.repositories.ProdutoRepository;

@SpringBootApplication
public class PrimeiroprojetoApplication implements CommandLineRunner{
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	


	public static void main(String[] args) {
		SpringApplication.run(PrimeiroprojetoApplication.class, args);
		
		
	}

	@Override // aqui instanciamos os objetos para a aplicação
	public void run(String... args) throws Exception {
		
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "6541231", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("564654", "65465464"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "564564", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "387564", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("21/10/2018 15:09"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("21/10/2018 15:12"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("22/10/2018 11:45"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pgto1, pgto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2.000);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
		
		
	
		
	}
}