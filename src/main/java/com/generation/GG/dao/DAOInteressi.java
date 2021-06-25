package com.generation.GG.dao;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.generation.GG.entities.Interessi;
import com.generation.utility.dao.Database;

public class DAOInteressi
{
	@Autowired
	private Database db;
	
	@Autowired
	private ApplicationContext context;
	
	public List<Interessi> read(String query, String... params)
	{
		List<Interessi> ris = new ArrayList<Interessi>();
		List<Map<String, String>> righe = db.rows(query, params);
		
		for(Map<String, String> riga : righe)
		{
			Interessi c = (Interessi) context.getBean("interessiMappa",riga);
			ris.add(c);
		}
		return ris;	
	}//Fine di read()
	
	public List<Interessi> leggiTutti()
	{
		return read("select * from interessi");
	}//Fine di leggiTutti()
	
	
	//Tramite questo metodo restituiamo un oggetto Videogioco partendo dal nome
	public List<Interessi> cercaPerInteresse(String nome)
	{
		try
		{
			return read("select interesse from interessi where interesse LIKE '%" + nome + "%'");	
		}
		catch(IndexOutOfBoundsException e)
		{
			return null;
		}
	}//Fine di cercaPerInteresse()
	
}//Fine classe DAOInteressi
