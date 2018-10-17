package com.guilhermevilar.primeiroprojeto.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermevilar.primeiroprojeto.domain.Categoria;
import com.guilhermevilar.primeiroprojeto.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource{
	
	
	
	@Autowired
	private CategoriaService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) { //PathVariable para saber que o id do value (url) irá para o parâmetro Integer id
	//Response Entity encapsula vários métodos para requisições http
		
		Categoria obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj);
		
		
	}
}
