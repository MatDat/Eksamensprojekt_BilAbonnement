package com.example.eksamensprojekt_bilabonnement.Service;

import com.example.eksamensprojekt_bilabonnement.Model.Bruger;
import com.example.eksamensprojekt_bilabonnement.Repository.BrugerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrugerService {

    @Autowired
    BrugerRepo brugerRepo;

    public boolean loginBruger(Bruger bruger){
        return brugerRepo.loginBruger(bruger);
    }
}
