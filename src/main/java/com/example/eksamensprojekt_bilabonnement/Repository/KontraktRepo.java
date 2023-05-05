package com.example.eksamensprojekt_bilabonnement.Repository;

import com.example.eksamensprojekt_bilabonnement.Model.Kontrakt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

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
}
