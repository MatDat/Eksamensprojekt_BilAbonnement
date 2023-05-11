package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Kunde;
import com.example.eksamensprojekt_bilabonnement.Model.Skade;
import com.example.eksamensprojekt_bilabonnement.Model.Skaderapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkadeRepo {

    @Autowired
    JdbcTemplate template;
    //Modtag skade data og opret ny rapport i databasen

    public void nySkade(Skade skade) {
        String sql = "INSERT INTO skade (beskrivelse, pris, skaderapport_id) " +
                "VALUES (?, ?, ?)";
        template.update(sql, skade.getBeskrivelse(), skade.getPris(), skade.getSkaderapport_id());
    }

    public void nySkadeRapport(Skaderapport skaderapport) {
        String sql = "INSERT INTO skaderapport (skaderapport_dato, kunde_id, vognnummer, bruger_id) " +
                "VALUES (?, ?, ?, ?)";
        template.update(sql, skaderapport.getSkaderapport_dato(),
                skaderapport.getKunde_id(), skaderapport.getVognnummer(), skaderapport.getBruger_id());
    }

    public List<Skaderapport> hentSkaderapporter() {
        String sql = "SELECT * FROM skaderapport";
        RowMapper<Skaderapport> rowMapper = new BeanPropertyRowMapper<>(Skaderapport.class);
        return template.query(sql,rowMapper);
    }

    public List<Skade> hentSkader(int skaderapport_id) {
        String sql = "SELECT * FROM skade WHERE skaderapport_id = ?";

        RowMapper<Skade> rowMapper = new BeanPropertyRowMapper<>(Skade.class);
        return template.query(sql,rowMapper, skaderapport_id);
    }
}
