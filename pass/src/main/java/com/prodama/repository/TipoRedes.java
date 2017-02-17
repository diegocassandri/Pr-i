package com.prodama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodama.model.TipoRede;


public interface TipoRedes extends JpaRepository<TipoRede, Long> {

	public List<TipoRede> findByNomeContainingIgnoreCase(String nome);
	
}
