package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Model.BilModel;
import com.example.eksamensprojekt_bilabonnement.Model.Maerke;
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

    public List<Bil> hentAlleBiler() {
        //Henter en liste af alle biler
        String sql = "SELECT * FROM bilabonnementDB.bil, bilabonnementDB.model, bilabonnementDB.maerke " +
                "WHERE bil.model_id = model.model_id AND model.maerke_id = maerke.maerke_id;";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper);
    }

    public List<Maerke> hentAlleMaerker() {
        //Henter en liste af alle mærker
        String sql = "SELECT * FROM bilabonnementDB.maerke";
        RowMapper<Maerke> rowMapper = new BeanPropertyRowMapper<>(Maerke.class);
        return template.query(sql, rowMapper);
    }

    public List<Maerke> hentMaerkeNavnFraID(int maerke_id) {
        //Henter mærke navn ud fra mærke ID
        String sql = "SELECT maerke_navn FROM bilabonnementDB.maerke " +
                "WHERE maerke_id = ?";
        RowMapper<Maerke> rowMapper = new BeanPropertyRowMapper<>(Maerke.class);
        return template.query(sql, rowMapper, maerke_id);
    }

    public List<BilModel> hentModelNavnFraID(int model_id) {
        //Henter model navn ud fra model ID
        String sql = "SELECT model_navn FROM bilabonnementDB.model " +
                "WHERE model_id = ?";
        RowMapper<BilModel> rowMapper = new BeanPropertyRowMapper<>(BilModel.class);
        return template.query(sql, rowMapper, model_id);
    }

    public List<BilModel> hentValgteModeller(String maerke_id) {
        //Henter en liste af alle modeller af et valgt mærke
        String sql = "SELECT * FROM bilabonnementDB.model " +
                "WHERE maerke_id = ?";
        RowMapper<BilModel> rowMapper = new BeanPropertyRowMapper<>(BilModel.class);
        return template.query(sql, rowMapper, maerke_id);
    }

    public List<Bil> hentBilerMedTilstand(String tilstand) {
        //Henter en liste af alle biler med en bestemt tilstand
        String sql = "SELECT * FROM bilabonnementDB.bil, bilabonnementDB.model, bilabonnementDB.maerke " +
                "WHERE bil_tilstand = ? AND bil.model_id = model.model_id AND model.maerke_id = maerke.maerke_id;";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper, tilstand);
    }

    public List<Bil> hentBilerMedBraendstof(String braendstof) {
        //Henter en liste af alle biler med en bestemt brændstofs type
        String sql = "SELECT * FROM bilabonnementDB.bil, bilabonnementDB.model, bilabonnementDB.maerke " +
                "WHERE braendstof = ? AND bil.model_id = model.model_id AND model.maerke_id = maerke.maerke_id;";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper, braendstof);
    }

    public void opdaterBilTilstand(String bilTilstand, int vognnummer) {
        //Opdaterer en bils tilstand
        String sql = "UPDATE bilabonnementDB.bil " +
                "SET bil_tilstand = ? " +
                "WHERE vognnummer = ?";
        template.update(sql, bilTilstand, vognnummer);
    }


    public boolean opretBil(Bil bil) {
        //Opretter en ny bil i databasen
        String sql = "INSERT INTO bilabonnementDB.bil (model_id, stelnummer, staalpris, registrerings_afgift, " +
                "CO2_udledning, bil_tilstand) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        template.update(sql, bil.getModel_id(), bil.getStelnummer(), bil.getStaalpris(),
                bil.getRegistrerings_afgift(), bil.getCO2_udledning(), String.valueOf(bil.getBilTilstand()));
        return true;
    }
}
