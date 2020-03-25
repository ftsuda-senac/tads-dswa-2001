package br.senac.tads.dsw.exemplosspring;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExemploController {

	@GetMapping("/ex1")
	public String exemplo1() {
		return "exemplo1";
	}

	@GetMapping("/ex2")
	public String exemplo2(Model modelo) {
		String mensagem = "Mensagem gerada no Controller";
		int numero = 2403;
		LocalDateTime dataHoraAcesso = LocalDateTime.now();
		
		modelo.addAttribute("msg", mensagem);
		modelo.addAttribute("numero", numero);
		modelo.addAttribute("dtAcesso", dataHoraAcesso);
		return "exemplo2";
	}

	@GetMapping("/ex2b")
	public ModelAndView exemplo2() {
		String mensagem = "Mensagem gerada no Controller - Exemplo ModelAndView";
		int numero = 2038;
		LocalDateTime dataHoraAcesso = LocalDateTime.now();
		
		ModelAndView mv = new ModelAndView("exemplo2");
		mv.addObject("msg", mensagem);
		mv.addObject("numero", numero);
		mv.addObject("dtAcesso", dataHoraAcesso);
		return mv;
	}

	@GetMapping("/ex3")
	public ModelAndView exemplo3(
			@RequestParam("nome") String nome,
			@RequestParam(value = "numero", defaultValue = "0") int numero,
			@RequestParam(value = "dtnasc", required = false) String dtNascStr) {
		
		ModelAndView mv = new ModelAndView("exemplo3");
		mv.addObject("nome", nome);
		mv.addObject("numero", numero);
		
		if (dtNascStr != null) {
			LocalDate dtNasc = LocalDate.parse(dtNascStr);
			Period periodo = Period.between(dtNasc, LocalDate.now());
			int idade = periodo.getYears();
			mv.addObject("dtnasc", dtNasc);
			mv.addObject("idade", idade);
		}
		return mv;
	}

	@GetMapping("/ex4")
	public ModelAndView exemplo4() {
		List<Item> itens = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			Item item  = new Item(i, "Item " + i);
			itens.add(item);
		}
		
		ModelAndView mv = new ModelAndView("exemplo4");
		mv.addObject("itens", itens);
		return mv;
	}

	@GetMapping("/ex5/{apelido}")
	public ModelAndView exemplo5(
			@PathVariable("apelido") String apelido,
			@RequestParam(value = "numero", defaultValue = "0") int numero,
			@RequestParam(value = "dtnasc", required = false) String dtNascStr) {
				
		ModelAndView mv = new ModelAndView("exemplo5");
		mv.addObject("apelido", apelido);
		mv.addObject("numero", numero);
		
		if (dtNascStr != null) {
			LocalDate dtNasc = LocalDate.parse(dtNascStr);
			Period periodo = Period.between(dtNasc, LocalDate.now());
			int idade = periodo.getYears();
			mv.addObject("dtnasc", dtNasc);
			mv.addObject("idade", idade);
		}
		return mv;
	}

	@GetMapping("/ex6")
	public ModelAndView exemplo6(@RequestHeader("user-agent") String userAgent) {
		ModelAndView mv = new ModelAndView("exemplo6");
		mv.addObject("ua", userAgent);
		return mv;
	}

	@GetMapping("/ex7")
	public ModelAndView exemplo7(@RequestHeader Map<String, String> cabecalhos) {
		ModelAndView mv = new ModelAndView("exemplo7");
		mv.addObject("cabecalhos", cabecalhos);
		return mv;
	}

}
