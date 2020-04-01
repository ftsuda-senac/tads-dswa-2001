package br.senac.tads.dsw.exemplosspring;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DadosPessoaisRestController {

	@GetMapping("/api/dados")
	@CrossOrigin(origins = "*")
	public ResponseEntity<DadosPessoais> obter() {
		DadosPessoais dados = new DadosPessoais();
		dados.setId(124);
		dados.setNome("Beltrana da Silva");
		dados.setDescricao("Descrição da beltrana");
		dados.setDtNascimento(LocalDate.of(2000, 4, 20));
		dados.setEmail("beltrana@teste.com");
		dados.setNumeroSorte(23);
		dados.setAltura(new BigDecimal("1.70"));
		dados.setPeso(new BigDecimal("70.9"));
		dados.setSexo(0);
		dados.setInteresses(Arrays.asList("Tecnologia", "Esportes"));
		return ResponseEntity.ok(dados);
	}
	
	@PostMapping("/api/dados/salvar")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Void> salvar(@RequestBody @Valid DadosPessoais dados, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}

}
