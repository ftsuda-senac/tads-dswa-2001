package br.senac.tads.dsw.exemplosspring;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@GetMapping("/index-dinamico")
	public ModelAndView abrirIndexDinamico() {
		List<ItemImagem> imagens = Arrays.asList(
				new ItemImagem("img/chapolin.png", "Chapolin"),
				new ItemImagem("img/jaspion.jpeg", "Jaspion"),
				new ItemImagem("img/madruga.jpg", "Madruga"));
		return new ModelAndView("index-dinamico").addObject("imagens", imagens);
	}

}
