package com.generation.GG;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.generation.GG.dao.DAOAccoppiamento;
import com.generation.GG.dao.DAOChat;
import com.generation.GG.dao.DAOInteressi;
import com.generation.GG.dao.DAOPiattaforme;
import com.generation.GG.dao.DAOProfili;
import com.generation.GG.dao.DAOVideogioco;
import com.generation.GG.entities.*;
import com.generation.utility.dao.Database;

@Configuration
public class Context 
{
	@Bean
	public Database db()
	{
		return new Database("datinggamer","root","lrnz91lzzrn");
	}
	
	@Bean
    @Scope("prototype")
    public Profilo profiloMappa(Map<String,String> riga)
    {
        Profilo p = new Profilo();
        p.fromMap(riga);
        return p;
    }
	
	@Bean
	@Scope("prototype")
	public Sesso sessoMappa(Map<String,String> riga)
	{
		Sesso s = new Sesso();
		s.fromMap(riga);
		return s;
	}
	
	@Bean
	@Scope("prototype")
	public Genere genereMappa(Map<String,String> riga)
	{
		Genere g = new Genere();
		g.fromMap(riga);
		return g;
	}
	
	@Bean
	@Scope("prototype")
	public Videogioco videogiocoMappa(Map<String,String> riga)
	{
		Videogioco v = new Videogioco();
		v.fromMap(riga);
		return v;
	}
	
	@Bean
	@Scope("prototype")
	public Chat chatMappa(Map<String,String> riga)
	{
		Chat c = new Chat();
		c.fromMap(riga);
		return c;
	}
	
	@Bean
	@Scope("prototype")
	public Interessi interessiMappa(Map<String,String> riga)
	{
		Interessi i = new Interessi();
		i.fromMap(riga);
		return i;
	}
	
	@Bean
    @Scope("prototype")
    public Accoppiamento accoppiamentoMappa(Map<String,String> riga)
    {
        Accoppiamento a = new Accoppiamento();
        a.fromMap(riga);
        return a;
    }
	
	@Bean
    @Scope("prototype")
    public Piattaforma piattaformaMappa(Map<String,String> riga)
    {
        Piattaforma p = new Piattaforma();
        p.fromMap(riga);
        return p;
    }
	
	@Bean
    @Scope("prototype")
    public Domanda domandaMappa(Map<String,String> riga)
    {
		Domanda d = new Domanda();
        d.fromMap(riga);
        return d;
    }
	
	@Bean
    @Scope("prototype")
    public DAOProfili daoProfilo()
    {
        return new DAOProfili();
    }
	
	@Bean
    @Scope("prototype")
    public DAOAccoppiamento daoAccoppiamento()
    {
        return new DAOAccoppiamento();
    }
	
	@Bean
	@Scope("prototype")
	public DAOChat daoChat()
	{
		return new DAOChat();
	}
	
	@Bean
	@Scope("prototype")
	public DAOInteressi daoInteressi()
	{
		return new DAOInteressi();
	}
	
	@Bean
	@Scope("prototype")
	public DAOVideogioco daoVideogioco()
	{
		return new DAOVideogioco();
	}
	
	@Bean
	@Scope("prototype")
	public DAOPiattaforme daoPiattaforma()
	{
		return new DAOPiattaforme();
	}
	
}//Fine classe Context