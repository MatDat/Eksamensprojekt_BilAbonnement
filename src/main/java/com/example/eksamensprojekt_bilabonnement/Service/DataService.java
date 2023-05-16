package com.example.eksamensprojekt_bilabonnement.Service;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Repository.BilRepo;
import com.example.eksamensprojekt_bilabonnement.Repository.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {//COMMENT

    @Autowired
    DataRepo dataRepo;
    @Autowired
    BilRepo bilRepo;

    //TODO WIP underneath
//    public List<List<String>> hentAlleLokationer(){
//        return dataRepo.hentAlleLokationer();
//    }

    public List<List<String>> hentAlleBilerSomString(){
        List<Bil> bilListe = bilRepo.hentBilerMedTilstand("LEJEKLAR");
        List<List<String>> bilStringList = new ArrayList<>();
        for (Bil bil : bilListe) {
            List<String> stringValues = hentEnkeltBilListe(bil);
            bilStringList.add(stringValues);
        }
        return bilStringList;
    }

    private List<String> hentEnkeltBilListe(Bil bil){
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(bil.getVognnummer()));
        list.add(bil.getStelnummer());
        list.add(String.valueOf(bil.getModel_id()));
        list.add(String.valueOf(bil.getStaalpris()));
        list.add(String.valueOf(bil.getRegistrerings_afgift()));
        list.add(String.valueOf(bil.getCO2_udledning()));
        list.add(bil.getBilTilstand().toString());
        list.add(bil.getMaerke_navn());
        list.add(bil.getModel_navn());
        list.add(bil.getGear_type().toString());
        list.add(bil.getBraendstof().toString());
        return list;
    }
}
