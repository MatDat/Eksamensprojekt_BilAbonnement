package com.example.eksamensprojekt_bilabonnement.Service;
import com.example.eksamensprojekt_bilabonnement.Model.Lokation;
import com.example.eksamensprojekt_bilabonnement.Repository.LokationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LokationService {

    @Autowired
    LokationRepo lokationRepo;

    public List<Lokation> hentAlleLokationer() {
        return lokationRepo.hentAlleLokationer();
    }

    public List<Lokation> hentLokationFraId(int lokation_id) {
        return lokationRepo.hentLokationFraId(lokation_id);
    }

    public void tilfoejLokation(Lokation lokation){
        lokationRepo.tilfoejLokation(lokation);
    }
}
