package com.example.eksamensprojekt_bilabonnement.Service;

import com.example.eksamensprojekt_bilabonnement.Model.*;
import com.example.eksamensprojekt_bilabonnement.Repository.BilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BilService {

    @Autowired
    BilRepo bilRepo;

    public List<Bil> hentAlleBiler() {
        return bilRepo.hentAlleBiler();
    }

    //Får en liste af biler med en bestemt tilstand fra BilRepo og laver det om til en String
    public List<Bil> hentBilerMedTilstand(BilTilstand tilstand) {
        return bilRepo.hentBilerMedTilstand(String.valueOf(tilstand));
    }

    public List<Bil> hentBilerMedBraendstof(Braendstof braendstof) {
        return bilRepo.hentBilerMedBraendstof(String.valueOf(braendstof));
    }

    public List<Maerke> hentAlleMaerker() {
        return bilRepo.hentAlleMaerker();
    }

    //Får en liste af modeller ud fra valgt maerke fra Repo
    public List<BilModel> hentValgteModeller(int maerke_id) {
        return bilRepo.hentValgteModeller(maerke_id);
    }

    //Henter mærke navn ud fra mærke ID
    public String hentMaerkeNavnFraID(int maerke_id) {
        return bilRepo.hentMaerkeNavnFraID(maerke_id).get(0).getMaerke_navn();
    }

    //Henter model navn ud fra model ID
    public String hentModelNavnFraID(int model_id) {
        return bilRepo.hentModelNavnFraID(model_id).get(0).getModel_navn();
    }

    public List<Integer> hentVognnumre(List<Bil> bilListe) {
        List<Integer> vognummerListe = new ArrayList<>();
        for (Bil bil : bilListe) {
            vognummerListe.add(bil.getVognnummer());
        }
        return vognummerListe;
    }

    public List<Double> hentStaalpriser(List<Bil> bilListe) {
        List<Double> staalprisListe = new ArrayList<>();
        for (Bil bil : bilListe) {
            staalprisListe.add(bil.getStaalpris());
        }
        return staalprisListe;
    }

    public List<Double> hentCo2(List<Bil> bilListe) {
        List<Double> co2Liste = new ArrayList<>();
        for (Bil bil : bilListe) {
            co2Liste.add(bil.getCO2_udledning());
        }
        return co2Liste;
    }

    public void opdaterBilTilstand(BilTilstand bilTilstand, int vognnummer) {
        bilRepo.opdaterBilTilstand(String.valueOf(bilTilstand), vognnummer);
    }

    public boolean opretBil(Bil bil) {
        return bilRepo.opretBil(bil);
    }
}
