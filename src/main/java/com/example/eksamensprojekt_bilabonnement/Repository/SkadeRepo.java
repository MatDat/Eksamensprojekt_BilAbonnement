package com.example.eksamensprojekt_bilabonnement.Repository;

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

    public void tilfoejSkade(Skade skade) {
        //Tilføjer en skade til Databasen, som metoden får med som inputparameter
        String sql = "INSERT INTO bilabonnementDB.skade (beskrivelse, pris, skaderapport_id) " +
                "VALUES (?, ?, ?)";
        template.update(sql, skade.getBeskrivelse(), skade.getPris(), skade.getSkaderapport_id());
    }

    public void tilfoejSkadeRapport(Skaderapport skaderapport) {
        //Tilføjer en skaderapport til Databasen, som metoden får med som inputparameter
        String sql = "INSERT INTO bilabonnementDB.skaderapport (skaderapport_dato, kontrakt_id, bruger_id) " +
                "VALUES (?, ?, ?)";
        template.update(sql, skaderapport.getSkaderapport_dato(),
                skaderapport.getKontrakt_id(), skaderapport.getBruger_id());
    }

    public List<Skaderapport> hentSkaderapporter() {
        //Henter hele skaderapport table, plotter det ind i Skaderapport objekter og returnerer en Liste
        String sql = "SELECT * FROM bilabonnementDB.skaderapport";
        RowMapper<Skaderapport> rowMapper = new BeanPropertyRowMapper<>(Skaderapport.class);
        return template.query(sql, rowMapper);
    }

    public List<Skaderapport> hentSkaderapporterMedSortering(String sortering) {
        //Henter hele skaderapport table, med given sortering i fra inputparameter,
        // plotter det ind i Skaderapport objekter og returnerer en Liste
        String sql = "SELECT * FROM bilabonnementDB.skaderapport ORDER BY " + sortering + " ASC";
        RowMapper<Skaderapport> rowMapper = new BeanPropertyRowMapper<>(Skaderapport.class);
        return template.query(sql, rowMapper);
    }

    public List<Skade> hentSkaderFraSkaderapportId(int skaderapport_id) {
        //henter og returnerer en liste af skader med et givent skaderapport_id fra inputparameteret
        //og returner en liste af Skade objekter
        String sql = "SELECT * FROM bilabonnementDB.skade WHERE skaderapport_id = ?";
        RowMapper<Skade> rowMapper = new BeanPropertyRowMapper<>(Skade.class);
        return template.query(sql, rowMapper, skaderapport_id);
    }

    public int hentSkaderapportIdFraKontraktId(int kontrakt_id) {
        //udfra et givent kontrakt_id fra inputparameteret vil denne metode returnere et skaderapport_id
        String sql = "SELECT skaderapport_id FROM bilabonnementDB.skaderapport WHERE kontrakt_id = ?";
        return template.queryForObject(sql, Integer.class, kontrakt_id);
    }

    public boolean skaderapportIndeholderSkader(int skaderapport_id) {
        // Denne metoder tjekker om en skaderapport indeholder mindst en skade
        // Metoden tjekker ved at hente en liste af skader ud fra et skaderapport_id
        // og så inde i en try catch prøve at printe det første element fra listen
        // hvis listen indeholder en eller flere skader vil der returnes true
        // hvis listen ikke indeholder noget så vil metoden catch en IndexOutOfBoundsException og returnere false
        String sql = "SELECT * FROM bilabonnementDB.skade WHERE skaderapport_id = ?";
        RowMapper<Skade> rowMapper = new BeanPropertyRowMapper(Skade.class);

        try {
            List<Skade> test = template.query(sql, rowMapper, skaderapport_id);
            System.out.println(test.get(0));
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}
