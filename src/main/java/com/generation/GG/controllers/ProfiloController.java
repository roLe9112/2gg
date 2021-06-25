package com.generation.GG.controllers;
import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.generation.GG.dao.DAOPiattaforme;
import com.generation.GG.dao.DAOProfili;
import com.generation.GG.dao.DAOVideogioco;
import com.generation.GG.entities.Piattaforma;
import com.generation.GG.entities.Profilo;
import com.generation.GG.entities.Videogioco;
//import com.generation.utility.dao.Database;

@Controller
@RequestMapping("/profilo")
public class ProfiloController
{
	@Autowired
	private DAOProfili dp;
	
//	@Autowired
//	private Database db;
	
	@Autowired
	private DAOPiattaforme dpt;
	
	@Autowired
	private DAOVideogioco dv;
	
	@Autowired
	private ApplicationContext context;
	
	@GetMapping("/")
	public String profilo(Model model, HttpSession session)
	{
		//String id = session.getAttribute("idProfilo") + "";
		System.out.println("RIGA 32 - ID SESSION PRE-MODEL: " + session.getAttribute("idProfilo"));
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
		{
			String idCurr = session.getAttribute("idProfilo") + "";
			Profilo selezionato = dp.profilo(session.getAttribute("idProfilo") + "");
			model.addAttribute(	"schedaprofilo", selezionato);
			System.out.println("RIGA 37 DOPO session.getAttribute(\"idProfilo\")");
			
			//Videogiochi preferiti
			String queryVgP = 	"select videogiochi.*\n"
					+ 	"from videogiochi\n"
					+ 	"inner join giochipref\n"
					+ 	"on videogiochi.id = giochipref.idgiochi\n"
					+ 	"where idprofilo = ?;";
			List<Videogioco> listVg = new ArrayList<Videogioco>();
			
			listVg.addAll(dv.read(queryVgP, idCurr));
			
			System.out.println("Numero elementi listPl: " + listVg.size());
			model.addAttribute("lsgiochipref", listVg);
			
			//Gioco ora
			String queryGOra = 	"select videogiochi.*\n"
					+ 	"from videogiochi\n"
					+ 	"inner join giocaora\n"
					+ 	"on videogiochi.id = giocaora.idgiochi\n"
					+ 	"where idprofilo = ?;";
			List<Videogioco> listVOra = new ArrayList<Videogioco>();
		
			listVOra.addAll(dv.read(queryGOra, idCurr));
			
			System.out.println("Numero elementi listPl: " + listVOra.size());
			model.addAttribute("lsgiocoora", listVOra);
				
			
			//Piattaforme
			String queryPt = 	"select piattaforma.*\n"
						+ 	"from piattaforma\n"
						+ 	"inner join piattapossedute\n"
						+ 	"on piattaforma.id = piattapossedute.idpiattaforma\n"
						+ 	"where idprofilo = ?;";
			List<Piattaforma> listPl = new ArrayList<Piattaforma>();
		
			listPl.addAll(dpt.read(queryPt, idCurr));
			
			System.out.println("Numero elementi listPl: " + listPl.size());
			model.addAttribute("lsplat", listPl);
			
			//Avatar
			model.addAttribute("linkimmagine", dp.indirizzoicona(idCurr));
			
			//Lista Interessi
			List<String> inteProf = dp.leggiIntProfilo(idCurr);
			model.addAttribute("listint", inteProf);
			
			return "profilo.jsp";
			
		}
	}
	
	@GetMapping("/formmodificaprofilo")
	public String formModificaProfilo(Model model, HttpSession session)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
		{
			Profilo selezionato = dp.profilo(session.getAttribute("idProfilo") + "");
			model.addAttribute(	"schedaprofilo", selezionato);
			System.out.println("RIGA 113 DOPO session.getAttribute(\"idProfilo\")");
			
			
		return "formmodificaprofilo.jsp";
		}
	}
	
	@PostMapping("/modificaprofilo")
	public String modificaProfilo(	@RequestParam Map<String,String> parametri, 
									@RequestParam("listinteressi") List<String> interessi,
									Model model, HttpSession session)
	{
		String id = session.getAttribute("idProfilo") + "";
		System.out.println(parametri);
		System.out.println(interessi);
		
		Profilo p = context.getBean(Profilo.class, parametri);
		
		System.out.println(p.toString());
		
		//Svuota interessi profilo
		dp.deleteInteressi(id);
		//Riempi con i nuovi
		if(interessi.size() > 0)
			dp.updateInteressi(id, interessi);
		
		System.out.println(p.toString());
		System.out.println("ID SESSION RIGA 56 PRE-IF: " + session.getAttribute("idProfilo"));
		if(dp.update(p, id))
		{
			System.out.println("RIGA 60 -> DOPO IF session.getAttribute(\"idProfilo\")");
			return "redirect:/";
		}
		else
		{
			System.out.println("Errore nel salvataggio dati del profilo");
			return "redirect:/errore";
		}
		
	}//Fine di nuovo()
	
	@PostMapping("/modificaavatar")
	public String modificaAvatar(	@RequestParam String idiconaprofilo,
									Model model, HttpSession session)
	{
		String id = session.getAttribute("idProfilo") + "";
		System.out.println("PARAMETRO idiconaprofilo: " + idiconaprofilo);
		
		System.out.println("ID ICON SCELTA: " + idiconaprofilo);
		System.out.println("ID SESSION RIGA 56 PRE-IF: " + session.getAttribute("idProfilo"));
		if(dp.updateAvatar(id, idiconaprofilo))
		{
			System.out.println("RIGA 60 -> DOPO IF session.getAttribute(\"idProfilo\")");
			return "redirect:/";
		}
		else
		{
			System.out.println("Errore nel salvataggio dati del profilo");
			return "redirect:/profilo/";
		}
		
	}//Fine di nuovo()
	
}
