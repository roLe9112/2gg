package com.generation.GG.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.generation.GG.entities.Domanda;
import com.generation.utility.dao.Database;

public class DAODomande
{
	@Autowired
	private Database db;
	
	@Autowired
	private ApplicationContext context;
	
	public List<Domanda> read(String query, String... params)
	{
		List<Domanda> ris = new ArrayList<Domanda>();
		List<Map<String, String>> righe = db.rows(query, params);
		
		for(Map<String, String> riga : righe)
		{
			Domanda d = (Domanda) context.getBean("domandaMappa",riga);
			ris.add(d);
		}
		return ris;	
	}//Fine di read()
	
	public List<Domanda> leggiTutti()
	{
		return read("select * from domande");
	}//Fine di leggiTutti()
	
}//Fine classe DAODomande
