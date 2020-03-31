package br.senac.tads.dsw.exemplosspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormularioController {
	
	@GetMapping("/formulario")
	public ModelAndView abrirForm() {
		return new ModelAndView("formulario");
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(@ModelAttribute DadosPessoais dadosRecebidos) {
		// SALVAR NO BD
		return new ModelAndView("resultado").addObject("dados", dadosRecebidos);
	}

}
