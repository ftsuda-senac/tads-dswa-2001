package br.senac.tads.dsw.exemplosspring;

public class Item {
	
	private int valor;
	
	private String texto;
	
	private boolean multi10;
	
	public Item() {
		
	}
	
	public Item(int valor, String texto) {
		this.valor = valor;
		this.texto = texto;
		multi10 = (valor % 10 == 0);
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public boolean isMulti10() {
		return multi10;
	}

	public void setMulti10(boolean multi10) {
		this.multi10 = multi10;
	}

}
