package com.prodama.controller;

import java.util.Optional;

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

import com.prodama.model.TipoConexao;
import com.prodama.repository.TipoConexoes;
import com.prodama.repository.filter.TipoConexaoFilter;


@Controller
@RequestMapping("/tipoConexao")
public class TipoConexaoController {

	@Autowired
	private TipoConexoes tipoConexoes;

	@GetMapping("/novo")
	public ModelAndView novo(TipoConexao tipoConexao) {
		ModelAndView mv = new ModelAndView("tipoConexao/cadastro-conexao");
		mv.addObject(tipoConexao);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid TipoConexao tipoConexao, BindingResult result, 
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(tipoConexao);
		}
		
		tipoConexoes.save(tipoConexao);
		attributes.addFlashAttribute("mensagem", "Tipo de conexão salvo com sucesso!");
		return new ModelAndView("redirect:/tipoConexao/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(TipoConexaoFilter tipoConexaoFilter) {
		ModelAndView mv = new ModelAndView("tipoConexao/pesquisa-conexao");
		mv.addObject("tipoConexoes", tipoConexoes.findByNomeContainingIgnoreCase(
				Optional.ofNullable(tipoConexaoFilter.getNome()).orElse("%")));
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		TipoConexao tipoConexao = tipoConexoes.findOne(codigo);
		return novo(tipoConexao);
	}
	
	@DeleteMapping("/{codigo}")
	public String apagar(@PathVariable Long codigo, RedirectAttributes attributes) {
		tipoConexoes.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Tipo de conexão removido com sucesso");
		return "redirect:/tipoConexao";
	}
	
	
	
}







