package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Lokation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LokationRepo {

    @Autowired
    JdbcTemplate template;

    public List<Lokation> hentAlleLokationer(){
        String sql = "SELECT * FROM bilabonnementDB.lokation;";
        RowMapper<Lokation> rowMapper = new BeanPropertyRowMapper<>(Lokation.class);
        return template.query(sql,rowMapper);
    }

}
