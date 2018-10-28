package com.guilhermevilar.primeiroprojeto.dto;

import java.io.Serializable;

import com.guilhermevilar.primeiroprojeto.domain.Categoria;

//DTO serve para definir quais dados apresentar quando fizer ações sobre a Categoria

public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	
	public CategoriaDTO() {	
		
	}
	
	public CategoriaDTO(Categoria obj) {
		
		id = obj.getId();
		nome = obj.getNome();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
	

}
