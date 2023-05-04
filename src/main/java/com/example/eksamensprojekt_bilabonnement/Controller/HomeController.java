package com.example.eksamensprojekt_bilabonnement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        System.out.println("test - frikke");
        return "home/index";
    }

    @GetMapping("SkadeOgUdbedringLandingPage")
    public String skadeOgUdbedringLandingPage() {
        return "skadeOgUdbedring/skadeOgUdbedring";
    }

    @GetMapping("dataRegistrering")
    public String dataRegistrering() {
        return "dataRegistrering/dataregistrering";
    }

    @GetMapping("login")
    public String login(){
        return "loginPage";
    }
}
