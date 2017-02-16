package com.prodama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodama.model.Vinho;

public interface Vinhos extends JpaRepository<Vinho, Long> {

	public List<Vinho> findByNomeContainingIgnoreCase(String nome);
	
}
