package br.senac.tads.dsw.agenda.web;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senac.tads.dsw.agenda.entidades.Contato;
import br.senac.tads.dsw.agenda.entidades.Email;
import br.senac.tads.dsw.agenda.entidades.Telefone;
import br.senac.tads.dsw.agenda.repository.ContatoRepository;
import br.senac.tads.dsw.agenda.repository.EmailRepository;
import br.senac.tads.dsw.agenda.repository.TelefoneRepository;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

	@Autowired
	private ContatoRepository contatoRepo;

	@Autowired
	private TelefoneRepository telefoneRepo;

	@Autowired
	private EmailRepository emailRepo;

	@GetMapping
	public ModelAndView listar() {
		List<Contato> resultados = contatoRepo.findAll();
		ModelAndView mv = new ModelAndView("lista-template");
		mv.addObject("itens", resultados);
		return mv;
	}
	
	@GetMapping("/busca")
	public ModelAndView buscar(@RequestParam("termoBusca") String termoBusca) {
		List<Contato> resultados = contatoRepo.searchNativo(termoBusca.toLowerCase());
		ModelAndView mv = new ModelAndView("lista-template");
		mv.addObject("msgBusca", "Resultados para <b>" + termoBusca + "</b>");
		mv.addObject("itens", resultados);
		return mv;
	}

	@GetMapping("/novo")
	public ModelAndView incluirNovo() {
		ModelAndView mv = new ModelAndView("form-template");
		Contato c = new Contato();
		mv.addObject("item", c);
		return mv;
	}

	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Integer id) {
		Optional<Contato> optContato = contatoRepo.findById(id);
		if (optContato.isPresent()) {
			ModelAndView mv = new ModelAndView("form-template");
			Contato c = optContato.get();
			Set<Telefone> telefones = c.getTelefones();
			for (Telefone t : telefones) {
				c.setTelefoneTemp(t.getNumero());
			}
			Set<Email> emails = c.getEmails();
			for (Email e : emails) {
				c.setEmailTemp(e.getEndereco());
			}
			mv.addObject("item", c);
			return mv;
		} else {
			// Contato não existe.
			ModelAndView mv = new ModelAndView("lista-template");
			mv.addObject("msgErro", "Contato não encontrado");
			return mv;
		}
	}

	@PostMapping("/salvar")
	@Transactional
	public String salvar(@ModelAttribute("item") @Valid Contato cEnviado, BindingResult bindingResult, 
			RedirectAttributes redirAttr) {
		
		if (bindingResult.hasErrors()) {
			// Reapresenta o formulário indicando os campos com erros
			return "form-template";
		}
		
		if (cEnviado.getId() != null) {
			// Alterando informação existente
			Optional<Contato> optContato = contatoRepo.findById(cEnviado.getId());
			if (optContato.isPresent()) {
				// Atualiza informações;
				Contato c = optContato.get();
				c.setNome(cEnviado.getNome());
				c.setApelido(cEnviado.getApelido());
				c.setDataNascimento(cEnviado.getDataNascimento());

				// Força setar valores temp por causa das validações
				c.setTelefoneTemp(cEnviado.getTelefoneTemp());
				c.setEmailTemp(cEnviado.getEmailTemp());
				
				c = contatoRepo.save(c);
				
				Set<Telefone> telefones = c.getTelefones();
				if (telefones != null && !telefones.isEmpty()) {
					for (Telefone t : telefones) {
						t.setNumero(cEnviado.getTelefoneTemp());
						t = telefoneRepo.save(t);
						break;
					}
				}
				
				Set<Email> emails = c.getEmails();
				if (emails != null && !emails.isEmpty()) {
					for (Email e : emails) {
						e.setEndereco(cEnviado.getEmailTemp());
						e = emailRepo.save(e);
						break;
					}
				}
			} else {
				redirAttr.addFlashAttribute("msgErro", "Contato informado não existe");
				return "redirect:/agenda";
			}
		} else {
			// Incluindo novo contato
			cEnviado.setDataCadastro(LocalDateTime.now());
			cEnviado = contatoRepo.save(cEnviado);
			
			Telefone t = new Telefone();
			t.setNumero(cEnviado.getTelefoneTemp());
			t.setTipo("PESSOAL");
			t.setContato(cEnviado);
			
			t = telefoneRepo.save(t);
			cEnviado.setTelefones(new HashSet<>(Arrays.asList(t)));
			
			Email e = new Email();
			e.setEndereco(cEnviado.getEmailTemp());
			e.setTipo("PESSOAL");
			e.setContato(cEnviado);
			
			e = emailRepo.save(e);
			cEnviado.setEmails(new HashSet<>(Arrays.asList(e)));
		}
		redirAttr.addFlashAttribute("msgSucesso", "Contato cadastrado com sucesso");
		return "redirect:/agenda";
	}

	@PostMapping("/remover/{id}")
	@Transactional
	public String remover(@PathVariable("id") Integer id, RedirectAttributes redirAttr) {
		Optional<Contato> optContato = contatoRepo.findById(id);
		if (!optContato.isPresent()) {
			// Contato nao encontrado
			redirAttr.addFlashAttribute("msgErro", "Contato não encontrado");
			return "redirect:/agenda";
		}
		Contato c = optContato.get();
		emailRepo.deleteAll(c.getEmails());
		telefoneRepo.deleteAll(c.getTelefones());
		contatoRepo.delete(c);
		
		redirAttr.addFlashAttribute("msgSucesso", "Contato removido com sucesso");
		return "redirect:/agenda";
	}

}
