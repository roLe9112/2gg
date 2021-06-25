package com.generation.GG.controllers;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.generation.GG.dao.DAOAccoppiamento;
import com.generation.GG.dao.DAOPiattaforme;
import com.generation.GG.dao.DAOProfili;
import com.generation.GG.dao.DAOVideogioco;
import com.generation.GG.entities.Piattaforma;
//import com.generation.GG.entities.Accoppiamento;
import com.generation.GG.entities.Profilo;
import com.generation.GG.entities.Videogioco;

@Controller
@RequestMapping("/match")
public class MatchController
{
	@Autowired
	private DAOProfili dp;
	
	@Autowired
	private DAOAccoppiamento dm;
	
	@Autowired
	private DAOPiattaforme dpt;
	
	@Autowired
	private DAOVideogioco dv;
	
//	@Autowired
//	private ApplicationContext context;
	
	@GetMapping("/")
	public String matchPagina(Model model, HttpSession session)
	{
		System.out.println("RIGA 32 - ID SESSION PRE-MODEL: " + session.getAttribute("idProfilo"));
		
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		
		String verAcc = dm.leggiAccoppiamento(session.getAttribute("idProfilo") + "");
		
		if(verAcc.equalsIgnoreCase(""))
		{
			return "redirect:/match/nomatch";
		}
		else
		{
			System.out.println("RIGA 40 - ID PROFILO ACCOPPIATO: " + verAcc);
			Profilo selezionato = dp.profilo(verAcc);
			
			model.addAttribute(	"schedaprofilo", selezionato);		
			
			
			//Videogiochi preferiti
			String queryVgP = 	"select videogiochi.*\n"
					+ 	"from videogiochi\n"
					+ 	"inner join giochipref\n"
					+ 	"on videogiochi.id = giochipref.idgiochi\n"
					+ 	"where idprofilo = ?;";
			List<Videogioco> listVg = new ArrayList<Videogioco>();
		
			listVg.addAll(dv.read(queryVgP, verAcc));
			
			System.out.println("Numero elementi listPl: " + listVg.size());
			model.addAttribute("lsgiochipref", listVg);
			
			//Gioco ora
			String queryGOra = 	"select videogiochi.*\n"
					+ 	"from videogiochi\n"
					+ 	"inner join giocaora\n"
					+ 	"on videogiochi.id = giocaora.idgiochi\n"
					+ 	"where idprofilo = ?;";
			List<Videogioco> listVOra = new ArrayList<Videogioco>();
		
			listVOra.addAll(dv.read(queryGOra, verAcc));
			
			System.out.println("Numero elementi listPl: " + listVOra.size());
			model.addAttribute("lsgiocoora", listVOra);
				
			
			//Piattaforme
			String queryPt = 	"select piattaforma.*\n"
						+ 	"from piattaforma\n"
						+ 	"inner join piattapossedute\n"
						+ 	"on piattaforma.id = piattapossedute.idpiattaforma\n"
						+ 	"where idprofilo = ?;";
			List<Piattaforma> listPl = new ArrayList<Piattaforma>();
		
			listPl.addAll(dpt.read(queryPt, verAcc));
			
			System.out.println("Numero elementi listPl: " + listPl.size());
			model.addAttribute("lsplat", listPl);
			
			model.addAttribute("linkimmagine", dp.indirizzoicona(verAcc));
			
			String esitoAccoppiamento = dm.leggiTipoAccopiamento(session.getAttribute("idProfilo") + "", verAcc);
            session.setAttribute("esitoamore", esitoAccoppiamento);
            
            List<String> inteProf = dp.leggiIntProfilo(verAcc);
			model.addAttribute("listint", inteProf);
			
			return "match.jsp";
		}
	}
	
	@GetMapping("/nomatch")
	public String noMatch()
	{
		return "nomatch.html";
	}
	
	@GetMapping("/accetta")
	public String accetta(HttpSession session)
	{
		String acc = session.getAttribute("idProfilo") + "";
		dm.aggiornaAccoppiamento(acc, "1");
		
		return "redirect:/match/";
	}
	
	@GetMapping("/rifiuta")
	public String rifiuta(HttpSession session)
	{
		String accRif = session.getAttribute("idProfilo") + "";
		dm.aggiornaAccoppiamento(accRif, "0");
		return "redirect:/match/";
	}
	
	
	
}
