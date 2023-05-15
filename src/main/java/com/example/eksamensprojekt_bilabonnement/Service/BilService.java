package com.example.eksamensprojekt_bilabonnement.Service;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Model.BilTilstand;
import com.example.eksamensprojekt_bilabonnement.Repository.BilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BilService {

    @Autowired
    BilRepo bilRepo;



    public List<Bil> hentAlleBiler(){return bilRepo.hentAlleBiler();}
    public List<Bil> hentBilerMedTilstand(String tilstand){return bilRepo.hentBilerMedTilstand(tilstand);}
    public List<Bil> hentBilerMedBraendstof(String braendstof){return bilRepo.hentBilerMedBraendstof(braendstof);}

    public List<Integer> hentVognnumre(List<Bil> biler){
        List<Integer> vn = new ArrayList<>();
        for (Bil bil : biler) {
            vn.add(bil.getVognnummer());
        }
        return vn;
    }
    public List<Double> hentStaalpriser(List<Bil> biler){
        List<Double> sp = new ArrayList<>();
        for (Bil bil : biler) {
            sp.add(bil.getStaalpris());
        }
        return sp;
    }
    public List<Double> hentCo2(List<Bil> biler){
        List<Double> co2 = new ArrayList<>();
        for (Bil bil : biler) {
            co2.add(bil.getCO2_udledning());
        }
        return co2;
    }

    public void opdaterBilTilstand(BilTilstand bilTilstand, int vognnummer) {
        bilRepo.opdaterBilTilstand(String.valueOf(bilTilstand), vognnummer);
    }
}
