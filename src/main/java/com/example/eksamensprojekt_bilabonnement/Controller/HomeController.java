package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Bruger;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String indexLoggedInAs(Model model, HttpSession session) {
        Bruger bruger = (Bruger) session.getAttribute("bruger");    //Henter brugerobjektet fra sessionen.
        if (bruger != null) {                                          //Tjekker om brugeren er logget ind eller ej.
            String loggedInUser = "{Logget ind som: " + bruger.getBrugernavn() + "}"; //Opretter en passende besked om logindstatus.
            model.addAttribute("loggedInUser", loggedInUser);           // Tilføjer beskeden som en attribut til modellen.
            System.out.println("{Logget ind som: " + bruger.getBrugernavn() + "}"); //Udskrift til konsol
        } else {
            String loggedInUser = "{Ikke logget ind}";                      //Opretter en passende besked om logindstatus.
            model.addAttribute("loggedInUser", loggedInUser);   // Tilføjer beskeden som en attribut til modellen.
            System.out.println("{Ikke logget ind}");                        //Udskrift til konsol
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
        return "bruger/login";
    }

    @GetMapping("opretBrugerSide")
    public String opretBrugerSide(){
        return "bruger/opretBruger";
    }
}
