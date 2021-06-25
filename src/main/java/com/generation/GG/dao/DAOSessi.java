package com.generation.GG.dao;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.generation.GG.entities.Sesso;
import com.generation.utility.dao.Database;

public class DAOSessi 
{
    @Autowired
    private Database db;

    @Autowired
    ApplicationContext context;

    public List<Sesso> read(String query, String... params)
    {
        List<Sesso> ris = new ArrayList<Sesso>();
        List<Map<String,String>> righe = db.rows(query, params);

        for(Map<String,String> riga :righe)
        {
            Sesso s= (Sesso) context.getBean("sessoMappa", riga);
            ris.add(s);
        }
        return ris;

    }// fine read()

    public boolean create(Sesso s)
    {
        String query = "insert into sesso (sesso) values (?)";

        return db.update(query, s.getSesso());
    }
}