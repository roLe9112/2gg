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
import com.generation.GG.dao.DAOInteressi;
import com.generation.GG.entities.Interessi;
import com.generation.utility.dao.Database;
import com.google.gson.Gson;

@Controller
@RequestMapping("/interessi")
public class InteressiController 
{
	@Autowired
	private DAOInteressi di;
	
	@Autowired
	private Database db;
	
	//@Autowired
	//private ApplicationContext context;
	
	
	@GetMapping("/test")
	@ResponseBody
	public String test()
	{
		Gson gson = new Gson();
		return gson.toJson(di.leggiTutti());
	}
	
	@GetMapping("/elenco")
	public String elenco(Model model)
	{

		model.addAttribute("elencoInteressi", di.leggiTutti());

		return "elenco.jsp";
	}//Fine di elenco()
	
	@GetMapping("/cercainteressi")
	public String cercaVg()
	{
		return "/interessi/cercainteressi.jsp";
	}

	@GetMapping("/risultato")
	public String risultato(@RequestParam String nameint, Model model)
	{
		
		List<Interessi> interessi = new ArrayList<Interessi>();
				
		interessi = di.cercaPerInteresse(nameint);
		
		//System.out.println("CONTENUTO interesse: " + interessi.toString());
		
		if(interessi.isEmpty())
			return "<h3>Nessun risultato trovato</h3>";

		model.addAttribute("listainteressi", interessi);
		return "/interessi/risultato.jsp";
		
	}//Fine di risultato()
	
	@GetMapping("/aggiungi")
	public String aggInteresse(@RequestParam String idinteresse, HttpSession session)
	{
		String profAttivo = session.getAttribute("idProfilo") + "";
		String query 	= 	"insert into listinteressi\n"
						+ 	"values\n"
						+ 	"(?,?);";
		
		if(db.update(query, profAttivo, idinteresse))
			return "redirect:/profilo/";
		else
			return "redirect:/errore";
	}
}
