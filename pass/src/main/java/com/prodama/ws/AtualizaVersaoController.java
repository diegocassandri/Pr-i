package com.prodama.ws;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.prodama.model.Conexao;
import com.prodama.model.ConexaoCliente;
import com.prodama.model.ConexaoWs;
import com.prodama.repository.ConexaoClientes;
import com.prodama.repository.Conexoes;


@RestController
public class AtualizaVersaoController {
	
	@Autowired
	private Conexoes conexoes;
	
	@Autowired
	private ConexaoClientes conexaoClientes;
	
	@RequestMapping(method=RequestMethod.GET,value="/conexoes/atualizaVersao/{codigoSenior}/{sigla}/{versao}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Conexao> salvar(@PathVariable Long codigoSenior,@PathVariable String sigla,@PathVariable String versao,@RequestBody ConexaoWs conexaoWs){
		
		Optional<Conexao> conexao = conexoes.findByCodigoSenior(codigoSenior);
		List<ConexaoCliente> conexaoCliente  = conexaoClientes.findByConexao(conexao.get());
			
		for (ConexaoCliente conexaoCliente2 : conexaoCliente) {
			if(sigla.equals(conexaoCliente2.getSistema().getNomeAbreviado())){
				
				conexaoCliente2.setVersao(versao);
				
			}
			conexaoClientes.save(conexaoCliente);
		}
		
		
		
		return new ResponseEntity<Conexao>(HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/conexoes")
	public List<Conexao> buscar(){
		return conexoes.findAll();
	}
}
