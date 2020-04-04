package br.senac.tads.dsw.exemplosspring;

public class ItemImagem {

	private String src;

	private String alt;

	public ItemImagem() {

	}

	public ItemImagem(String src, String alt) {
		this.src = src;
		this.alt = alt;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

}
