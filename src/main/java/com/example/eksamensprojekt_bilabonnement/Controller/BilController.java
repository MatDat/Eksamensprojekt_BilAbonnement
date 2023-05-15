package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Model.BilTilstand;
import com.example.eksamensprojekt_bilabonnement.Service.BilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BilController {

    @Autowired
    BilService bilService;

    @GetMapping("opdaterBilTilstand")
    public String opdaterBilTilstand(BilTilstand bilTilstand, int vognnummer) {
        bilService.opdaterBilTilstand(bilTilstand, vognnummer);
        return "home/index";
    }

    @GetMapping("addBilSide")
    public String addBilSide(@ModelAttribute Bil bil){
        return "dataregistrering/addBil";
    }

    @PostMapping("opretBil")
    public String opretBil(){

        return "home/index";
    }
}
