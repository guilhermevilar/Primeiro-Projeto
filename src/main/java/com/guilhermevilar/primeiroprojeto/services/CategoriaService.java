package com.guilhermevilar.primeiroprojeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermevilar.primeiroprojeto.domain.Categoria;
import com.guilhermevilar.primeiroprojeto.repositories.CategoriaRepository;
import com.guilhermevilar.primeiroprojeto.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired //dessa forma a dependência é auomaticamente instanciada
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id){
		
		Categoria obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
		
		}
		return obj;
	}

}
