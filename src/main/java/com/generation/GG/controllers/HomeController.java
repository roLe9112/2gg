package com.generation.GG.controllers;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.generation.GG.dao.DAOProfili;
import com.generation.GG.entities.Profilo;


@Controller
public class HomeController
{
	@Autowired
	private DAOProfili dp;
	
	@Autowired
	private ApplicationContext context;	
	
	@GetMapping("/")
	public String home(HttpSession session)
	{
		if(session.getAttribute("login") == null)
			return "Principale.html";
		return "redirect:/profilo/";
	}
	
	@GetMapping("/formlogin")
	public String formlogin()
	{
		return "formlogin.html";
	}
	
	@PostMapping("/login")
	public String login( 	@RequestParam("email") String e,
							@RequestParam("password") String p,
							HttpSession session)
	{
		if(dp.login(e, p))
		{
			String query 	= "select id\r\n"
							+ "from profili\r\n"
							+ "where email = ?\r\n"
							+ "and\r\n"
							+ "password = md5(?);";
			String idRacc = dp.read(query, e, p).get(0).getId() + "";
			
			System.out.println("Id raccolto: " + idRacc);
			
			session.setAttribute("login", "ok");
			session.setAttribute("idProfilo", idRacc);
			System.out.println("Id SESSION: " + session.getAttribute("idProfilo"));
			return "redirect:/";
		}
		else
			return "redirect:/formlogin";
	}//fine login()
	
	@GetMapping("/dashboard")
	public String dashboard()
	{
		return "dashboard.html";
	}
	
	
	@GetMapping("/formregistrazione")
	public String formregistrazione()
	{
		return "formregistrazione.html";
	}
	
	@GetMapping("/registrazione")
	public String registrazione(Profilo a)
	{
		if(dp.create(a))
		{
			return "redirect:/login";
		}
		else
			return "redirect:/formregistrazione";
	}//Fine registrazione()
	
	@PostMapping("/nuovoutente")
	public String nuovoUtente(@RequestParam Map<String,String> parametri)
	{
		System.out.println(parametri);
		
		Profilo p = context.getBean(Profilo.class, parametri);
		
		System.out.println(p.toString());
		
		if(dp.create(p))
			return "redirect:/success";
		else
		{
			System.out.println("Errore nel salvataggio del profilo");
			return "redirect:/errore";
		}
		
	}//Fine di nuovoUtente()
	
	@GetMapping("/success")
	public String successo()
	{
		return "success.html";
	}
	
	@GetMapping("/errore")
	public String errore()
	{
		return "errore.html";
	}
	
	
//	@GetMapping("/json")
//	@ResponseBody
//	public String stampaProfilo(int id)
//	{
//		Gson gson = new Gson();
//		String query = "select * from profili where id = ?";
//		return gson.toJson(dp.read(query, id + ""));
//	}//Fine di stampaProfili()
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.setAttribute("login", null);
		session.setAttribute("username", null);
		session.setAttribute("idProfilo", null);
		return "Principale.html";
	}
	
	
	
}//Fine classe HomeController