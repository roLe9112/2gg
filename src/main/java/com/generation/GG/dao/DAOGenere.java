package com.generation.GG.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.generation.GG.entities.Genere;
import com.generation.utility.dao.Database;

public class DAOGenere 
{
    @Autowired
    private Database db;

    @Autowired
    private ApplicationContext context;

    public List<Genere> read(String query, String... params)
    {
        List<Genere> ris = new ArrayList<Genere>();
        List<Map<String, String>> righe = db.rows(query, params);

        for(Map<String, String> riga : righe)
        {
            Genere g = (Genere) context.getBean("genereMappa",riga);
            ris.add(g);
        }
        return ris;
    }//Fine di read()



    public boolean create(Genere g)
    {
        String query = "insert into genere (nome) values (?)";
        return db.update(query, g.getNome()); 

    }


}//ND