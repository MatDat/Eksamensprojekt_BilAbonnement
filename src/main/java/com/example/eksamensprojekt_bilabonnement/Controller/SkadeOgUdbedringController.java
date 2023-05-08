package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Model.Skade;
import com.example.eksamensprojekt_bilabonnement.Model.Skaderapport;
import com.example.eksamensprojekt_bilabonnement.Service.BilService;
import com.example.eksamensprojekt_bilabonnement.Service.SkadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class SkadeOgUdbedringController {

    @Autowired
    BilService bilService;
    @Autowired
    SkadeService skadeService;

    @PostMapping("bilerKlarTilRapport")
    public String printBilerKlarTilSkadesRapport(@ModelAttribute Bil bil, Model model) {

        List<Bil> biler = bilService.hentBiler();
        model.addAttribute("biler", biler);
        return "skadeOgUdbedring/bilerKlarTilRapport";
    }

    @GetMapping("/rapportForm/vognnummer={vognnummer}")
    public String rapportForm(@PathVariable ("vognnummer") int vognnummer, Model model) {
        System.out.println(vognnummer);
//        model.addAttribute("vn", vognnummer);
        return "skadeOgUdbedring/rapportForm";
    }

    @PostMapping("/indsendRapportForm")
    public String indsendRapportForm(@ModelAttribute Skade skade, @ModelAttribute Skaderapport skaderapport,
                                     WebRequest wr) {
        int hej = Integer.valueOf(wr.getParameter("vognnummer"));
        skaderapport.setVognnummer(hej);
        System.out.println(skaderapport.getVognnummer()); //TO TEST IF ABOVE WORKS
        skadeService.nySkadeRapport(skaderapport);
        skadeService.nySkade(skade);

        return "home/index";
    }
}
