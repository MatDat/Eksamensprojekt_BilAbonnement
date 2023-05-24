package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Kunde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KundeRepo {

    @Autowired
    JdbcTemplate template;

    public List<Kunde> hentAlleKunder() {
        //Metoden returnerer en liste med alle Kunder
        String sql = "SELECT * FROM bilabonnementDB.kunde;";
        RowMapper<Kunde> rowMapper = new BeanPropertyRowMapper<>(Kunde.class);
        return template.query(sql, rowMapper);
    }

    public List<Kunde> hentKundeFraId(int kunde_id) {
        //Metoden returnerer en Kunde
        String sql = "SELECT * FROM bilabonnementDB.kunde WHERE kunde_id = ?";
        RowMapper<Kunde> rowMapper = new BeanPropertyRowMapper<>(Kunde.class);
        return template.query(sql, rowMapper, kunde_id);
    }

    public void opretKunde(Kunde kunde) {
        //Metoden opretter en Kunde i DB
        String sql = "INSERT INTO bilabonnementDB.kunde (fornavn, efternavn, email, mobil) VALUES (?,?,?,?)";
        template.update(sql, kunde.getFornavn(), kunde.getEfternavn(), kunde.getEmail(), kunde.getMobil());
    }
}
