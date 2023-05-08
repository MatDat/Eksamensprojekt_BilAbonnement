package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Bruger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class BrugerRepo {

    @Autowired
    JdbcTemplate template;

    public boolean loginBruger(Bruger bruger) {
        String sql = "SELECT * FROM bilabonnementDB.bruger WHERE brugernavn = ? AND kode = ?";
        RowMapper<Bruger> rm = new BeanPropertyRowMapper<>(Bruger.class);
        try {
            Bruger b = template.queryForObject(sql, rm, bruger.getBrugernavn(), bruger.getKode());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean opretBruger(Bruger bruger) {
        if (!eksistererBruger(bruger)) {
            String sql = "INSERT INTO bruger (bruger_id, brugernavn, kode) VALUES (?, ?, ?)";
            template.update(sql, bruger.getBruger_id(), bruger.getBrugernavn(), bruger.getKode());
            return true;
        } else {
            return false;
        }
    }

    public boolean eksistererBruger(Bruger bruger) {
        String sql = "SELECT * FROM bruger WHERE brugernavn = ?";
        RowMapper<Bruger> rm = new BeanPropertyRowMapper<>(Bruger.class);
        try {
            Bruger b = template.queryForObject(sql, rm, bruger.getBrugernavn());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }

    }
}
