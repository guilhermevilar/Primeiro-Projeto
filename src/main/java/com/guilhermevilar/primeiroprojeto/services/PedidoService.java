package com.guilhermevilar.primeiroprojeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermevilar.primeiroprojeto.domain.Pedido;
import com.guilhermevilar.primeiroprojeto.repositories.PedidoRepository;
import com.guilhermevilar.primeiroprojeto.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired //dessa forma a dependência é auomaticamente instanciada
	private PedidoRepository repo;
	
	public Pedido find(Integer id){
		
		Pedido obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
		
		}
		return obj;
	}

}
