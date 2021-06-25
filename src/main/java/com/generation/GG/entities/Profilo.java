package com.generation.GG.entities;
import java.sql.Date;
import java.util.List;
import com.generation.utility.entities.Entity;

public class Profilo extends Entity
{
	private String email;
	private String nick;
	private String password;
	private int idDomanda;
	private String risposta;
	private Date dob;
	private int idSesso;
	private int idOrientamento;
	private boolean tipoGamer;
	private String bio;
	private boolean amore;
	private String numeroTelefono;
	private String citta;
	private String provincia;
	private List<Entity> listInteressi;
	private int idIconaProfilo;
	private List<Entity> giochiPref;
	private List<Entity> giocaOra;
	private List<Entity> piattaPossedute;
	private String eta;
	
	
	
	
	//G&S
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdDomanda() {
		return idDomanda;
	}
	public void setIdDomanda(int idDomanda) {
		this.idDomanda = idDomanda;
	}
	public String getRisposta() {
		return risposta;
	}
	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getIdSesso() {
		return idSesso;
	}
	public void setIdSesso(int idSesso) {
		this.idSesso = idSesso;
	}
	public int getIdOrientamento() {
		return idOrientamento;
	}
	public void setIdOrientamento(int idOrientamento) {
		this.idOrientamento = idOrientamento;
	}
	public boolean isTipoGamer() {
		return tipoGamer;
	}
	public void setTipoGamer(boolean tipoGamer) {
		this.tipoGamer = tipoGamer;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public boolean isAmore() {
		return amore;
	}
	public void setAmore(boolean amore) {
		this.amore = amore;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public List<Entity> getListInteressi() {
		return listInteressi;
	}
	public void setListInteressi(List<Entity> listInteressi) {
		this.listInteressi = listInteressi;
	}
	public int getIdIconaProfilo() {
		return idIconaProfilo;
	}
	public void setIdIconaProfilo(int idIconaProfilo) {
		this.idIconaProfilo = idIconaProfilo;
	}
	public List<Entity> getGiochiPref() {
		return giochiPref;
	}
	public void setGiochiPref(List<Entity> giochiPref) {
		this.giochiPref = giochiPref;
	}
	public List<Entity> getGiocaOra() {
		return giocaOra;
	}
	public void setGiocaOra(List<Entity> giocaOra) {
		this.giocaOra = giocaOra;
	}
	public List<Entity> getPiattaPossedute() {
		return piattaPossedute;
	}
	public void setPiattaPossedute(List<Entity> piattaPossedute) {
		this.piattaPossedute = piattaPossedute;
	}		
	
	public String getEta() {
		return eta;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	
}//Fine classe Account
