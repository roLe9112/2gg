package com.generation.GG.entities;

import java.sql.Date;
import java.sql.Time;

import com.generation.utility.entities.Entity;

public class Chat extends Entity
{
	private String messaggio;
	private Date data;
	private Time ora;
	private int IdDestinatario;
	private int IdMittente;
	private String nomeVis;
	
	
	public String getNomeVis() {
		return nomeVis;
	}
	public void setNomeVis(String nomeVis) {
		this.nomeVis = nomeVis;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getOra() {
		return ora;
	}
	public void setOra(Time ora) {
		this.ora = ora;
	}
	public int getIdDestinatario() {
		return IdDestinatario;
	}
	public void setIdDestinatario(int idDestinatario) {
		IdDestinatario = idDestinatario;
	}
	public int getIdMittente() {
		return IdMittente;
	}
	public void setIdMittente(int idMittente) {
		IdMittente = idMittente;
	}
	
	
}
