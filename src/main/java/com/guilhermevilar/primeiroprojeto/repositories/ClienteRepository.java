package com.guilhermevilar.primeiroprojeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guilhermevilar.primeiroprojeto.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	
	@Transactional //evita locking no gerenciamentro de transações do banco, deixa mais rápido
	Cliente findByEmail(String email); //assim o Spring Data já detecta que você quer fazer uma busca por email!
}
