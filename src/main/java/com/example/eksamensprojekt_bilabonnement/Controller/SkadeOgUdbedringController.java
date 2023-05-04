package com.example.eksamensprojekt_bilabonnement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SkadeOgUdbedringController {

    @GetMapping("bilerKlarTilRapport")
    public String registrerFejl() {
        return "skadeOgUdbedring/bilerKlarTilRapport";
    }
}
