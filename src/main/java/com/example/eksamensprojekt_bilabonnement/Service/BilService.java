package com.example.eksamensprojekt_bilabonnement.Service;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Repository.BilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BilService {

    @Autowired
    BilRepo bilRepo;

    public List<Bil> hentBiler() {
        return bilRepo.hentBiler();
    }

    public List<Bil> hentAlleBiler(){return bilRepo.hentAlleBiler();}
    public List<Bil> hentBilerMedTilstand(String tilstand){return bilRepo.hentBilerMedTilstand(tilstand);}

    public List<Integer> hentVognnumre(List<Bil> biler){
        List<Integer> vn = new ArrayList<>();
        for (int i = 0; i < biler.size(); i++) {
            vn.add(biler.get(i).getVognnummer());
        }
        return vn;
    }

}
