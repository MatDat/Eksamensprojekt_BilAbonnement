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
        //Henter en liste af alle brugere
        String sql = "SELECT bruger_id, brugernavn, kode FROM bilabonnementDB.bruger";
        RowMapper<Bruger> rowMapper = new BeanPropertyRowMapper<>(Bruger.class);
        List<Bruger> brugerListe = template.query(sql, rowMapper);
        return brugerListe;
    }

    public boolean logIndBruger(Bruger bruger) {
        //Logger en bruger ind i programmet
        String sql = "SELECT * FROM bilabonnementDB.bruger WHERE brugernavn = ? AND kode = ?";
        RowMapper<Bruger> rowMapper = new BeanPropertyRowMapper<>(Bruger.class);
        try {
            Bruger brugerLoggedInd = template.queryForObject(sql, rowMapper, bruger.getBrugernavn(), bruger.getKode());
            session.setAttribute("bruger", brugerLoggedInd);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }


    public boolean logIndAdmin(Bruger bruger) {
        //Logger admin ind på adminsiden
        String sql = "SELECT * FROM bilabonnementDB.bruger WHERE brugernavn = 'Admin' AND kode = ? AND brugernavn = ?";
        //^Denne linie tjekker om indtastede brugernavn & kode stemmeroverens med Admin brugernavnet.
        RowMapper<Bruger> rowMapper = new BeanPropertyRowMapper<>(Bruger.class);
        try {
            template.queryForObject(sql, rowMapper, bruger.getKode(), bruger.getBrugernavn());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean opretBruger(Bruger bruger) {
        //Opretter en ny bruger i databasen
        if (!brugerEksisterer(bruger)) {
            String sql = "INSERT INTO bilabonnementDB.bruger (bruger_id, brugernavn, kode) VALUES (?, ?, ?)";
            template.update(sql, bruger.getBruger_id(), bruger.getBrugernavn(), bruger.getKode());
            return true;
        }
        return false;
    }

    private boolean brugerEksisterer(Bruger bruger) {
        //Tjekker om er brugernavn eksisterer - bruges bla. til at man ikke kan oprette 2 brugere med samme navn
        String sql = "SELECT * FROM bilabonnementDB.bruger WHERE brugernavn = ?";
        RowMapper<Bruger> rowMapper = new BeanPropertyRowMapper<>(Bruger.class);
        try {
            template.queryForObject(sql, rowMapper, bruger.getBrugernavn());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean sletBruger(Bruger bruger) {
        //Sletter en bruger fra databasen
        String sql = "DELETE FROM bilabonnementDB.bruger WHERE bruger_id = ? AND bruger_id != 1";
        int paavirkedeRaekker = template.update(sql, bruger.getBruger_id());
        return paavirkedeRaekker > 0;   // Disse 2 linier sørger for at man ikke kan skrive et ID som ikke er der.
    }

}
