package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Kunde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KundeRepo {//COMMENT

    @Autowired
    JdbcTemplate template;

    public List<Kunde> hentAlleKunder(){
        String sql = "SELECT * FROM bilabonnementDB.kunde;";
        RowMapper<Kunde> rowMapper = new BeanPropertyRowMapper<>(Kunde.class);
        return template.query(sql,rowMapper);
    }

}
