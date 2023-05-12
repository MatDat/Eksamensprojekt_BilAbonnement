package com.example.eksamensprojekt_bilabonnement.Service;

import com.example.eksamensprojekt_bilabonnement.Model.Skade;
import com.example.eksamensprojekt_bilabonnement.Model.Skaderapport;
import com.example.eksamensprojekt_bilabonnement.Repository.SkadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkadeService {

    @Autowired
    SkadeRepo skadeRepo;

    public void nySkade(Skade skade) {
        skadeRepo.nySkade(skade);
    }

    public void nySkadeRapport(Skaderapport skaderapport) {
        skadeRepo.nySkadeRapport(skaderapport);
    }

    public List<Skaderapport> hentSkaderapporter() {
        return skadeRepo.hentSkaderapporter();
    }

    public List<Skade> hentSkader(int skaderapport_id) {
        return skadeRepo.hentSkader(skaderapport_id);
    }

    public int hentSkaderapportIDFraKontraktID(int kontrakt_id) {
        return skadeRepo.hentSkaderapportIDFraKontraktID(kontrakt_id);
    }

    public boolean bilErSkadet(int skaderapport_id) {
        return skadeRepo.bilErSkadet(skaderapport_id);
    }
}
