package com.guilhermevilar.primeiroprojeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.guilhermevilar.primeiroprojeto.domain.Categoria;
import com.guilhermevilar.primeiroprojeto.repositories.CategoriaRepository;
import com.guilhermevilar.primeiroprojeto.services.exceptions.DataIntegrityException;
import com.guilhermevilar.primeiroprojeto.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired //dessa forma a dependência é auomaticamente instanciada
	private CategoriaRepository repo;
	
	public Categoria find(Integer id){
		
		Categoria obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
		
		}
		return obj;
	}
	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); //o objeto a ser inserido tem que ter o id nulo. Se não for nulo, o método save vai considerar que é uma atualização.
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId()); // assim vemos se o objeto já existe para ser atualizado.
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id); // caso o id não exista, dispara exceção.
		
		try {
			repo.delete(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível uma categoria que possui produtos.");
		}
		
	}

}
