package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Lokation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LokationRepo {//COMMENT

    @Autowired
    JdbcTemplate template;

    public List<Lokation> hentAlleLokationer(){
        String sql = "SELECT * FROM bilabonnementDB.lokation;";
        RowMapper<Lokation> rowMapper = new BeanPropertyRowMapper<>(Lokation.class);
        return template.query(sql,rowMapper);
    }

    public Lokation hentLokationFraId(int lokation_id){
        String sql = "SELECT * FROM bilabbonnementDB.lokation WHERE lokation_id = ?";
        return template.queryForObject(sql, Lokation.class,lokation_id);
    }

}
