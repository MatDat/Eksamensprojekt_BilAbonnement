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
        // henter alle lokationer inklusiv postby info ved at joine lokation og postyby tables
        String sql = "SELECT * FROM bilabonnementDB.lokation " +
                "INNER JOIN bilabonnementDB.postby " +
                "ON lokation.lokation_id = postby.lokation_id";
        RowMapper<Lokation> rowMapper = new BeanPropertyRowMapper<>(Lokation.class);
        return template.query(sql, rowMapper);
    }

    public List<Lokation> hentLokationFraId(int lokation_id) {
        // henter en lokation, inklusiv postby info, udfra et givent lokation_id
        String sql = "SELECT * FROM bilabonnementDB.lokation, bilabonnementDB.postby WHERE lokation.lokation_id = ?" +
                " AND postby.lokation_id = ?";
        RowMapper<Lokation> rowMapper = new BeanPropertyRowMapper<>(Lokation.class);
        return template.query(sql, rowMapper, lokation_id, lokation_id);
    }

    public void tilfoejLokation(Lokation lokation){
        // tilføjer en lokation til lokation table, ved at tage nødvendige værdier fra et Lokation objekt
        // metoden kalder tilfoejPostby metoden efter, for at få postby info koblet på
        String sql = "INSERT INTO bilabonnementDB.lokation (lokation_navn, adresse) VALUES (?,?)";
        template.update(sql,lokation.getLokation_navn(),lokation.getAdresse());
        tilfoejPostby(lokation);
    }
    private void tilfoejPostby(Lokation lokation){
        // tilføjer postby info til postby table, ved at tage nødvendige værdier fra et Lokation objekt
        // Da man altid tilføjer postby info lige efter en lokation, laver vi en select all statement først for at få
        // lokation_id'et så postby info er koblet på lokationen lige tilføjet
        String select = "SELECT * FROM bilabonnementDB.lokation";
        RowMapper<Lokation> rowMapper = new BeanPropertyRowMapper<>(Lokation.class);
        List<Lokation> lokationList = template.query(select,rowMapper);

        int lokation_id = lokationList.get(lokationList.size()-1).getLokation_id();

        String sql = "INSERT INTO bilabonnementDB.postby (lokation_id, by_navn, postnummer) VALUES (?,?,?)";
        template.update(sql,lokation_id,lokation.getBy_navn(),lokation.getPostnummer());
    }

}
