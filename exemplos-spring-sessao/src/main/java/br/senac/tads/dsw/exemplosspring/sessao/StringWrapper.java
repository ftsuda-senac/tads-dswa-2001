package br.senac.tads.dsw.exemplosspring.sessao;

// Encapsula uma String para ser alterada quando usada como
// @ModelAttribute do Spring.
// String não pode ser diretamente alterada
// Ver https://stackoverflow.com/a/8215448
public class StringWrapper {
	
	private String valor;
	
	public StringWrapper() {
		
	}
	
	public StringWrapper(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
