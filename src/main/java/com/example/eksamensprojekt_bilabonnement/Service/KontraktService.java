package com.example.eksamensprojekt_bilabonnement.Service;

import com.example.eksamensprojekt_bilabonnement.Model.*;
import com.example.eksamensprojekt_bilabonnement.Repository.BilRepo;
import com.example.eksamensprojekt_bilabonnement.Repository.KontraktRepo;
import com.example.eksamensprojekt_bilabonnement.Repository.KundeRepo;
import com.example.eksamensprojekt_bilabonnement.Repository.LokationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
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

    public boolean tilføjKontrakt(Kontrakt k) {
        List<String> fejlBeskeder = opretFejlBeskeder(k);
        if (!fejlBeskeder.isEmpty()) {          //TODO vi skal bruge List<String> fejlbeskeder til at informere hvilke
            System.out.println(fejlBeskeder);   //input felter der er forkerte
            return false;
        } else
            return kontraktRepo.addKontrakt(k);
    }

    public Kontrakt hentKontraktMedId(int kontrakt_id){
        return kontraktRepo.hentKontraktMedId(kontrakt_id);
    }


    public List<Double> getTotalPrisFraVognnummre(List<Integer> vognnumre) {
        return kontraktRepo.getTotalPrisFraVognnummre(vognnumre);
    }

    private List<String> opretFejlBeskeder(Kontrakt k) {
        List<String> fejlBeskeder = new ArrayList<>();
        List<Bil> bilListe = bilRepo.hentAlleBiler();       //Loader de 3 lister for at sende samme liste rundt i
        List<Kunde> kundeList = kundeRepo.hentAlleKunder(); //de private validTest metoder
        List<Lokation> lokationList = lokationRepo.hentAlleLokationer();

        //TODO tilføj bruger_id til tracking - tag den fra session? - k.setBruger_id();

        if (!vognnumerOgBilTilstandErValid(k, bilListe)) {
            fejlBeskeder.add("Bilen med vognnummeret " + k.getVognnummer() + ", findes ikke i systemet" +
                    " eller er ikke tilgængelig til udlejning");
        }
        if (!kundeIdErValid(k, kundeList)) {
            fejlBeskeder.add("Kunden med id: " + k.getKunde_id() + " findes ikke i systemet");
        }
        if (!total_prisErValid(k)) {
            fejlBeskeder.add("Totalprisen skal være større end 0");
        }
        if (!datoerErValid(k)) {
            fejlBeskeder.add("Startdatoen må ikke være før slutdatoen eller før i dag");
        }
        if (!afhentningslokationIdErValid(k, lokationList)) {
            fejlBeskeder.add("Afhentningslokationen med id " + k.getAfhentningslokation_id() + " findes ikke i systemet");
        }
        if (!afleveringslokationIdErValid(k, lokationList)) {
            fejlBeskeder.add("Afleveringslokationen med id " + k.getAfleveringslokation_id() + " findes ikke i systemet");
        }
        return fejlBeskeder;
    }


    private boolean vognnumerOgBilTilstandErValid(Kontrakt kontrakt, List<Bil> bilList) {
        for (int i = 0; i < bilList.size(); i++) {
            //TODO linje under er til debug
            System.out.println(bilList.get(i).getVognnummer() + " " + bilList.get(i).getBilTilstand());
            if (bilList.get(i).getVognnummer() == kontrakt.getVognnummer() && bilList.get(i).getBilTilstand() == BilTilstand.LEJEKLAR) {
                return true;
            }
        }
        return false;
    }

    private boolean total_prisErValid(Kontrakt kontrakt) {
        if (kontrakt.getTotal_pris() > 0) {
            return true;
        }
        return false;
    }

    private boolean datoerErValid(Kontrakt kontrakt) {
        SimpleDateFormat datoFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date kDatoStart = datoFormat.parse(kontrakt.getStart_dato());
            Date kSlutStart = datoFormat.parse(kontrakt.getSlut_dato());
            Date dagsDato = datoFormat.parse(datoFormat.format(new Date()));

            //TODO check om bilen er ved at blive booked i et tidsrum hvor den allerede er booket i
            if (kDatoStart.before(kSlutStart) && !kDatoStart.before(dagsDato)) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private boolean afhentningslokationIdErValid(Kontrakt k, List<Lokation> lokationList) {
        for (int i = 0; i < lokationList.size(); i++) {
            if (k.getAfhentningslokation_id() == lokationList.get(i).getLokation_id()) {
                return true;
            }
        }
        return false;
    }
    public List<Kontrakt> hentKontrakter(boolean erNuværende, String sortering) {
        return kontraktRepo.hentKontrakter(erNuværende, sortering);
    }

    private boolean afleveringslokationIdErValid(Kontrakt k, List<Lokation> lokationList) {
        for (int i = 0; i < lokationList.size(); i++) {
            if (k.getAfleveringslokation_id() == lokationList.get(i).getLokation_id()) {
                return true;
            }
        }
        return false;
    }

    private boolean kundeIdErValid(Kontrakt k, List<Kunde> kundeList) {
        for (int i = 0; i < kundeList.size(); i++) {
            if (k.getKunde_id() == kundeList.get(i).getKunde_id()) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> hentKontraktIDFraVognnummer(int vognnummer) { // TODO: TEST
        return kontraktRepo.hentKontraktIDFraVognnummer(vognnummer);
    }
}
