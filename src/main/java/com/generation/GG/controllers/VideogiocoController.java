package com.generation.GG.controllers;
import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.generation.GG.dao.DAOVideogioco;
import com.generation.GG.entities.Videogioco;
import com.generation.utility.dao.Database;
import com.google.gson.Gson;

@Controller
@RequestMapping("/videogiochi")
public class VideogiocoController
{
	@Autowired
	private DAOVideogioco dv;
	
	@Autowired
	private Database db;
	
	//@Autowired
	//private ApplicationContext context;
	
	
	@GetMapping("/test")
	@ResponseBody
	public String test(HttpSession session)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
		{
		Gson gson = new Gson();
		return gson.toJson(dv.leggiTutti());
		}
	}
	
	
	@GetMapping("/cercavg")
	public String cercaVg(HttpSession session)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
			return "/videogiochi/cercavg.jsp";
	}

	@GetMapping("/risultato")
	public String risultato(@RequestParam String namevg, HttpSession session, Model model)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
		{
			if(namevg.length() <= 3)
				return "redirect:/videogiochi/cercavg";
			
			List<Videogioco> vgs = new ArrayList<Videogioco>();
			String profAttivo = session.getAttribute("idProfilo") + "";
			vgs = dv.cercaPerNome(namevg, profAttivo);
			
			//System.out.println("CONTENUTO vgs: " + vgs.toString());

			if(vgs.size() == 0)
				return "redirect:/videogiochi/cercavg";
			else
			{
			model.addAttribute("listavg", vgs);
			return "/videogiochi/risultato.jsp";
			}
		}
		
	}//Fine di risultato()
	
	@GetMapping("/aggiungi")
	public String aggVg(@RequestParam String idvgscelto, HttpSession session)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
		{
		System.out.println("RIGA 74 idvgscelto: " + idvgscelto);
		String profAttivo = session.getAttribute("idProfilo") + "";
		System.out.println("RIGA 76 profAttivo: " + profAttivo);
		String query 	= 	"insert into giochipref\n"
						+ 	"values\n"
						+ 	"(?,?);";
		
		if(db.update(query, profAttivo, idvgscelto))
			return "redirect:/profilo/";
		else
			return "redirect:/errore";
		}
	}
	
	@GetMapping("/rimuovi")
	public String rimVg(@RequestParam String idvgpref, HttpSession session)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
		{
			System.out.println("RIGA 115 idvgpref: " + idvgpref);
			String profAttivo = session.getAttribute("idProfilo") + "";
			System.out.println("RIGA 117 profAttivo: " + profAttivo);
			String query 	= 	"delete from giochipref where idgiochi = ? and idprofilo =  ?;";
			
			if(db.update(query, idvgpref, profAttivo))
				return "redirect:/profilo/";
			else
				return "redirect:/errore";
		}
	}

	@GetMapping("/aggora")
	public String aggGiocoOra(@RequestParam String idgiocaora, HttpSession session)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
		{
		System.out.println("RIGA 74 idvgscelto: " + idgiocaora);
		String profAttivo = session.getAttribute("idProfilo") + "";
		System.out.println("RIGA 76 profAttivo: " + profAttivo);
		String query 	= 	"insert into giocaora\n"
						+ 	"values\n"
						+ 	"(?,?);";
		
		if(db.update(query, profAttivo, idgiocaora))
			return "redirect:/profilo/";
		else
			return "redirect:/errore";
		}
	}
	
	@GetMapping("/rimuoviora")
	public String rimVgOra(@RequestParam String idvgora, HttpSession session)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
		{
			System.out.println("RIGA 115 idvgpref: " + idvgora);
			String profAttivo = session.getAttribute("idProfilo") + "";
			System.out.println("RIGA 117 profAttivo: " + profAttivo);
			String query 	= 	"delete from giocaora where idgiochi = ? and idprofilo =  ?;";
			
			if(db.update(query, idvgora, profAttivo))
				return "redirect:/profilo/";
			else
				return "redirect:/errore";
		}
	}
	
	@GetMapping("/cercavgora")
	public String cercaVgOra(HttpSession session)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
			return "/videogiochi/cercavgora.jsp";
	}

	@GetMapping("/risultatoora")
	public String risultatoOra(@RequestParam String namevg, HttpSession session, Model model)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
		{
			if(namevg.length() <= 3)
				return "redirect:/videogiochi/cercavgora";
			
			List<Videogioco> vgora = new ArrayList<Videogioco>();
			String profAttivo = session.getAttribute("idProfilo") + "";
			vgora = dv.cercaPerNomeOra(namevg, profAttivo);
			
			//System.out.println("CONTENUTO vgs: " + vgs.toString());

			if(vgora.size() == 0)
				return "redirect:/videogiochi/cercavgora";
			else
			{
			model.addAttribute("listavgora", vgora);
			return "/videogiochi/risultatoora.jsp";
			}
		}
		
	}//Fine di risultato()

}