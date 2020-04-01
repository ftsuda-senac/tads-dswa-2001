package br.senac.tads.dsw.exemplosspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FormularioController {

	@Autowired
	private DadosPessoaisService service;

	@GetMapping("/lista")
	public ModelAndView listar() {
		return new ModelAndView("lista").addObject("dados", service.findAll());
	}
	
	@GetMapping("/formulario")
	public ModelAndView abrirForm() {
		return new ModelAndView("formulario");
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@ModelAttribute DadosPessoais dadosRecebidos) {
		// SALVAR NO BD
		service.save(dadosRecebidos);
		return new ModelAndView("resultado").addObject("dados", dadosRecebidos);
	}

	@PostMapping("/salvar-prg")
	public ModelAndView salvarPrg(@ModelAttribute DadosPessoais dadosRecebidos, RedirectAttributes redirAttr) {
		service.save(dadosRecebidos);
		redirAttr.addFlashAttribute("dados", dadosRecebidos);
		return new ModelAndView("redirect:/resultado-prg");
	}

	@GetMapping("/resultado-prg")
	public ModelAndView resultadoPrg() {
		return new ModelAndView("resultado");
	}

}
