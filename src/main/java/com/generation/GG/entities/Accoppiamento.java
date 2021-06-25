package com.generation.GG.entities;

import java.sql.Date;

import com.generation.utility.entities.Entity;

public class Accoppiamento extends Entity
{
	private int idprofilo1;
	private int idprofilo2;
	private boolean esitoProfilo1;
	private boolean esitoProfilo2;
	private Date data;
	
	
	public int getIdprofilo1() {
		return idprofilo1;
	}
	public void setIdprofilo1(int idprofilo1) {
		this.idprofilo1 = idprofilo1;
	}
	public int getIdprofilo2() {
		return idprofilo2;
	}
	public void setIdprofilo2(int idprofilo2) {
		this.idprofilo2 = idprofilo2;
	}
	public boolean isEsitoProfilo1() {
		return esitoProfilo1;
	}
	public void setEsitoProfilo1(boolean esitoProfilo1) {
		this.esitoProfilo1 = esitoProfilo1;
	}
	public boolean isEsitoProfilo2() {
		return esitoProfilo2;
	}
	public void setEsitoProfilo2(boolean esitoProfilo2) {
		this.esitoProfilo2 = esitoProfilo2;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
}