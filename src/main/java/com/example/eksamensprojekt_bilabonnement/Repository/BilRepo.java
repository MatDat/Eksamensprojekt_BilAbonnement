package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Model.BilTilstand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BilRepo {
    @Autowired
    JdbcTemplate template;

    public List<Bil> hentBiler() {
        String sql = "SELECT * FROM bilabonnementdb.bil WHERE bil_tilstand = 'RAPPORTKLAR'";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper);
    }

    public List<Bil> hentAlleBiler() {
        String sql = "SELECT * FROM bilabonnementDB.bil, bilabonnementDB.model, bilabonnementDB.maerke " +
                "WHERE bil.model_id = model.model_id AND model.maerke_id = maerke.maerke_id;";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper);
    }

    public List<Bil> hentBilerMedTilstand(String tilstand) {
        String sql = "SELECT * FROM bilabonnementDB.bil, bilabonnementDB.model, bilabonnementDB.maerke " +
                "WHERE bil_tilstand = ? AND bil.model_id = model.model_id AND model.maerke_id = maerke.maerke_id;";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper, tilstand);
    }

    public void opdaterBilTilstand(String bilTilstand, int vognnummer) {
        String sql = "UPDATE bil " +
                "SET bil_tilstand = ? " +
                "WHERE vognnummer = ?";
        template.update(sql, bilTilstand, vognnummer);
    }


    public boolean opretBil(Bil bil) {
        String sql = "INSERT INTO bil (vognnummer, stelnummer,maerke_id, model_id, staalpris, registrerings_afgift, " +
                "CO2_udledning, bil_tilstand) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        template.update(sql, bil.getVognnummer(), bil.getStelnummer(), bil.getMaerke_navn(), bil.getModel_id(), bil.getStaalpris(),
                bil.getRegistrerings_afgift(), bil.getCO2_udledning(), bil.getBilTilstand());
        return true;
    }
}
