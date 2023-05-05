package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Service.BilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SkadeOgUdbedringController {

    @Autowired
    BilService bilService;

    @PostMapping("bilerKlarTilRapport")
    public String printBilerKlarTilSkadesRapport(@ModelAttribute Bil bil, Model model) {

        List<Bil> biler = bilService.hentBiler();
        model.addAttribute("biler", biler);
        return "skadeOgUdbedring/bilerKlarTilRapport";
    }

    @GetMapping("rapportForm")
    public String rapportForm() {
        return "skadeOgUdbedring/rapportForm";
    }
}
