package com.prodama.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodama.model.Sistema;
import com.prodama.repository.Sistemas;

@Service
public class CadastroSistemaService {
	
	@Autowired
	private Sistemas sistemas;
	
	@Transactional
	public Sistema salvar(Sistema sistema){
		
		Optional<Sistema> sistemaOptional = sistemas.findByNomeIgnoreCase(sistema.getNome());
		if ((sistemaOptional.isPresent()) && (sistema.getCodigo() == null)) {
			throw new RuntimeException("Sistema já cadastrado");
		}
		
		sistemas.save(sistema);
		return sistemas.saveAndFlush(sistema);
	}
	
}
