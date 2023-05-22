package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Kontrakt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class KontraktRepo {

    @Autowired
    JdbcTemplate template;

    public boolean tilfoejKontrakt(Kontrakt k) {
        String sql = "INSERT INTO bilabonnementDB.kontrakt (kontrakt_id, start_dato, slut_dato, kunde_id, " +
                "afhentningslokation_id, total_pris, afleveringslokation_id, bruger_id,vognnummer) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            template.update(sql, k.getKontrakt_id(), k.getStart_dato(), k.getSlut_dato(), k.getKunde_id(),
                    k.getAfhentningslokation_id(), k.getTotal_pris(), k.getAfleveringslokation_id(),
                    k.getBruger_id(), k.getVognnummer());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Double> hentTotalPrisFraVognnumre(List<Integer> vognnumre) {
        String sql = "SELECT total_pris " +
                "FROM bilabonnementDB.kontrakt " +
                "WHERE vognnummer = ? " +
                "AND CURDATE() BETWEEN start_dato AND slut_dato;";
        List<Double> totalPrisliste = new ArrayList<>();
        for (int i = 0; i < vognnumre.size(); i++) {
            totalPrisliste.add(template.queryForObject(sql, Double.class, vognnumre.get(i)));
        }
        return totalPrisliste;
    }

    public Kontrakt hentKontraktFraId(int kontrakt_id) {
        String sql = "SELECT * FROM bilabonnementDB.kontrakt WHERE kontrakt_id = ?";
        RowMapper<Kontrakt> rowMapper = new BeanPropertyRowMapper(Kontrakt.class);
        return template.queryForObject(sql, rowMapper, kontrakt_id);
    }

    public List<Kontrakt> hentKontrakterMedSortering(boolean erNuvaerende, String sortering) {
        String sql;
        if (erNuvaerende) {
            sql = "SELECT * FROM bilabonnementDB.kontrakt WHERE kontrakt.slut_dato >= CURDATE() " +
                    "ORDER BY " + sortering + " DESC";
        } else {
            sql = "SELECT * FROM bilabonnementDB.kontrakt WHERE kontrakt.slut_dato <= CURDATE() " +
                    "ORDER BY " + sortering + " DESC";
        }
        RowMapper<Kontrakt> rowMapper = new BeanPropertyRowMapper<>(Kontrakt.class);
        return template.query(sql, rowMapper);
    }

    public List<Kontrakt> hentAlleKontrakter() {
        String sql = "SELECT * FROM bilabonnementDB.kontrakt";
        RowMapper<Kontrakt> rowMapper = new BeanPropertyRowMapper<>(Kontrakt.class);
        return template.query(sql, rowMapper);
    }

    public List<Integer> hentKontraktIdFraVognnummer(int vognnummer) {
        String sql = "SELECT kontrakt_id FROM kontrakt WHERE vognnummer = ? AND " +
                "slut_dato <= CURDATE() ORDER BY slut_dato DESC";
        List<Integer> kontraktIdListe = template.queryForList(sql, Integer.class, vognnummer);
        return kontraktIdListe;
    }
}
