package com.example.eksamensprojekt_bilabonnement.Service;

import com.example.eksamensprojekt_bilabonnement.Model.Kontrakt;
import com.example.eksamensprojekt_bilabonnement.Repository.KontraktRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KontraktService {


    @Autowired
    KontraktRepo kontraktRepo;

    public boolean addKontrakt(Kontrakt k){
        //TODO IF LOOP der checker om lejeaftale input er i orden

        //vognnummer findes, OG har rigtig Tilstand (LEJEKLAR)
        //kunde findes i DB
        //start_dato er før slut_dato
        //tilføj bruger_id til tracking - k.setBruger_id();
        return kontraktRepo.addKontrakt(k);
    }


    public List<Double> getTotalPrisFraVognnummre(List<Integer>vognnumre){
        return kontraktRepo.getTotalPrisFraVognnummre(vognnumre);
    }
}
