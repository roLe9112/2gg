package com.generation.GG.entities;
import com.generation.utility.entities.Entity;

public class Videogioco extends Entity
{
	private String nome;
	private String immagine;
	private String genere;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	
	
}//Fine classe Videogioco
