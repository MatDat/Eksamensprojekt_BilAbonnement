package com.example.eksamensprojekt_bilabonnement.Controller;


import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Service.BilService;
import com.example.eksamensprojekt_bilabonnement.Service.KontraktService;
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
    public String seBilerDropdown(@RequestParam("valgtMulighed") String valgtMulighed, Model model) {
        List<Bil> bilListe = null;
        switch (valgtMulighed) {
            case "alle" -> bilListe = bilService.hentAlleBiler();
            case "UDLEJET" -> {
                bilListe = bilService.hentBilerMedTilstand(valgtMulighed);
                List<Double> priser = kontraktService.hentTotalPrisFraVognnumre(bilService.hentVognnumre(bilListe));
                model.addAttribute("toggle", 1);
                model.addAttribute("priser", priser);
            }
            case "SOLGT", "LEJEKLAR" -> bilListe = bilService.hentBilerMedTilstand(valgtMulighed);
            case "EL", "BENZIN", "DIESEL" -> bilListe = bilService.hentBilerMedBraendstof(valgtMulighed);
        }
        model.addAttribute("biler", bilListe);
        model.addAttribute("staalpriser", bilService.hentStaalpriser(bilListe));
        model.addAttribute("co2", bilService.hentCo2(bilListe));
        return "forretningsudvikler/seBiler";
    }
}