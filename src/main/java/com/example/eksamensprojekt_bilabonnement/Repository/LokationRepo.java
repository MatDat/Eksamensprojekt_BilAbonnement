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

    public List<Lokation> hentAlleLokationer() {
        String sql = "SELECT * FROM bilabonnementDB.lokation;";
        RowMapper<Lokation> rowMapper = new BeanPropertyRowMapper<>(Lokation.class);
        return template.query(sql, rowMapper);
    }

    public List<Lokation> hentLokationFraId(int lokation_id) {//TODO Emil
//        String sql = "SELECT * FROM bilabonnementDB.lokation, bilabonnementDB.postby WHERE lokation.lokation_id = ?" +
//                " AND lokation.lokation_id = postby.lokation_id";
        String sql = "SELECT * FROM bilabonnementDB.lokation, bilabonnementDB.postby WHERE lokation.lokation_id = ?" +
                " AND postby.lokation_id = ?";
        RowMapper<Lokation> rowMapper = new BeanPropertyRowMapper<>(Lokation.class);
        return template.query(sql, rowMapper, lokation_id, lokation_id);
    }

}
