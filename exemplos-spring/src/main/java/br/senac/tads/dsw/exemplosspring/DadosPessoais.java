package br.senac.tads.dsw.exemplosspring;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class DadosPessoais {

	private Integer id;

	@NotBlank(message = "Preenchimento do nome é obrigatório")
	private String nome;

	private String descricao;

	@Email
	@NotBlank
	private String email;

	private String senha;

	private String repetirSenha;

	@Min(value = 1)
	@Max(99)
	private int numeroSorte;

	@Digits(integer = 1, fraction = 2, message = "Altura com mais de 2 casas decimais")
	private BigDecimal altura;

	@Digits(integer = 3, fraction = 1, message = "Peso com mais de 1 casa decimal")
	private BigDecimal peso;

	@Past
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // ISO-8601
	private LocalDate dtNascimento;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) // 2000-03-24 00:00:00.999
	private LocalDateTime dtOperacao;

	private int sexo;

	@NotNull
	private List<String> interesses;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRepetirSenha() {
		return repetirSenha;
	}

	public void setRepetirSenha(String repetirSenha) {
		this.repetirSenha = repetirSenha;
	}

	public int getNumeroSorte() {
		return numeroSorte;
	}

	public void setNumeroSorte(int numeroSorte) {
		this.numeroSorte = numeroSorte;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public List<String> getInteresses() {
		return interesses;
	}

	public void setInteresses(List<String> interesses) {
		this.interesses = interesses;
	}

}
