package com.example.eksamensprojekt_bilabonnement.Service;

import com.example.eksamensprojekt_bilabonnement.Model.Bruger;
import com.example.eksamensprojekt_bilabonnement.Repository.BrugerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrugerService {

    @Autowired
    BrugerRepo brugerRepo;

    public boolean logIndBruger(Bruger bruger) {
        return brugerRepo.logIndBruger(bruger);
    }

    public boolean logIndAdmin(Bruger bruger) {
        return brugerRepo.logIndAdmin(bruger);
    }

    public boolean opretBruger(Bruger bruger) {
        return brugerRepo.opretBruger(bruger);
    }

    public boolean sletBruger(Bruger bruger) {
        return brugerRepo.sletBruger(bruger);
    }

    public List<Bruger> hentBrugerListe() {
        return brugerRepo.hentBrugerListe();
    }
}
