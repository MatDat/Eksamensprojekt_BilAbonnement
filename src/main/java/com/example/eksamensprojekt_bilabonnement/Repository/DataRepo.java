package com.example.eksamensprojekt_bilabonnement.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DataRepo {//COMMENT

    @Autowired
    JdbcTemplate template;

    //TODO WIP underneath
//    public List<List<String>> hentAlleLokationer() {
//        String sql = "SELECT * FROM bilabonnementDB.lokation";
//        List<Map<String, Object>> rows = template.queryForList(sql);
//        List<List<String>> locations = new ArrayList<>();
//
//        for (Map<String, Object> row : rows) {
//            List<String> location = new ArrayList<>();
//            location.add(row.get("lokation_id").toString());
//            location.add((String) row.get("adresse"));
//            location.add((String) row.get("lokation_navn"));
//            locations.add(location);
//        }
//        return locations;
//    }
    
}
