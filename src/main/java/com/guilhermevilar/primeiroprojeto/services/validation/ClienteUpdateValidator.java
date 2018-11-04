package com.guilhermevilar.primeiroprojeto.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.guilhermevilar.primeiroprojeto.domain.Cliente;
import com.guilhermevilar.primeiroprojeto.dto.ClienteDTO;
import com.guilhermevilar.primeiroprojeto.repositories.ClienteRepository;
import com.guilhermevilar.primeiroprojeto.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		//aqui iremos verificar se o e-mail atualizado é do mesmo Cliente.
		//caso seja, ele pode fazer a atualização e continuar com o mesmo e-mail
		//caso contrário, outro Cliente não poderá atualizar o e-mail com um já existente por outro usuário
		//vamos ter que comparar ID's. O Id do usuário na atualização vai na URI, temos que buscar de lá.
		
		//Map é uma coleção de pares chave valor
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
	
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) { 
			
			list.add(new FieldMessage("email", "Email já existente, por favor, insera outro e-mail."));
		}
		
		
      // inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
