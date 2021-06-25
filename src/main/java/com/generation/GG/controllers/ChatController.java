package com.generation.GG.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.GG.dao.DAOAccoppiamento;
import com.generation.GG.dao.DAOChat;
import com.generation.GG.dao.DAOPiattaforme;
import com.generation.GG.dao.DAOProfili;
import com.generation.GG.dao.DAOVideogioco;
import com.generation.GG.entities.Chat;
import com.generation.GG.entities.Piattaforma;
import com.generation.GG.entities.Profilo;
import com.generation.GG.entities.Videogioco;

@Controller
@RequestMapping("/chat")
public class ChatController 
{	
	@Autowired
	private DAOChat dc;
	
	@Autowired
	private DAOProfili dp;
	
	@Autowired
	private DAOAccoppiamento dm;
	
	@Autowired
	private DAOPiattaforme dpt;
	
	@Autowired
	private DAOVideogioco dv;
	
	@Autowired
	private ApplicationContext context;	
	
	@GetMapping("/")
	public String readPersone(@RequestParam Map<String,String> parametri,Model model, HttpSession session)
	{	
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
		{	
			session.setAttribute("idMittente", session.getAttribute("idProfilo") + "");
			String idMittente = session.getAttribute("idMittente") + "";
			
			session.setAttribute("idDestinatario", parametri.get("idDestinatario"));
			String idDestinatario = session.getAttribute("idDestinatario") + "";
	//		session.setAttribute("idDestinatario", parametri.get("idDestinatario"));
	//		String idDestinatario = session.getAttribute("idDestinatario") + "";
			//System.out.println("LIST DEL DAOPROFILO" + dp.readGenteConCuiChattare(idMittente));
			model.addAttribute("elencoPersone", dp.readGenteConCuiChattare(idMittente));
			model.addAttribute("chatSingola", dc.readChatSingola(idMittente, idDestinatario));
			//model.addAttribute("chatSingola", dc.readChatSingola(idMittente, idDestinatario));
			//System.out.println("STAMPA MODEL RIGA 41 " + model);
			//Avatar
			//model.addAttribute("linkimmagine", dp.indirizzoicona(idDestinatario));
			
			return "chat.jsp";
		}
	}
	
	@GetMapping("/msgTo")
	public String messaggio(@RequestParam Map<String,String> parametri, HttpSession session)
	{
		//System.out.println("Parametri riga " + parametri);
		session.setAttribute("idMittente", session.getAttribute("idProfilo") + "");
		String idMittente = session.getAttribute("idMittente") + "";
		
		//System.out.println("SESSION CHAT SINGOLA"+ session.getAttribute("idDestinatario"));
		Chat c = context.getBean(Chat.class, parametri);
		//System.out.println("CONTENUTO C riga 42:" + c);
		//System.out.println("Contenuto id riga 43:" + id);
		
		//System.out.println(c);
		if(dc.create(c, session.getAttribute("idDestinatario") + "", idMittente))
		{
			System.out.println("Messaggio creato");
			return "redirect:/chat/chatDopoInvia";
		}
		else
		{
			System.out.println("Messaggio fallito");
			return "redirect:/chat/chatDopoInvia";
		}
	}//Fine msgTo
	
	@GetMapping("/chatSingola")
	public String readChat(Model model, HttpSession session, @RequestParam Map<String,String> parametri)
	{
		session.setAttribute("idMittente", session.getAttribute("idProfilo") + "");
		String idMittente = session.getAttribute("idMittente") + "";
		
		session.setAttribute("idDestinatario", parametri.get("idDestinatario"));;
		String idDestinatario = session.getAttribute("idDestinatario") + "";
		//System.out.println("SESSION CHAT SINGOLA"+ session.getAttribute("idDestinatario"));
		
		//System.out.println("VALORE DI ID riga 81 " + idDestinatario);
		model.addAttribute("elencoPersone", dp.readGenteConCuiChattare(idMittente));
		model.addAttribute("chatSingola", dc.readChatSingola(idMittente, idDestinatario));
		
		return "chatsingola.jsp";
	}
	
	@GetMapping("/chatDopoInvia")
	public String readChatDopoInvio(Model model, HttpSession session)
	{
		model.addAttribute("elencoPersone", dp.readGenteConCuiChattare(session.getAttribute("idMittente") + ""));
		model.addAttribute("chatSingola", dc.readChatSingola(session.getAttribute("idMittente") + "", session.getAttribute("idDestinatario") + ""));
		return "chatsingola.jsp";
		
	}
	
	@GetMapping("/profilo")
	public String profiloChat(@RequestParam("profilo") String profilo, Model model, HttpSession session)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		
		
		if(profilo.equalsIgnoreCase(""))
		{
			return "redirect:/match/nomatch";
		}
		else
		{
			System.out.println("RIGA 40 - ID PROFILO ACCOPPIATO: " + profilo);
			Profilo selezionato = dp.profilo(profilo);
			
			model.addAttribute(	"schedaprofilo", selezionato);		
			
			
			//Videogiochi preferiti
			String queryVgP = 	"select videogiochi.*\n"
					+ 	"from videogiochi\n"
					+ 	"inner join giochipref\n"
					+ 	"on videogiochi.id = giochipref.idgiochi\n"
					+ 	"where idprofilo = ?;";
			List<Videogioco> listVg = new ArrayList<Videogioco>();
		
			listVg.addAll(dv.read(queryVgP, profilo));
			
			System.out.println("Numero elementi listPl: " + listVg.size());
			model.addAttribute("lsgiochipref", listVg);
			
			//Gioco ora
			String queryGOra = 	"select videogiochi.*\n"
					+ 	"from videogiochi\n"
					+ 	"inner join giocaora\n"
					+ 	"on videogiochi.id = giocaora.idgiochi\n"
					+ 	"where idprofilo = ?;";
			List<Videogioco> listVOra = new ArrayList<Videogioco>();
		
			listVOra.addAll(dv.read(queryGOra, profilo));
			
			System.out.println("Numero elementi listPl: " + listVOra.size());
			model.addAttribute("lsgiocoora", listVOra);
				
			
			//Piattaforme
			String queryPt = 	"select piattaforma.*\n"
						+ 	"from piattaforma\n"
						+ 	"inner join piattapossedute\n"
						+ 	"on piattaforma.id = piattapossedute.idpiattaforma\n"
						+ 	"where idprofilo = ?;";
			List<Piattaforma> listPl = new ArrayList<Piattaforma>();
		
			listPl.addAll(dpt.read(queryPt, profilo));
			
			System.out.println("Numero elementi listPl: " + listPl.size());
			model.addAttribute("lsplat", listPl);
			
			model.addAttribute("linkimmagine", dp.indirizzoicona(profilo));
			
			String esitoAccoppiamento = dm.leggiTipoAccopiamento(session.getAttribute("idProfilo") + "", profilo);
            session.setAttribute("esitoamore", esitoAccoppiamento);
            
            List<String> inteProf = dp.leggiIntProfilo(profilo);
			model.addAttribute("listint", inteProf);
			
			return "/chat/profilochat.jsp";
		}
	}
	
	@GetMapping("/successblock")
	public String sucessBlock()
	{
		return "successelimina.html";
	}
	
	@GetMapping("/rifiuta")
	public String rifiuta(@RequestParam("profilo") String profilo, HttpSession session)
	{
		String accRif = session.getAttribute("idProfilo") + "";
		dm.bloccaAccoppiamento(profilo, accRif);
		return "redirect:/chat/successblock";
	}

}
