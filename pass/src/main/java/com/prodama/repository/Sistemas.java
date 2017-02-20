package com.prodama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodama.model.Sistema;


public interface Sistemas extends JpaRepository<Sistema, Long> {

	public List<Sistema> findByNomeContainingIgnoreCase(String nome);
	
}
