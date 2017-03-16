package com.prodama.ws;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.prodama.model.Conexao;
import com.prodama.model.ConexaoCliente;

import com.prodama.repository.ConexaoClientes;
import com.prodama.repository.Conexoes;


@RestController
public class AtualizaVersaoController {
	
	@Autowired
	private Conexoes conexoes;
	
	@Autowired
	private ConexaoClientes conexaoClientes;
	
	@RequestMapping(method=RequestMethod.GET,value="/conexoes/versao/{codigoSenior}/{sigla}/{versao}")
	public String salvar(@PathVariable Long codigoSenior,@PathVariable String sigla,@PathVariable String versao){
		
		Optional<Conexao> conexao = conexoes.findByCodigoSenior(codigoSenior);
		List<ConexaoCliente> conexaoCliente  = conexaoClientes.findByConexao(conexao.get());
		String retorno = "";
			
		for (ConexaoCliente conexaoCliente2 : conexaoCliente) {
			if(sigla.equals(conexaoCliente2.getSistema().getNomeAbreviado())){
				
				conexaoCliente2.setVersao(versao.replace("-", "."));
				retorno  = "Cliente Senior: " + codigoSenior + "<br>" +  "Sistema: " + sigla + "<br>" + "Versão: "+  versao.replace("-", ".");
				
			} 
			
			if(retorno.equals("")){
				retorno = "Não Encontrado!";
			}
			conexaoClientes.save(conexaoCliente);
		}
		
		
		return retorno;
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/conexoes")
	public String buscar(){
		return "Não permitido!";
	}
}
