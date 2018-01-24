package com.prodama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodama.model.Conexao;
import com.prodama.model.ConexaoRede;


public interface ConexaoRedes extends JpaRepository<ConexaoRede, Long> {

	public List<ConexaoRede> findByConexao(Conexao conexao);
	
}
