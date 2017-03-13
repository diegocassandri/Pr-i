package com.prodama.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodama.model.Conexao;


public interface Conexoes extends JpaRepository<Conexao, Long> {

	public List<Conexao> findByClienteContaining(String nome);
	
	public Optional<Conexao> findByCodigoSenior(Long codigoSenior);
	
}
