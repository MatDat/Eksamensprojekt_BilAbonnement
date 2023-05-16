package com.example.eksamensprojekt_bilabonnement.Service;

import com.example.eksamensprojekt_bilabonnement.Model.Kunde;
import com.example.eksamensprojekt_bilabonnement.Repository.KundeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KundeService {//COMMENT

    @Autowired
    KundeRepo kundeRepo;

    public List<Kunde> hentAlleKunder(){
        return kundeRepo.hentAlleKunder();
    }


    public Kunde hentKundeFraId(int kunde_id){
        return kundeRepo.hentKundeFraId(kunde_id);
    }
}
