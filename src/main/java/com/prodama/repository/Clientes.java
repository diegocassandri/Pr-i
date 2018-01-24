package com.prodama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodama.model.Cliente;


public interface Clientes extends JpaRepository<Cliente, Long> {

	public List<Cliente> findByNomeContainingIgnoreCase(String nome);
	
}
