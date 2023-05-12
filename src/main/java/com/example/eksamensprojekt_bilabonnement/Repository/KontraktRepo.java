package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Kontrakt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class KontraktRepo {

    @Autowired
    JdbcTemplate template;

    public boolean addKontrakt(Kontrakt k){
        String sql = "INSERT INTO bilabonnementDB.kontrakt (kontrakt_id, start_dato, slut_dato, kunde_id, " +
                "afhentningslokation_id, total_pris, afleveringslokation_id, vognnummer) VALUES (?,?,?,?,?,?,?,?)";
        try{
            template.update(sql, k.getKontrakt_id(),k.getStart_dato(),k.getSlut_dato(),k.getKunde_id(),
                    k.getAfhentningslokation_id(),k.getTotal_pris(),k.getAfleveringslokation_id(),k.getVognnummer());
            return true;
        }catch (Exception e){
            System.out.println("Noget gik galt i KontraktRepo.addKontrakt(k):   " + e.getMessage());
            return false;
        }
    }


    public List<Double> getTotalPrisFraVognnummre(List<Integer>vognnumre){
        String sql = "SELECT total_pris " +
                "FROM bilabonnementDB.kontrakt " +
                "WHERE vognnummer = ? " +
                "AND CURDATE() BETWEEN start_dato AND slut_dato;";

        List<Double> rl = new ArrayList<>();

        for (int i = 0; i < vognnumre.size(); i++) {
            rl.add(template.queryForObject(sql,Double.class,vognnumre.get(i)));
        }

        return rl;

    }

    public List<Integer> hentKontraktIDFraVognnummer(int vognnummer) {
        String sql = "SELECT kontrakt_id FROM kontrakt WHERE vognnummer = ? AND " +
                "slut_dato <= CURDATE() ORDER BY slut_dato DESC";


        List<Integer> vns = template.queryForList(sql, Integer.class, vognnummer);

        return vns;
    }

}
