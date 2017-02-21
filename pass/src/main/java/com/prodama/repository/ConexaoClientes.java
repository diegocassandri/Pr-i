package com.prodama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodama.model.Conexao;
import com.prodama.model.ConexaoCliente;


public interface ConexaoClientes extends JpaRepository<ConexaoCliente, Long> {

	public List<ConexaoCliente> findByConexao(Conexao conexao);
	
}
