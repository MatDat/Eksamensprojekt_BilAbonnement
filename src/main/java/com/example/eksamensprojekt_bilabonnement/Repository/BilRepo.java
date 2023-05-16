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
public class BilRepo {//COMMENT
    @Autowired
    JdbcTemplate template;



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
}
