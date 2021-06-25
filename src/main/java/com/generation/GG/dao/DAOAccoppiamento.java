package com.generation.GG.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.generation.GG.entities.Accoppiamento;
import com.generation.utility.dao.Database;

public class DAOAccoppiamento
{
	@Autowired
	private Database db;
	
	@Autowired
	private ApplicationContext context;
	
	public List<Accoppiamento> read(String query, String... params)
	{
		List<Accoppiamento> ris = new ArrayList<Accoppiamento>();
		List<Map<String, String>> righe = db.rows(query, params);
		
		for(Map<String, String> riga : righe)
		{
			Accoppiamento a = (Accoppiamento) context.getBean("accoppiamentoMappa",riga);
			ris.add(a);
		}
		return ris;	
	}//Fine di read()
	
	public List<Accoppiamento> leggiTutti(String id)
	{
		return read(	"select idprofilo1, idprofilo2\n"
					+ 	"from accoppiamento\n"
					+ 	"where idprofilo1 = ? or idprofilo2 = ?;", id);
	}//Fine di leggiTutti()
	
	public String leggiAccoppiamento(String id)
	{
		String query1 	=  "select idprofilo1 as id\n"
						+ "from accoppiamento\n"
						+ "where (idprofilo2 = ?) and controlloacc = 1 and esitoprofilo2 is null;";
				

		Map<String, String> riga = db.row(query1, id);
		System.out.println("RIGA 47 LEGGIACCOPPIAMENTO QUERY1: " + riga);
		String ris = "";
		
		if(riga == null)
		{
			System.out.println("RIGA 51: DENTRO L'IF!!!!!!");
			String query2 = "select idprofilo2 as id\n"
					+ "from accoppiamento\n"
					+ "where (idprofilo1 = ?) and controlloacc = 1 and esitoprofilo1 is null;";
			Map<String, String> riga2 = db.row(query2, id);
			if(riga2 == null)
			{
				System.out.println("RIGA 58 LEGGIACCOPPIAMENTO: " + riga2);
				return "";
			}
			ris = riga2.get("id");
		}
		else
		{
			ris = riga.get("id");
		}
		System.out.println("RIGA 67 LEGGIACCOPPIAMENTO: " + riga);
		
		return ris;
		
	}
	
	public boolean aggiornaAccoppiamento(String id, String esito)
	{
		System.out.println("ID PASSATO RIGA 75: " + id + " ---> esito: " + esito);
		String query1 	=  "select id\n"
						+ "from accoppiamento\n"
						+ "where (idprofilo2 = ?) and controlloacc = 1 and esitoprofilo2 is null";
				

		Map<String, String> rigaAgg = db.row(query1, id);
		System.out.println("RIGA 81 AGGIORNAACCOPPIAMENTO QUERY1: " + rigaAgg);
		
		if(rigaAgg == null)
		{
			System.out.println("RIGA 85: DENTRO L'IF!!!!!!");
			
			String query2 = "select id\n"
					+ "from accoppiamento\n"
					+ "where (idprofilo1 = ?) and controlloacc = 1 and esitoprofilo1 is null";
			Map<String, String> rigaAgg2 = db.row(query2, id);
			
			System.out.println("RIGA 92 AGGIORNAACCOPPIAMENTO QUERY2: " + rigaAgg2);
			
			rispostaAcc1(esito, rigaAgg2.get("id"));
			return true;
		}
		else
		{
			rispostaAcc2(esito, rigaAgg.get("id"));
			return true;
		}
		
		
	}
	
	public boolean bloccaAccoppiamento(String id, String idelim)
	{
		String query 	= "update accoppiamento\n"
						+ "set esitoprofilo1 = 0, esitoprofilo2 = 0\n"
						+ "where (idprofilo1 = ? or idprofilo2 = ?) and (idprofilo1 = ? or idprofilo2 = ?);";
		
		return db.update(query, id, id, idelim, idelim);
	}
	
	public boolean rispostaAcc1(String esito, String id)
	{
		String query 	= "update accoppiamento\n"
						+ "set esitoprofilo1 = ?\n"
						+ "where id = ?";
		
		return db.update(query, esito, id);
	}
	
	public boolean rispostaAcc2(String esito, String id)
	{
		String query 	= "update accoppiamento\n"
						+ "set esitoprofilo2 = ?\n"
						+ "where id = ?";
		
		return db.update(query, esito, id);
	}
	
	public boolean create(Accoppiamento a)
	{
		String query 	= "insert into Accoppiamento (idProfilo1, idProfilo2, esitoProfilo1, esitoProfilo2, data)"
						+ " values (?,?,?,?,?)";
		return db.update(query, a.getIdprofilo1() + "", a.getIdprofilo2() + "", 
							(a.isEsitoProfilo1()? "1" : "0"),
							(a.isEsitoProfilo2()? "1" : "0"),
							a.getData() + "");
	}//Fine di create()
	
	public boolean update(Accoppiamento a)
	{
		String query 	= "update Accoppiamento set idProfilo1 = ?, set idProfilo2 = ?,"
						+ " set esitoProfilo1 = ?, set esitoProfilo2 = ?,"
						+ " set data = ? where id = ?";
		return db.update(query, a.getIdprofilo1() + "", a.getIdprofilo2() + "", 
						(a.isEsitoProfilo1()? "1" : "0"),
						(a.isEsitoProfilo2()? "1" : "0"),
						a.getData() + "", a.getId() + "");
	}//Fine di update()
	
	
	public String leggiTipoAccopiamento(String idSessione, String idAccoppiato)
    {
        String query ="select controlloamore from accoppiamento where (idprofilo1 = ? or idprofilo2 = ?) and (idprofilo1 = ? or idprofilo2 = ?);";
        String ris = db.row(query, idSessione, idSessione, idAccoppiato, idAccoppiato).get("controlloamore");
        System.out.println("leggiTipoAccopiamento: " + ris);
        return ris;
    }
	
	public boolean delete(int id)
	{
		String query = "delete from Accoppiamento where id = ?";
		return db.update(query, id + "");
	}//Fine di delete()
}
