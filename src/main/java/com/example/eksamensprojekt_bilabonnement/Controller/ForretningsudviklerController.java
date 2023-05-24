package com.example.eksamensprojekt_bilabonnement.Controller;


import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Model.BilTilstand;
import com.example.eksamensprojekt_bilabonnement.Model.Braendstof;
import com.example.eksamensprojekt_bilabonnement.Service.BilService;
import com.example.eksamensprojekt_bilabonnement.Service.KontraktService;
import com.example.eksamensprojekt_bilabonnement.Service.KundeService;
import com.example.eksamensprojekt_bilabonnement.Service.LokationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ForretningsudviklerController {

    @Autowired
    BilService bilService;

    @Autowired
    KontraktService kontraktService;

    @Autowired
    KundeService kundeService;

    @Autowired
    LokationService lokationService;


    @PostMapping("/forretningsudviklerSide")
    public String forretningsudviklerSide() {
        return "forretningsudvikler/forretningsudviklerSide";
    }

    @PostMapping("/seBiler")
    public String seBiler(Model model) {
        // der bliver oprettet en liste af Bil objekter som der bliver tilføjet model så det kan vises på html siden.
        // Udfra Bil listen bliver der efterfølgende oprettet lister af stålpriser og co2-udleding,
        // som også bliver tilføjet til model.
        // Controlleren sender så brugeren til seBiler.html

        List<Bil> bilListe = bilService.hentAlleBiler();
        model.addAttribute("biler", bilListe);
        model.addAttribute("staalpriser", bilService.hentStaalpriser(bilListe));
        model.addAttribute("co2", bilService.hentCo2(bilListe));
        return "forretningsudvikler/seBiler";
    }


    @PostMapping("/seBilerDropdown")
    public String seBilerDropdown(@RequestParam("dropdown") String valgtMulighed, Model model) {
        // Denne controller sender den rette liste af biler til seBiler.html udfra hvilken dropdown mulighed brugeren
        //har valgt. Hvis brugeren vælger udlejede biler bliver der også tilføjet en liste af udlejningspriser til model.
        // Her vil der så også blive sat en værdi 1 til "toggle" på model. Dette bruges af et Thymeleaf if-statement
        // på htmlsiden, så udlejningspris kun bliver vist hvis man vælger at se udlejede biler.


        List<Bil> bilListe = null;
        switch (valgtMulighed) {
            case "alle" -> bilListe = bilService.hentAlleBiler();
            case "UDLEJET" -> {
                bilListe = bilService.hentBilerMedTilstand(BilTilstand.valueOf(valgtMulighed));
                List<Double> priser = kontraktService.hentTotalPrisFraVognnumre(bilService.hentVognnumre(bilListe));
                model.addAttribute("toggle", 1);
                model.addAttribute("priser", priser);
            }
            case "SOLGT", "LEJEKLAR" -> bilListe = bilService.hentBilerMedTilstand(BilTilstand.valueOf(valgtMulighed));
            case "EL", "BENZIN", "DIESEL" -> bilListe = bilService.hentBilerMedBraendstof(Braendstof.valueOf(valgtMulighed));
        }
        model.addAttribute("biler", bilListe);
        model.addAttribute("staalpriser", bilService.hentStaalpriser(bilListe));
        model.addAttribute("co2", bilService.hentCo2(bilListe));
        return "forretningsudvikler/seBiler";
    }

    @PostMapping("/seKunder")
    public String seKunder(Model model){
        // tilføjer liste af Kunde objekter til model og sender brugeren til seKunder.html
        model.addAttribute("kundeListe",kundeService.hentAlleKunder());
        return "forretningsudvikler/seKunder";
    }


    @PostMapping("seLokationer")
    public String seLokationer(Model model){
        // tilføjer liste af Lokation objekter til model og sender brugeren til seLokationer.html
        model.addAttribute("lokationListe",lokationService.hentAlleLokationer());
        return "forretningsudvikler/seLokationer";
    }




}