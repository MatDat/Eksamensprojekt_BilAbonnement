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

    @GetMapping("opdaterBilTilstand")
    public String opdaterBilTilstand(BilTilstand bilTilstand, int vognnummer) {
        bilService.opdaterBilTilstand(bilTilstand, vognnummer);
        return "home/index";
    }

    @GetMapping("addBilSide")
    public String addBilSide() {
        return "dataregistrering/addBil";
    }

    @GetMapping("vaelgMaerkeSide")
    public String vaelgMaerkeSide(Model model) {
        List<Maerke> alleMaerkeNavne = bilService.hentAlleMaerker();
        model.addAttribute("maerker", alleMaerkeNavne);

//        System.out.println(alleMaerkeNavne.get(2).getMaerke_id());  //Test om ID kommer med

        return "dataregistrering/vaelgMaerke";
    }
    @GetMapping("/vaelgModelSide")
    public String vaelgModelSide(Model model, @RequestParam("maerke_id") String maerke_id) {
        System.out.println(maerke_id); //Test om navnet kommer med (Konsollen)
        String maerke_navn = bilService.hentMaerkeNavnFraID(Integer.valueOf(maerke_id)).get(0).getMaerke_navn();
        model.addAttribute("maerke_navn", maerke_navn );  //Tilføjer maerke_navn til h2

        List<Bil_Model> valgteModels = bilService.hentValgteModeller(maerke_id); //Kalder Repo metoden
        model.addAttribute("models", valgteModels); //Til at kalde listen i HTML'en

        for (int i = 0; i < valgteModels.size(); i++) {
            System.out.println(valgteModels.get(i).getModel_navn()); //Tester om liste kommer med (Konsollen)
        }

        return "dataregistrering/vaelgModel";
    }

    @GetMapping("opretBilSide")
    public String opretBilSide(Model model, @RequestParam("model_id") String model_id) {
        String model_navn = bilService.hentModelNavnFraID(Integer.valueOf(model_id)).get(0).getModel_navn();
        model.addAttribute("model", model_navn);
        model.addAttribute("model_id", model_id);
        return "dataregistrering/addBil";
    }

    @PostMapping("opretBil")
    public String opretBil(WebRequest wr) {
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
