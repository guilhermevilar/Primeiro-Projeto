package com.guilhermevilar.primeiroprojeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermevilar.primeiroprojeto.domain.Cliente;
import com.guilhermevilar.primeiroprojeto.repositories.ClienteRepository;
import com.guilhermevilar.primeiroprojeto.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired //dessa forma a dependência é auomaticamente instanciada
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id){
		
		Cliente obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		
		}
		return obj;
	}

}
