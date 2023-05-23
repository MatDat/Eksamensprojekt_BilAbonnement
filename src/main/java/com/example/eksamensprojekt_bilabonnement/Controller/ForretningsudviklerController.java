package com.example.eksamensprojekt_bilabonnement.Controller;


import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Model.BilTilstand;
import com.example.eksamensprojekt_bilabonnement.Model.Kunde;
import com.example.eksamensprojekt_bilabonnement.Model.braendstof;
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

        List<Bil> bilListe = bilService.hentAlleBiler();
        model.addAttribute("biler", bilListe);
        model.addAttribute("staalpriser", bilService.hentStaalpriser(bilListe));
        model.addAttribute("co2", bilService.hentCo2(bilListe));
        return "forretningsudvikler/seBiler";
    }


    @PostMapping("/seBilerDropdown")
    public String seBilerDropdown(@RequestParam("dropdown") String valgtMulighed, Model model) {
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
            case "EL", "BENZIN", "DIESEL" -> bilListe = bilService.hentBilerMedBraendstof(braendstof.valueOf(valgtMulighed));
        }
        model.addAttribute("biler", bilListe);
        model.addAttribute("staalpriser", bilService.hentStaalpriser(bilListe));
        model.addAttribute("co2", bilService.hentCo2(bilListe));
        return "forretningsudvikler/seBiler";
    }

    @PostMapping("/seKunder")
    public String seKunder(Model model){
        model.addAttribute("kundeListe",kundeService.hentAlleKunder());
        return "forretningsudvikler/seKunder";
    }


    @PostMapping("seLokationer")
    public String seLokationer(Model model){
        model.addAttribute("lokationListe",lokationService.hentAlleLokationer());
        return "forretningsudvikler/seLokationer";
    }




}