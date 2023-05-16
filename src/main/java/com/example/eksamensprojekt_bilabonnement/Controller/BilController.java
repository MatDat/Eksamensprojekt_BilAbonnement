package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Model.BilTilstand;
import com.example.eksamensprojekt_bilabonnement.Model.Maerke;
import com.example.eksamensprojekt_bilabonnement.Service.BilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
    public String addBilSide(){
        return "dataregistrering/addBil";
    }

    @GetMapping("vaelgMaerkeSide")
    public String vaelgMaerkeSide(Model model){
        List<Maerke> alleMaerkeNavne = bilService.hentAlleMaerker();

        model.addAttribute("maerke_navn", alleMaerkeNavne);
        return "dataregistrering/vaelgMaerke";
    }

    @GetMapping("vaelgModelSide")
    public String vaelgModelSide(){
        return "dataregistrering/vaelgModel";
    }

    @GetMapping("opretBilSide")
    public String opretBilSide(){
        return "dataregistrering/addBil";
    }

    @PostMapping("opretBil")
    public String opretBil(@ModelAttribute Bil bil){
        bilService.opretBil(bil);
        return "home/index";
    }
}
