package br.senac.tads.dsw.agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import br.senac.tads.dsw.agenda.entidades.Contato;
import br.senac.tads.dsw.agenda.entidades.Email;
import br.senac.tads.dsw.agenda.entidades.Telefone;
import br.senac.tads.dsw.agenda.repository.ContatoRepository;
import br.senac.tads.dsw.agenda.repository.EmailRepository;
import br.senac.tads.dsw.agenda.repository.TelefoneRepository;

@SpringBootApplication
public class AgendaApplication {

	@Autowired
	private ContatoRepository contatoRepo;

	@Autowired
	private TelefoneRepository telefoneRepo;

	@Autowired
	private EmailRepository emailRepo;

	@EventListener
	public void tratarContextRefresh(ContextRefreshedEvent event) {
		
		if (contatoRepo.count() == 0) {
			// Nenhum registro no banco de dados -> Forçar a criação de 1 registro
			Contato c = new Contato();
			c.setNome("Fulano da Silva");
			c.setApelido("fusil");
			c.setDataNascimento(LocalDate.of(2000, 05, 15));
			c.setDataCadastro(LocalDateTime.now());
			
			c = contatoRepo.save(c);

			Telefone t = new Telefone();
			t.setTipo("PESSOAL");
			t.setNumero("11 99999-9999");

			Email e = new Email();
			e.setTipo("PESSOAL");
			e.setEndereco("fulano@teste.com");
			
			t.setContato(c);
			c.setTelefones(new HashSet<>(Arrays.asList(t)));
			t = telefoneRepo.save(t);
			
			e.setContato(c);
			c.setEmails(new HashSet<>(Arrays.asList(e)));
			e = emailRepo.save(e);			
			
		}
		
	}

	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);
	}

}
