package br.senac.tads.dsw.agenda.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotBlank
	private String nome;

	@Column
	@NotBlank
	private String apelido;

	@Column
	@DateTimeFormat(iso = ISO.DATE) // ISO-8601
	@Past
	private LocalDate dataNascimento;

	@Column
	private LocalDateTime dataCadastro;

	@OneToMany(mappedBy = "contato")
	private Set<Telefone> telefones;

	@OneToMany(mappedBy = "contato")
	private Set<Email> emails;

	@Transient
	@NotBlank
	private transient String telefoneTemp;

	@Transient
	@NotBlank
	@javax.validation.constraints.Email
	private transient String emailTemp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public String getTelefoneTemp() {
		return telefoneTemp;
	}

	public void setTelefoneTemp(String telefoneTemp) {
		this.telefoneTemp = telefoneTemp;
	}

	public String getEmailTemp() {
		return emailTemp;
	}

	public void setEmailTemp(String emailTemp) {
		this.emailTemp = emailTemp;
	}

}
