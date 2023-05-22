package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Bruger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrugerRepo {//COMMENT

    @Autowired
    JdbcTemplate template;

    public List<Bruger> hentBrugerListe() {
        String sql = "SELECT bruger_id, brugernavn, kode FROM bruger";
        RowMapper<Bruger> rowMapper = new BeanPropertyRowMapper<>(Bruger.class);
        List<Bruger> brugerListe = template.query(sql, rowMapper);
        return brugerListe;
    }

    public boolean logIndBruger(Bruger bruger) { //TODO
        if (bruger.getBrugernavn() == null || bruger.getKode() == null
                || bruger.getBrugernavn().isEmpty() || bruger.getKode().isEmpty()) {
            return false; //^Nægter adgang hvis man prøver at logge ind uden at skrive noget
        }
        String sql = "SELECT COUNT(*) FROM bilabonnementDB.bruger WHERE brugernavn = ? AND kode = ?";
        try {
            int count = template.queryForObject(sql, Integer.class, bruger.getBrugernavn(), bruger.getKode());
            return count > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }


    public boolean logIndAdmin(Bruger bruger) {
        String sql = "SELECT * FROM bilabonnementDB.bruger WHERE brugernavn = 'Admin' AND kode = ? AND brugernavn = ?";
        //^Denne linie tjekker om indtastede brugernavn & kode stemmeroverens med Admin brugernavnet.
        try {
            template.queryForObject(sql, new BeanPropertyRowMapper<>(Bruger.class), bruger.getKode(), bruger.getBrugernavn());
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
