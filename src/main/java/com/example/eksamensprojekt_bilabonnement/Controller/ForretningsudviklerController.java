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
    public String forretningsudviklerSide(){
        return "forretningsudvikler/forretningsudviklerSide";
    }

    @PostMapping("/seBiler")
    public String seBiler(Model model){

        List<Bil> biler = bilService.hentAlleBiler();
        model.addAttribute("biler", biler);

        return "forretningsudvikler/seBiler";
    }

    @PostMapping("/testRS")
    public String testRS(){

        List<Integer> testliste = new ArrayList<>();


        testliste.add(2);

        List<Double> lulu = kontraktService.getTotalPrisFraVognnummre(testliste);
        System.out.println(lulu.get(0));

        return "forretningsudvikler/seBiler";

    }

    @PostMapping("/seBilerDropdown")
    public String handleFormSubmission(@RequestParam("dropdown") String selectedOption, Model model) {
        List<Bil> biler = null;
        System.out.println(selectedOption);

        switch (selectedOption){
            case "alle" -> biler = bilService.hentAlleBiler();
            case "UDLEJET" -> {
                biler = bilService.hentBilerMedTilstand(selectedOption);
                List<Double> priser = kontraktService.getTotalPrisFraVognnummre(bilService.hentVognnumre(biler));
                model.addAttribute("toggle",1);
                model.addAttribute("priser",priser);
            }
        }

        model.addAttribute("biler", biler);


        return "forretningsudvikler/seBiler";
    }




}
