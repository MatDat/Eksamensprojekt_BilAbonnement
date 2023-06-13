package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.*;
import com.example.eksamensprojekt_bilabonnement.Service.BilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class BilController {

    @Autowired
    BilService bilService;

    @GetMapping("/opdaterBilTilstand")
    public String opdaterBilTilstand(BilTilstand bilTilstand, int vognnummer) {
        //Opdaterer/ændrer en bils tilstand
        bilService.opdaterBilTilstand(bilTilstand, vognnummer);
        return "home/index";
    }

    @GetMapping("/vaelgMaerkeSide")
    public String vaelgMaerkeSide(Model model) {
        //Går ind på en side hvor man kan vælge et mærke fra databasen,
        // med henblik på at oprette en ny bil i databasen
        List<Maerke> maerkeNavnListe = bilService.hentAlleMaerker();
        model.addAttribute("maerker", maerkeNavnListe);
        return "dataRegistrering/vaelgMaerke";
    }
    @GetMapping("/vaelgModelSide")
    public String vaelgModelSide(Model model, @RequestParam("maerke_id") String maerke_id) {
        //Gå ind på en side som henter en liste med alle modellerne ud fra valgt mærke på tidligere step.
        // Den tager mærke ID med sig
        String maerke_navn = bilService.hentMaerkeNavnFraID(Integer.valueOf(maerke_id)).get(0).getMaerke_navn();
        model.addAttribute("maerke_navn", maerke_navn );  //Tilføjer maerke_navn til h2

        List<BilModel> valgteModeller = bilService.hentValgteModeller(maerke_id); //Kalder Repo metoden til at hente modeller
        model.addAttribute("models", valgteModeller); //Til at kalde listen i HTML'en
        return "dataRegistrering/vaelgModel";
    }

    @GetMapping("/tilfoejBilSide")
    public String tilfoejBilSide(Model model, @RequestParam("model_id") String model_id) {
        //Går ind på en side med en formular hvor man inputter de sidste data
        // som mangler for at oprette en bil til databasen. Den tager model ID med sig
        String model_navn = bilService.hentModelNavnFraID(Integer.valueOf(model_id)).get(0).getModel_navn();
        model.addAttribute("model", model_navn); // Printer valgte model_navn i formularen
        model.addAttribute("model_id", model_id); //Til at tage model_id med videre til tilfoejBil()
        return "dataRegistrering/tilfoejBil";
    }

    @PostMapping("/opretBil")
    public String opretBil(WebRequest wr) {
        //Tager inputs fra formularen og setter dem på et nyoprettet bilobjekt,
        // her efter INSERTER den via Service/Repo bilen i databasen og returnerer til forsiden.
        //Fremtidig implementering: man kunne lave success/Fejl beskeder.
        Bil bil = new Bil();

        bil.setModel_id(Integer.valueOf(wr.getParameter("model_id")));
        bil.setStelnummer(wr.getParameter("stelnummer"));
        bil.setStaalpris(Double.valueOf(wr.getParameter("staalpris")));
        bil.setRegistrerings_afgift(Double.valueOf(wr.getParameter("registrerings_afgift")));
        bil.setCO2_udledning(Double.valueOf(wr.getParameter("CO2_udledning")));
        bil.setBilTilstand(BilTilstand.valueOf(wr.getParameter("bilTilstand")));

        bilService.opretBil(bil);

        return "redirect:/";
    }
}
