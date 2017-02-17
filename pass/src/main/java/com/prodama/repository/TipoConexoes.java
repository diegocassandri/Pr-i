package com.prodama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodama.model.TipoConexao;


public interface TipoConexoes extends JpaRepository<TipoConexao, Long> {

	public List<TipoConexao> findByNomeContainingIgnoreCase(String nome);
	
}
