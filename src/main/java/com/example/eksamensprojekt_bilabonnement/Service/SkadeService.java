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

    public void tilfoejSkade(Skade skade) {
        skadeRepo.tilfoejSkade(skade);
    }

    public void tilfoejSkadeRapport(Skaderapport skaderapport) {
        skadeRepo.tilfoejSkadeRapport(skaderapport);
    }

    public List<Skaderapport> hentSkaderapporter() {
        return skadeRepo.hentSkaderapporter();
    }

    public List<Skaderapport> hentSkaderapporterMedSortering(String sortering) {
        return skadeRepo.hentSkaderapporterMedSortering(sortering);
    }


    public List<Skade> hentSkaderFraSkaderapportId(int skaderapport_id) {
        return skadeRepo.hentSkaderFraSkaderapportId(skaderapport_id);
    }

    public int hentSkaderapportIdFraKontraktId(int kontrakt_id) {
        return skadeRepo.hentSkaderapportIdFraKontraktId(kontrakt_id);
    }

    public boolean bilErSkadet(int skaderapport_id) {
        return skadeRepo.skaderapportIndeholderSkader(skaderapport_id);
    }

}
