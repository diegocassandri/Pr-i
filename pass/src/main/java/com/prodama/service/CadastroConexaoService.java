package com.prodama.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodama.model.Conexao;
import com.prodama.repository.Conexoes;

@Service
public class CadastroConexaoService {
	
	@Autowired
	private Conexoes conexoes;
	
	@Transactional  
	public Conexao salvar(Conexao conexao){
		
		Optional<Conexao> conexaoOptional = conexoes.findByCodigoSenior(conexao.getCodigoSenior());
		if ((conexaoOptional.isPresent()) && (conexao.getCodigo() == null ) ) {
			throw new RuntimeException("Código Senior já cadastrado");
		}
		
		conexoes.save(conexao);
		return conexoes.saveAndFlush(conexao);
	}
	
}
