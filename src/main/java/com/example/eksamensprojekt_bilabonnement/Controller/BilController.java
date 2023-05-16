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
// COMMENT
    @Autowired
    BilService bilService;

    @GetMapping("opdaterBilTilstand")
    public String opdaterBilTilstand(BilTilstand bilTilstand, int vognnummer) {
        bilService.opdaterBilTilstand(bilTilstand, vognnummer);
        return "home/index";
    }

    @GetMapping("addBilSide")
    public String addBilSide(){
        return "dataregistrering/addBil";
    }

    @GetMapping("vaelgMaerkeSide")
    public String vaelgMaerkeSide(){
        return "dataregistrering/vaelgMaerke";
    }

    @PostMapping("opretBil")
    public String opretBil(@ModelAttribute Bil bil){
        bilService.opretBil(bil);
        return "home/index";
    }
}
