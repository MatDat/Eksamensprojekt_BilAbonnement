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
        String sql = "INSERT INTO skade (beskrivelse, pris, skaderapport_id) " +
                "VALUES (?, ?, ?)";
        template.update(sql, skade.getBeskrivelse(), skade.getPris(), skade.getSkaderapport_id());
    }

    public void tilfoejSkadeRapport(Skaderapport skaderapport) {
        String sql = "INSERT INTO skaderapport (skaderapport_dato, kontrakt_id, bruger_id) " +
                "VALUES (?, ?, ?)";
        template.update(sql, skaderapport.getSkaderapport_dato(),
                skaderapport.getKontrakt_id(), skaderapport.getBruger_id());
    }

    public List<Skaderapport> hentSkaderapporter() {
        String sql = "SELECT * FROM skaderapport";
        RowMapper<Skaderapport> rowMapper = new BeanPropertyRowMapper<>(Skaderapport.class);
        return template.query(sql, rowMapper);
    }

    public List<Skaderapport> hentSkaderapporterMedSortering(String sortering) {
        String sql = "SELECT * FROM skaderapport ORDER BY " + sortering + " ASC";
        RowMapper<Skaderapport> rowMapper = new BeanPropertyRowMapper<>(Skaderapport.class);
        return template.query(sql, rowMapper);
    }

    public List<Skade> hentSkaderFraSkaderapportId(int skaderapport_id) {
        String sql = "SELECT * FROM skade WHERE skaderapport_id = ?";
        RowMapper<Skade> rowMapper = new BeanPropertyRowMapper<>(Skade.class);
        return template.query(sql, rowMapper, skaderapport_id);
    }

    public int hentSkaderapportIdFraKontraktId(int kontrakt_id) {
        String sql = "SELECT skaderapport_id FROM skaderapport WHERE kontrakt_id = ?";
        return template.queryForObject(sql, Integer.class, kontrakt_id);
    }

    public boolean bilErSkadet(int skaderapport_id) { //TODO ændres?
        String sql = "SELECT * FROM skade WHERE skaderapport_id = ?";
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
