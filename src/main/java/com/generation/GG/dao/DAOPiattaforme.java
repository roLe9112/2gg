package com.generation.GG.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.generation.GG.entities.Piattaforma;
import com.generation.utility.dao.Database;

public class DAOPiattaforme 
{
	@Autowired
	private Database db;
	
	@Autowired
	private ApplicationContext context;
	
	public List<Piattaforma> read(String query, String...params)
	{
		List<Piattaforma> ris = new ArrayList<Piattaforma>();
		List<Map<String,String>> righe = db.rows(query, params);
		
		for(Map<String,String> riga : righe)
		{
			Piattaforma c = (Piattaforma) context.getBean("piattaformaMappa", riga);
			ris.add(c);
		}
		return ris;
	}//Fine read()
	
	public Piattaforma leggiPiatt(String id)
	{
		
		return read("select * from piattaforma where id = " + id).get(0);
		
	}
	
	public List<Piattaforma> leggiTutti()
	{
		return read("select * from piattaforma");
	}//Fine di leggiTutti()
	
	//Tramite questo metodo restituiamo un oggetto Videogioco partendo dal nome
	public List<Piattaforma> cercaPerPiattaforma(String nome, String id)
	{
		try
		{
			return read(	"select * from piattaforma\r\n"
						+ 	"where (piattaforma like '%" + nome + "%' or produttore like '%" + nome +"%')\r\n"
						+ 	"and id not in (select idpiattaforma from piattapossedute where idprofilo = ?)", id);	
		}
		catch(IndexOutOfBoundsException e)
		{
			return null;
		}
	}//Fine di cercaPerPiattaforma()
	
}
