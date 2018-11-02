package com.guilhermevilar.primeiroprojeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.guilhermevilar.primeiroprojeto.domain.Cidade;
import com.guilhermevilar.primeiroprojeto.domain.Cliente;
import com.guilhermevilar.primeiroprojeto.domain.Endereco;
import com.guilhermevilar.primeiroprojeto.domain.enums.TipoCliente;
import com.guilhermevilar.primeiroprojeto.dto.ClienteDTO;
import com.guilhermevilar.primeiroprojeto.dto.ClienteNewDTO;
import com.guilhermevilar.primeiroprojeto.repositories.ClienteRepository;
import com.guilhermevilar.primeiroprojeto.repositories.EnderecoRepository;
import com.guilhermevilar.primeiroprojeto.services.exceptions.DataIntegrityException;
import com.guilhermevilar.primeiroprojeto.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired //dessa forma a dependência é auomaticamente instanciada
	private ClienteRepository repo;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;

	
	public Cliente find(Integer id){
		
		Cliente obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		
		}
		return obj;
	}
	
	
	public Cliente insert(Cliente obj) {
		obj.setId(null); //o objeto a ser inserido tem que ter o id nulo. Se não for nulo, o método save vai considerar que é uma atualização.
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return obj;
	}
	
	
	public Cliente update(Cliente obj) {
		
		
		Cliente newObj = find(obj.getId()); 
		updateData(newObj, obj); // atualiza esse novo objeto, a partir do objeto que veio no argumento
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id); // caso o id não exista, dispara exceção.
		
		try {
			repo.delete(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque existem entidades relacionadas.");
		}
	}
	
	
	public List<Cliente> findAll(){ //retorna todas as categorias
		
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) { //específico para insert (post)
		
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) { //verifica se a pessoa entrou com outro telefone
			cli.getTelefones().add(objDto.getTelefone2());
		}
		
		if (objDto.getTelefone3() != null) { 
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
