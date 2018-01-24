package com.prodama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodama.model.Grupo;
import com.prodama.model.Usuario;
public interface Grupos extends JpaRepository<Grupo, Long> {
	
	List<Grupo> findByUsuariosIn(Usuario usuario);

}