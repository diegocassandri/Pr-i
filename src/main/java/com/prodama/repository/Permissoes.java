package com.prodama.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodama.model.Grupo;
import com.prodama.model.Permissao;

public interface Permissoes extends JpaRepository<Permissao, Long> {
	
	List<Permissao> findByGruposIn(Grupo grupo);

}