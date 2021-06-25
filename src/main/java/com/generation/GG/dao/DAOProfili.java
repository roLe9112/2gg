package com.generation.GG.dao;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.generation.GG.entities.Profilo;
import com.generation.utility.dao.Database;


public class DAOProfili 
{
	@Autowired
	private Database db;
	
	@Autowired
	private ApplicationContext context;
	
	
	public List<Profilo> read(String query, String...params)
	{
		List<Profilo> ris = new ArrayList<Profilo>();
		List<Map<String,String>> righe = db.rows(query, params);
		
		for(Map<String,String> riga: righe)
		{
			Profilo p = (Profilo) context.getBean("profiloMappa",riga);
			ris.add(p);
		}
		return ris;
	}//Fine read
	
//	public Profilo readProfilo(String id)
//    {
//		Map<String, String> riga = read("select profili.* from profili where id = ? ", id);
//		Profilo p = context.getBean(Profilo.class, 
//									);
//        return p;
//    }//Fine readProfilo()
	
	public Profilo profilo(String id)
    {
        String query =  "select * "    		+
                        "from profili "    	+
                        "where id = ? "    	;

        Map<String, String> riga = db.row(query, id);

        //System.out.println(riga);

        Profilo ris = context.getBean(Profilo.class, riga);
        return ris;

    }
	
	public List<Profilo> readGenteConCuiChattare(String id)
	{
		String query = "select concat(if(idprofilo1 != ? , idprofilo1, \"\"),if(idprofilo2 != ?, idprofilo2, \"\")) as id "
				+ "from accoppiamento where controlloacc = 1 and esitoprofilo1 = 1 and esitoprofilo2 = 1;";
		List<Profilo> ris = new ArrayList<Profilo>();
		List<Profilo> ris2 = new ArrayList<Profilo>();
		
		ris.addAll(read(query,id,id));
		//System.out.println("ris dentro ciclo for riga 68 "  + ris);
		
		query = "select id, nick from profili where id = ? ;";
		//System.out.println("ris riga 52" + ris);
		for(Profilo c : ris)
		{
			ris2.addAll(read(query,c.getId() + ""));
		}
		//System.out.println("\n------------------------------------------------\nris 2 riga 77 " + ris2);
		return ris2;
	}
	
	public boolean create(Profilo p)
	{
		String query = "insert into profili (email, nick, password, iddomanda, risposta, idsesso, dob) values (?,?,md5(?),?,?,?,?)";
		return db.update(query, p.getEmail(), p.getNick(), p.getPassword(), 
							p.getIdDomanda() + "", p.getRisposta(), p.getIdSesso() + "", p.getDob() + "");
	}//Fine create()
	
	
	
	public boolean update(Profilo p, String id)
	{
		//System.out.println("\nPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" + p.toString() + "ID:" + id);
		String query 	= "update profili set nick = ?, "
						+ "idOrientamento = ?, tipoGamer = ?, bio = ?, "
						+ "amore = ?, numeroTelefono = ?, citta = ?, provincia = ? "
						+ " where id = ?";
		return db.update(query,  p.getNick(), p.getIdOrientamento() + "",
						(p.isTipoGamer() ?  "1" : "0"), p.getBio(), (p.isAmore() ? "1"  : "0"), 
						p.getNumeroTelefono() + "", p.getCitta(), p.getProvincia(), id);
	}//Fine update()
	
	
	public boolean updateAvatar(String id, String avatar)
	{
		String query 	= "update profili set idiconaprofilo = ? "
						+ " where id = ?";
		return db.update(query, avatar, id);
	}
	
//	public boolean update(Profilo p, String id)
//	{
//		System.out.println("\nPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP\n" + p.toString() + "\n--------------\n ID:" + id);
//		String query 	= "update profili set nick = ?, "
//						+ " tipoGamer = ?, bio = ?, "
//						+ " amore = ?, numeroTelefono = ?, citta = ?, provincia = ? "
//						+ " where id = ?";
//		System.out.println(query);
//		return db.update(query,  p.getNick(),
//						(p.isTipoGamer() ?  "1" : "0"), p.getBio(), (p.isAmore() ? "1"  : "0"), 
//						p.getNumeroTelefono() + "", p.getCitta(), p.getProvincia(), id);
//	}//Fine update()
	
	
	
	public boolean delete(int id)
	{
		String query = "delete from profili where id = ?";
		return db.update(query, id + "");
	}//Fine delete()
	
	public boolean login(String email, String password)
	{
		String query 	= "select count(*) as n\r\n"
						+ "    	from profili\r\n"
						+ "    	where email = ?\r\n"
						+ "   	and\r\n"
						+ "    	password = md5(?);";
		
		Map<String,String> riga = db.row(query, email, password);
		int controllo = Integer.parseInt(riga.get("n"));
		
		if(controllo > 0)
		{
			return true;
		}	
		else
			return false;
	}//Fine login()
	
	//Interessi
	public boolean updateInteressi(String id, List<String> param)
	{
		String query = "insert into listinteressi (idprofilo, idinteresse) values (?,?)";
		
		for(String s : param)
			if(!s.equalsIgnoreCase("0"))
				db.update(query, id, s);
		
		return true;
	}
	
	public boolean deleteInteressi(String id)
	{
		String query = "delete from listinteressi where idprofilo = ?";
		
		return db.update(query, id);
	}
	
	public List<String> leggiIntProfilo(String id)
	{
		String query = 	"select interessi.interesse as interesse\r\n"
					+ 	"from listinteressi inner join interessi\r\n"
					+ 	"on listinteressi.idinteresse = interessi.id\r\n"
					+ 	"where listinteressi.idprofilo = ?";
		
		List<String> ris = new ArrayList<String>();
		List<Map<String,String>> righe = db.rows(query, id);
		
		for(Map<String,String> riga: righe)
		{
			ris.add(riga.get("interesse"));
		}
		
		return ris;
		
	}

	public String indirizzoicona(String id)
	{
		String query = "select linkicona from icone inner join profili on profili.idiconaprofilo = icone.id where profili.id = ?";
		String ris = db.row(query, id).get("linkicona");
		
		return ris;
	}
	
	
}
