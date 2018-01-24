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



import com.prodama.model.TipoRede;
import com.prodama.repository.TipoRedes;
import com.prodama.repository.filter.TipoRedeFilter;

@Controller
@RequestMapping("/tipoRede")
public class TipoRedeController {

	@Autowired
	private TipoRedes tipoRedes;

	@GetMapping("/novo")
	public ModelAndView novo(TipoRede tipoRede) {
		ModelAndView mv = new ModelAndView("tipoRede/cadastro-rede");
		mv.addObject(tipoRede);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid TipoRede tipoRede, BindingResult result, 
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(tipoRede);
		}
		
		tipoRedes.save(tipoRede);
		attributes.addFlashAttribute("mensagem", "Tipo de rede salvo com sucesso!");
		return new ModelAndView("redirect:/tipoRede/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(TipoRedeFilter tipoRedeFilter) {
		ModelAndView mv = new ModelAndView("tipoRede/pesquisa-rede");
		mv.addObject("tipoRedes", tipoRedes.findByNomeContainingIgnoreCase(
				Optional.ofNullable(tipoRedeFilter.getNome()).orElse("%")));
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		TipoRede tipoRede = tipoRedes.findOne(codigo);
		return novo(tipoRede);
	}
	
	@DeleteMapping("/{codigo}")
	public String apagar(@PathVariable Long codigo, RedirectAttributes attributes) {
		tipoRedes.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Tipo de rede removido com sucesso");
		return "redirect:/tipoRede";
	}
	
	
	
}







