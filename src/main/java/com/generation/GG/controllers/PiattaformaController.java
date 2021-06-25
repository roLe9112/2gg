package com.generation.GG.controllers;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.generation.GG.dao.DAOPiattaforme;
import com.generation.GG.entities.Piattaforma;
import com.generation.utility.dao.Database;
import com.google.gson.Gson;

@Controller
@RequestMapping("/piattaforma")
public class PiattaformaController 
{
	@Autowired
	private DAOPiattaforme dp;
	
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
		return gson.toJson(dp.leggiTutti());
		}
	}
	
	@GetMapping("/cercapiatt")
	public String cercaVg(HttpSession session)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
			return "/piattaforma/cercapiatt.jsp";
	}

	@GetMapping("/risultato")
	public String risultato(@RequestParam String nameplat, HttpSession session, Model model)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
		{
			if(nameplat.length() <= 1)
				return "redirect:/piattaforma/cercapiatt";
			
			List<Piattaforma> piatt = new ArrayList<Piattaforma>();
			String profAttivo = session.getAttribute("idProfilo") + "";		
			piatt.addAll(dp.cercaPerPiattaforma(nameplat, profAttivo));
			
			//System.out.println("CONTENUTO interesse: " + interessi.toString());
			
			if(piatt.size() == 0)
				return "redirect:/piattaforma/cercapiatt";
			else
			{
				model.addAttribute("listapiatt", piatt);
				return "/piattaforma/risultato.jsp";
			}
		
		}
		
	}//Fine di risultato()
	
	@GetMapping("/aggiungi")
	public String aggPlat(@RequestParam String idplat, HttpSession session)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
		{
			System.out.println("RIGA 74 idplat: " + idplat);
			String profAttivo = session.getAttribute("idProfilo") + "";
			System.out.println("RIGA 76 profAttivo: " + profAttivo);
			String query 	= 	"insert into piattapossedute\n"
							+ 	"values\n"
							+ 	"(?,?);";
			
			if(db.update(query, profAttivo, idplat))
				return "redirect:/profilo/";
			else
				return "redirect:/errore";
		}
	}
	
	@GetMapping("/rimuovi")
	public String rimPlat(@RequestParam String idplat, HttpSession session)
	{
		if(session.getAttribute("login") == null)
		{
			return "redirect:/formlogin";
		}
		else
		{
			System.out.println("RIGA 115 idplat: " + idplat);
			String profAttivo = session.getAttribute("idProfilo") + "";
			System.out.println("RIGA 117 profAttivo: " + profAttivo);
			String query 	= 	"delete from piattapossedute where idpiattaforma = ? and idprofilo =  ?;";
			
			if(db.update(query, idplat, profAttivo))
				return "redirect:/profilo/";
			else
				return "redirect:/errore";
		}
	}
	
}
