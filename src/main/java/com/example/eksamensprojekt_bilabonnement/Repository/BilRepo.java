package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Model.Bil_Model;
import com.example.eksamensprojekt_bilabonnement.Model.Maerke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BilRepo {//COMMENT
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

    public List<Maerke> hentAlleMaerker(){
        String sql = "SELECT * FROM bilabonnementDB.maerke";
        RowMapper<Maerke> rm = new BeanPropertyRowMapper<>(Maerke.class);
        return template.query(sql, rm);
    }

    public List<Maerke> hentMaerkeNavnFraID(int maerke_id){
        String sql = "SELECT maerke_navn FROM bilabonnementDB.maerke " +
                "WHERE maerke_id = ?";
        RowMapper<Maerke> rm = new BeanPropertyRowMapper<>(Maerke.class);
        return template.query(sql, rm, maerke_id);
    }
    public List<Bil_Model> hentModelNavnFraID(int model_id){
        String sql = "SELECT model_navn FROM bilabonnementDB.model " +
                "WHERE model_id = ?";
        RowMapper<Bil_Model> rm = new BeanPropertyRowMapper<>(Bil_Model.class);
        return template.query(sql, rm, model_id);
    }

    public List<Bil_Model> hentValgteModeller(String maerke_id){
        String sql = "SELECT * FROM bilabonnementDB.model " +
                "WHERE maerke_id = ?";
        RowMapper<Bil_Model> rm = new BeanPropertyRowMapper<>(Bil_Model.class);
        return template.query(sql, rm, maerke_id);
    }

    public List<Bil_Model> hentAlleBil_Models(){
        String sql = "SELECT * FROM bilabonnementDB.model";
        RowMapper<Bil_Model> rm = new BeanPropertyRowMapper<>(Bil_Model.class);
        return template.query(sql, rm);
    }

    public List<Bil> hentBilerMedTilstand(String tilstand) {
        String sql = "SELECT * FROM bilabonnementDB.bil, bilabonnementDB.model, bilabonnementDB.maerke " +
                "WHERE bil_tilstand = ? AND bil.model_id = model.model_id AND model.maerke_id = maerke.maerke_id;";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper, tilstand);
    }
    public List<Bil> hentBilerMedBraendstof(String braendstof) {
        String sql = "SELECT * FROM bilabonnementDB.bil, bilabonnementDB.model, bilabonnementDB.maerke " +
                "WHERE braendstof = ? AND bil.model_id = model.model_id AND model.maerke_id = maerke.maerke_id;";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper, braendstof);
    }

    public void opdaterBilTilstand(String bilTilstand, int vognnummer) {
        String sql = "UPDATE bil " +
                "SET bil_tilstand = ? " +
                "WHERE vognnummer = ?";
        template.update(sql, bilTilstand, vognnummer);
    }


    public boolean opretBil(Bil bil) {
        String sql = "INSERT INTO bil (model_id, stelnummer, staalpris, registrerings_afgift, " +
                "CO2_udledning, bil_tilstand) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        template.update(sql, bil.getModel_id(), bil.getStelnummer(), bil.getStaalpris(),
                bil.getRegistrerings_afgift(), bil.getCO2_udledning(), String.valueOf(bil.getBilTilstand()));
        return true;
    }
}
