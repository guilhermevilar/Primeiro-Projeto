package com.guilhermevilar.primeiroprojeto.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.guilhermevilar.primeiroprojeto.domain.Cliente;
import com.guilhermevilar.primeiroprojeto.services.validation.ClienteUpdate;

//Não deixaremos o cliente alterar cpf ou cnpj!

@ClienteUpdate
public class ClienteDTO implements Serializable{ //DTO é onde ficam os dados básicos para deletar, criar, etc...
	private static final long serialVersionUID = 1L; 
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres.")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	@Email(message="Email inválido")
	private String email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO (Cliente obj) {
		
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
