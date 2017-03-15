package com.prodama.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.prodama.model.Conexao;
import com.prodama.repository.Conexoes;

@RestController
public class AtualizaVersaoController {
	
	@Autowired
	private Conexoes conexoes;
	
	@RequestMapping(method=RequestMethod.POST,value="/atualizaVersao",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Conexao> salvar(@RequestBody Conexao conexao){
		
		conexoes.save(conexao);
		
		return new ResponseEntity<Conexao>(conexao,HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/conexoes")
	public List<Conexao> buscar(){
		return conexoes.findAll();
	}
}
