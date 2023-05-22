package com.example.eksamensprojekt_bilabonnement.Service;

import com.example.eksamensprojekt_bilabonnement.Model.*;
import com.example.eksamensprojekt_bilabonnement.Repository.BilRepo;
import com.example.eksamensprojekt_bilabonnement.Repository.KontraktRepo;
import com.example.eksamensprojekt_bilabonnement.Repository.KundeRepo;
import com.example.eksamensprojekt_bilabonnement.Repository.LokationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class KontraktService {


    @Autowired
    KontraktRepo kontraktRepo;

    @Autowired
    BilRepo bilRepo;

    @Autowired
    KundeRepo kundeRepo;
    @Autowired
    LokationRepo lokationRepo;

    public List<String> validerOgTilfoejKontrakt(Kontrakt k) {
        List<String> fejlBeskeder = opretFejlBeskeder(k);
        if (fejlBeskeder.isEmpty()) {
            kontraktRepo.tilfoejKontrakt(k);
        }
        return fejlBeskeder;
    }

    public Kontrakt hentKontraktMedId(int kontrakt_id) {
        return kontraktRepo.hentKontraktFraId(kontrakt_id);
    }


    public List<Double> hentTotalPrisFraVognnumre(List<Integer> vognnumre) {
        return kontraktRepo.hentTotalPrisFraVognnumre(vognnumre);
    }

    private List<String> opretFejlBeskeder(Kontrakt k) {
        List<String> fejlBeskeder = new ArrayList<>();
        List<Bil> bilListe = bilRepo.hentAlleBiler();       //Loader de 3 lister for at sende samme liste rundt i
        List<Kunde> kundeListe = kundeRepo.hentAlleKunder(); //de private validTest metoder
        List<Lokation> lokationListe = lokationRepo.hentAlleLokationer();

        //TODO tilføj bruger_id til tracking - tag den fra session? - k.setBruger_id();

        if (!vognnummerOgBilTilstandErValid(k, bilListe)) {
            fejlBeskeder.add("Bilen med vognnummeret " + k.getVognnummer() + " findes ikke i systemet" +
                    " eller er ikke tilgængelig til udlejning");
        }
        if (!kundeIdErValid(k, kundeListe)) {
            fejlBeskeder.add("Kunden med id: " + k.getKunde_id() + " findes ikke i systemet");
        }
        if (!datoerErValid(k)) {
            fejlBeskeder.add("Startdatoen må ikke være før slutdatoen eller før i dag");
        }
        if (!afhentningslokationIdErValid(k, lokationListe)) {
            fejlBeskeder.add("Afhentningslokationen med id " + k.getAfhentningslokation_id() + " findes ikke i systemet");
        }
        if (!afleveringslokationIdErValid(k, lokationListe)) {
            fejlBeskeder.add("Afleveringslokationen med id " + k.getAfleveringslokation_id() + " findes ikke i systemet");
        }
        return fejlBeskeder;
    }
    public List<Kontrakt> hentAlleKontrakter(){
        return kontraktRepo.hentAlleKontrakter();
    }

    private boolean vognnummerOgBilTilstandErValid(Kontrakt kontrakt, List<Bil> bilListe) {
        for (int i = 0; i < bilListe.size(); i++) {
            if (bilListe.get(i).getVognnummer() == kontrakt.getVognnummer() &&
                    bilListe.get(i).getBilTilstand() == BilTilstand.LEJEKLAR) {
                return true;
            }
        }
        return false;
    }

    private boolean datoerErValid(Kontrakt kontrakt) { //TODO - Frikke
        SimpleDateFormat datoFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date kDatoStart = datoFormat.parse(kontrakt.getStart_dato());
            Date kSlutStart = datoFormat.parse(kontrakt.getSlut_dato());
            Date dagsDato = datoFormat.parse(datoFormat.format(new Date()));

            if (kDatoStart.before(kSlutStart) && !kDatoStart.before(dagsDato)) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private boolean afhentningslokationIdErValid(Kontrakt k, List<Lokation> lokationListe) {
        for (int i = 0; i < lokationListe.size(); i++) {
            if (k.getAfhentningslokation_id() == lokationListe.get(i).getLokation_id()) {
                return true;
            }
        }
        return false;
    }

    public List<Kontrakt> hentKontrakterMedSortering(boolean erNuvaerende, String sortering) {
        return kontraktRepo.hentKontrakterMedSortering(erNuvaerende, sortering);
    }

    private boolean afleveringslokationIdErValid(Kontrakt k, List<Lokation> lokationListe) {
        for (int i = 0; i < lokationListe.size(); i++) {
            if (k.getAfleveringslokation_id() == lokationListe.get(i).getLokation_id()) {
                return true;
            }
        }
        return false;
    }

    private boolean kundeIdErValid(Kontrakt k, List<Kunde> kundeListe) {
        for (int i = 0; i < kundeListe.size(); i++) {
            if (k.getKunde_id() == kundeListe.get(i).getKunde_id()) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> hentKontraktIdFraVognnummer(int vognnummer) {
        return kontraktRepo.hentKontraktIdFraVognnummer(vognnummer);
    }
}
