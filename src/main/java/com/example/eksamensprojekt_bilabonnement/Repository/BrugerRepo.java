package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Bruger;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrugerRepo {
    @Autowired
    JdbcTemplate template;
    @Autowired
    HttpSession session;

    public List<Bruger> hentBrugerListe() {
        String sql = "SELECT bruger_id, brugernavn, kode FROM bruger";
        RowMapper<Bruger> rowMapper = new BeanPropertyRowMapper<>(Bruger.class);
        List<Bruger> brugerListe = template.query(sql, rowMapper);
        return brugerListe;
    }

    public boolean logIndBruger(Bruger bruger) {
        String sql = "SELECT * FROM bilabonnementDB.bruger WHERE brugernavn = ? AND kode = ?";
        try {
            Bruger brugerLoggedInd = template.queryForObject(sql, new BeanPropertyRowMapper<>(Bruger.class),
                    bruger.getBrugernavn(), bruger.getKode());
            session.setAttribute("bruger", brugerLoggedInd);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }


    public boolean logIndAdmin(Bruger bruger) {
        String sql = "SELECT * FROM bilabonnementDB.bruger WHERE brugernavn = ? AND kode = ?";
        //^Denne linie tjekker om indtastede brugernavn & kode stemmeroverens med Admin brugernavnet.
        try {
            template.queryForObject(sql, new BeanPropertyRowMapper<>(Bruger.class), bruger.getBrugernavn(), bruger.getKode());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean opretBruger(Bruger bruger) {
        if (!brugerEksisterer(bruger)) {
            String sql = "INSERT INTO bruger (bruger_id, brugernavn, kode) VALUES (?, ?, ?)";
            template.update(sql, bruger.getBruger_id(), bruger.getBrugernavn(), bruger.getKode());
            return true;
        }
        return false;
    }

    private boolean brugerEksisterer(Bruger bruger) {
        String sql = "SELECT * FROM bruger WHERE brugernavn = ?";
        RowMapper<Bruger> rowMapper = new BeanPropertyRowMapper<>(Bruger.class);
        try {
            template.queryForObject(sql, rowMapper, bruger.getBrugernavn());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean sletBruger(Bruger bruger) { //TODO brug required in html
        String sql = "DELETE FROM bruger WHERE bruger_id = ? AND bruger_id != 1";
        int paavirkedeRaekker = template.update(sql, bruger.getBruger_id()); //Gemmer antal af
        return paavirkedeRaekker > 0;    //Hvis paavirkedeRaekker er større end 0, returns true - ellers false ofc
    }

}
