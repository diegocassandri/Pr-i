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


import com.prodama.model.Sistema;
import com.prodama.repository.Sistemas;
import com.prodama.repository.filter.SistemaFilter;
import com.prodama.service.CadastroSistemaService;

@Controller
@RequestMapping("/sistemas")
public class SistemasController {

	@Autowired
	private Sistemas sistemas;

	@Autowired
	private CadastroSistemaService cadastroSistema;
	
	@GetMapping("/novo")
	public ModelAndView novo(Sistema sistema) {
		ModelAndView mv = new ModelAndView("sistema/cadastro-sistema");
		mv.addObject(sistema);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Sistema sistema, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(sistema);
		}
		
		try{
			cadastroSistema.salvar(sistema);
		}catch (RuntimeException e){
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(sistema);
		}
		
		attributes.addFlashAttribute("mensagem", "Sistema salvo com sucesso!");
		return new ModelAndView("redirect:/sistemas/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(SistemaFilter sistemaFilter) {
		ModelAndView mv = new ModelAndView("sistema/pesquisa-sistema");
		mv.addObject("sistemas", sistemas.findByNomeContainingIgnoreCase(
				Optional.ofNullable(sistemaFilter.getNome()).orElse("%")));
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Sistema sistema = sistemas.findOne(codigo);
		return novo(sistema);
	}
	
	@DeleteMapping("/{codigo}")
	public String apagar(@PathVariable Long codigo, RedirectAttributes attributes) {
		sistemas.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Sistema removido com sucesso");
		return "redirect:/sistemas";
	}
	
	
	
}







