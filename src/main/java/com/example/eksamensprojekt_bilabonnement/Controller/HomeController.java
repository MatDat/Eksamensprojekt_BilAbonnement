package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Bruger;
import jakarta.servlet.http.HttpSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /*@GetMapping("/")
    public String index() {
        System.out.println("test - frikke");
        return "home/index";
    }*/

    @GetMapping("/")
    public String indexLoggedInAs(Model model, HttpSession session) {
        Bruger bruger = (Bruger) session.getAttribute("bruger");    //Henter brugerobjektet fra sessionen.
        if (bruger != null) {                                          //Tjekker om brugeren er logget ind eller ej.
            String loggedInUser = "{Logged in as: " + bruger.getBrugernavn() + "}"; //Opretter en passende besked om logindstatus.
            model.addAttribute("loggedInUser", loggedInUser);           // Tilføjer beskeden som en attribut til modellen.
            System.out.println("{Logged ind som: " + bruger.getBrugernavn() + "}"); //Udskrift til konsol
        } else {
            String loggedInUser = "{Ikke logged ind}";                      //Opretter en passende besked om logindstatus.
            model.addAttribute("loggedInUser", loggedInUser);   // Tilføjer beskeden som en attribut til modellen.
            System.out.println("{Ikke logged ind}");                        //Udskrift til konsol
        }
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

    @GetMapping("loginButton")
    public String login() {
        return "bruger/loginPage";
    }
}
