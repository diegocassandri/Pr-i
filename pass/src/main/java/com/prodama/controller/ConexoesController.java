package com.prodama.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.prodama.model.Conexao;
import com.prodama.model.SimNao;
import com.prodama.repository.Clientes;
import com.prodama.repository.Conexoes;
import com.prodama.repository.TipoConexoes;
import com.prodama.repository.TipoRedes;
import com.prodama.repository.filter.ConexaoFilter;

@Controller
@RequestMapping("/senhas")
public class ConexoesController {

	@Autowired
	private Conexoes conexoes;
	
	@Autowired
	private Clientes clientes;
	
	@Autowired
	private TipoRedes tipoRedes;
	
	@Autowired
	private TipoConexoes tipoConexoes;

	@GetMapping("/novo")
	public ModelAndView novo(Conexao conexao) {
		ModelAndView mv = new ModelAndView("cadastro-conexao");
		mv.addObject(conexao);
		mv.addObject("permissoes", SimNao.values());
		mv.addObject("clientes", clientes.findAll());
		mv.addObject("tipoConexoes", tipoConexoes.findAll());
		mv.addObject("tipoRedes", tipoRedes.findAll());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Conexao conexao, BindingResult result, 
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(conexao);
		}
		
		conexoes.save(conexao);
		attributes.addFlashAttribute("mensagem", "Conexão salva com sucesso!");
		return new ModelAndView("redirect:/senhas/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ConexaoFilter conexaoFilter) {
		ModelAndView mv = new ModelAndView("senhas");
		mv.addObject("conexoes", conexoes.findAll());
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Conexao conexao = conexoes.findOne(codigo);
		return novo(conexao);
	}
	
	@DeleteMapping("/{codigo}")
	public String apagar(@PathVariable Long codigo, RedirectAttributes attributes) {
		conexoes.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Conexão removida com sucesso");
		return "redirect:/senhas";
	}
	
	
	
}







