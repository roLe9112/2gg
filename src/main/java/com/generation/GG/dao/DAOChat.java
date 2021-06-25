package com.generation.GG.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.generation.GG.entities.Chat;
import com.generation.utility.dao.Database;

public class DAOChat 
{
	@Autowired
	private Database db;
	
//	@Autowired
//	private DAOProfili dp;
	
	@Autowired
	private ApplicationContext context;
	
	public List<Chat> read(String query, String...params)
	{
		List<Chat> ris = new ArrayList<Chat>();
		List<Map<String,String>> righe = db.rows(query, params);
		
		for(Map<String,String> riga : righe)
		{
			Chat c = (Chat) context.getBean("chatMappa", riga);
			ris.add(c);
		}
		return ris;
	}//Fine read()
	
//	public List<Chat> readAll()
//	{
//		return read("select chat.*, mittente.*, destinatario.* from profili as destinatario\r\n"
//				+ "inner join \r\n"
//				+ "chat on\r\n"
//				+ "destinatario.id = chat.idDestinatario\r\n"
//				+ "inner join\r\n"
//				+ "profili as mittente\r\n"
//				+ "	on\r\n"
//				+ "mittente.id = chat.idMittente;");
//	}//Fine
	
	public List<Chat> readChatSingola(String idsession, String iddestinatario)
	{
		String query = "select messaggio, data, ora, idmittente, profili.nick as nomeVis\r\n"
				+ "from chat inner join profili on profili.id = chat.idmittente\r\n"
				+ "where (iddestinatario = ?  or idmittente = ?) and (iddestinatario = ? or idmittente = ?)\r\n"
				+ "order by chat.id;";
		List<Chat> ris = new ArrayList<Chat>();
		ris.addAll(read(query, idsession,idsession, iddestinatario, iddestinatario));
		//System.out.println("STAMPA RIS : " + ris);
		return ris;
	}//Fine readChatSingola()
	
	public List<Chat> readChat(String idsession, String iddestinatario)
	{
		String query = "select messaggio, data, ora, idmittente, profili.nick as nomeVis\r\n"
				+ "from chat inner join profili on profili.id = chat.idmittente\r\n"
				+ "where (iddestinatario = ?  or idmittente = ?) and (iddestinatario = ? or idmittente = ?)\r\n"
				+ "order by chat.id;";
		List<Chat> ris = new ArrayList<Chat>();
		ris.addAll(read(query, idsession,idsession, iddestinatario, iddestinatario));
		//System.out.println("STAMPA RIS : " + ris);
		return ris;
	}//Fine readChatSingola()
	
	
//	public List<Chat> readChatMittente(String idMittente)
//	{
//		String query = "select *\r\n"
//				+ "from profili as destinatario\r\n"
//				+ "	inner join \r\n"
//				+ "    chat on\r\n"
//				+ "   destinatario.id"
//				+ "= chat.idDestinatario\r\n"
//				+ "    inner join \r\n"
//				+ "    profili as mittente\r\n"
//				+ "	on\r\n"
//				+ "    ? = chat.idMittente\r\n";
//		return read(query, idMittente);
//	}		
//	
//	public List<Chat> readChat(String idDestinatario, String idMittente)
//	{
//		String query = "select chat.* from profili as destinatario inner join chat on ? = chat.idDestinatario inner join"
//				+ " profili as mittente on ? = chat.idMittente;";
//		return read(query, idDestinatario, idMittente);
//	}
	
	public boolean create(Chat c, String idDestinatario,String idMittente)
	{
		LocalDate dataLocale = LocalDate.now();
		LocalTime oraLocale = LocalTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateTimeFormatter oraFormat = DateTimeFormatter.ofPattern("HH:mm");
		String query = "insert into chat(messaggio, data, ora, iddestinatario, idmittente) values (?,?,?,?,?)";
		return db.update(query, c.getMessaggio(), (dateFormat.format(dataLocale)), 
				(oraFormat.format(oraLocale)), idDestinatario, idMittente);
	}//Fine create()
	
//	public String toJSON(String idDestinatario, String idMittente)
//	{
//		String query 	= "select * from profili as destinatario inner join chat on destinatario.id = chat.idDestinario"
//						+ " inner join profili as mittente on mittente.id = chat.idMittente";
//		String ris = "[";
//		for(Chat c : read(query))
//		{
//			ris += c.toJSON() + ",";
//		}
//		
//		String [] ris2 = ris.split("");
//		ris = "";
//		
//		for(int i = 0; i <(ris2.length - 1); i++)
//			ris += ris2[i];
//		
//		ris += "]";
//		return ris;
//	}
	
}
