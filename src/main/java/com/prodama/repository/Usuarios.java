package com.prodama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodama.model.Usuario;



public interface Usuarios extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String login);
}