package com.guilhermevilar.primeiroprojeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.guilhermevilar.primeiroprojeto.domain.Categoria;
import com.guilhermevilar.primeiroprojeto.dto.CategoriaDTO;
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
	
	
	public List<Categoria> findAll(){ //retorna todas as categorias
		
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	

}
