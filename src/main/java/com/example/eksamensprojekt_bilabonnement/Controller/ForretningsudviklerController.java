package com.example.eksamensprojekt_bilabonnement.Controller;


import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Service.BilService;
import com.example.eksamensprojekt_bilabonnement.Service.KontraktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@Controller
public class ForretningsudviklerController {

    @Autowired
    BilService bilService;

    @Autowired
    KontraktService kontraktService;


    @PostMapping("/forretningsudviklerSide")
    public String forretningsudviklerSide(){
        return "forretningsudvikler/forretningsudviklerSide";
    }

    @PostMapping("/seBiler")
    public String seBiler(Model model){

        List<Bil> biler = bilService.hentAlleBiler();
        model.addAttribute("biler", biler);
        model.addAttribute("staalpriser",bilService.hentStaalpriser(biler));
        model.addAttribute("co2",bilService.hentCo2(biler));

        
        return "forretningsudvikler/seBiler";
    }
    

    @PostMapping("/seBilerDropdown")
    public String seBilerDropdown(@RequestParam("dropdown") String selectedOption, Model model) {
        List<Bil> biler = null;


        switch (selectedOption){
            case "alle" -> biler = bilService.hentAlleBiler();
            case "UDLEJET" -> {
                biler = bilService.hentBilerMedTilstand(selectedOption);
                List<Double> priser = kontraktService.getTotalPrisFraVognnummre(bilService.hentVognnumre(biler));
                model.addAttribute("toggle",1);
                model.addAttribute("priser",priser);
            }
            case "SOLGT", "LEJEKLAR" -> biler = bilService.hentBilerMedTilstand(selectedOption);
            case "EL","BENZIN","DIESEL" -> biler = bilService.hentBilerMedBraendstof(selectedOption);
        }

        model.addAttribute("biler", biler);
        model.addAttribute("staalpriser",bilService.hentStaalpriser(biler));
        model.addAttribute("co2",bilService.hentCo2(biler));



        return "forretningsudvikler/seBiler";
    }




}
