package com.generation.GG.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.generation.GG.entities.Videogioco;
import com.generation.utility.dao.Database;

public class DAOVideogioco
{
	@Autowired
	private Database db;
	
	@Autowired
	private ApplicationContext context;
	
	public List<Videogioco> read(String query, String... params)
	{

		List<Videogioco> ris = new ArrayList<Videogioco>();
		//Qui trasformiamo ogni mappa in una Videogioco diversa
		List<Map<String, String>> righe = db.rows(query, params);
		
		//Cicliamo tutte le mappe riga per riga.
		//Ogni riga è un record del DB.
		for(Map<String, String> riga : righe)
		{
			Videogioco s = context.getBean(Videogioco.class, riga);
			ris.add(s);
		}//Fine di for
		return ris;
	}//Fine di read()
	
	//Creiamo una variante del metodo read()
	public List<Videogioco> leggiTutti()
	{
		return read("select * from videogiochi");
	}
	
	//Cerca per VIDEOGIOCHI PREFERITI
	public List<Videogioco> cercaPerNome(String nome, String id)
	{
		try
		{
			return read("select * from videogiochi\r\n"
					+ 	"where (nome like '%" + nome + "%' or genere like '%" + nome +"%')\r\n"
					+ 	"and id not in (select idgiochi from giochipref where idprofilo = ?)", id);	
		}
		catch(IndexOutOfBoundsException e)
		{
			return null;
		}
	}//Fine di cercaPerNome()
	
	//Cerca per VIDEOGIOCHI GIOCATI ORA
	public List<Videogioco> cercaPerNomeOra(String nome, String id)
	{
		try
		{
			return read("select * from videogiochi\r\n"
					+ 	"where (nome like '%" + nome + "%' or genere like '%" + nome +"%')\r\n"
					+ 	"and id not in (select idgiochi from giocaora where idprofilo = ?)", id);	
		}
		catch(IndexOutOfBoundsException e)
		{
			return null;
		}
	}//Fine di cercaPerNome()


}