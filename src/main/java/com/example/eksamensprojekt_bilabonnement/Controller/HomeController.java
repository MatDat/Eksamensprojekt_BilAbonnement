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
        //Går ind på hjemmesidens forside. Som udgangspunkt går den ind på siden som 'ikke logget ind'.
        // Den status kan man så ændre til logget ind via log ind funktionen
        Bruger bruger = (Bruger) session.getAttribute("bruger");    //Henter brugerobjektet fra sessionen.
        if (bruger != null) {       //Tjekker om brugeren er logget ind eller ej.
            model.addAttribute("toggle", 0); // Toggler log ud-knappen ON
            String loggedInUser = "Status: {Logget ind som: " + bruger.getBrugernavn() + "}"; //Opretter en passende besked om logindstatus.
            model.addAttribute("loggedInUser", loggedInUser);           // Tilføjer beskeden som en attribut til modellen.
        } else {
            model.addAttribute("toggle", 1);    // Toggler log ind-knappen ON
            String loggedInUser = "Status: {Ikke logget ind}";           //Opretter en passende besked om logindstatus.
            model.addAttribute("loggedInUser", loggedInUser);   // Tilføjer beskeden som en attribut til modellen.
        }
        return "home/index";
    }

    @GetMapping("/SkadeOgUdbedringLandingPage")
    public String skadeOgUdbedringLandingPage(HttpSession session) {
        Bruger bruger = (Bruger) session.getAttribute("bruger");    //Henter brugerobjektet fra sessionen.
        if(bruger == null){
            return "bruger/login";
        } else {
            return "skadeOgUdbedring/skadeOgUdbedring";
        }

    }

    @GetMapping("/dataRegistrering")
    public String dataRegistrering(HttpSession session) {
        Bruger bruger = (Bruger) session.getAttribute("bruger");    //Henter brugerobjektet fra sessionen.
        if(bruger == null){
            return "bruger/login";
        } else {
            return "dataRegistrering/dataregistrering";
        }
    }

    @PostMapping("/forretningsudviklerSide")
    public String forretningsudviklerSide(HttpSession session) {
        Bruger bruger = (Bruger) session.getAttribute("bruger");    //Henter brugerobjektet fra sessionen.
        if(bruger == null){
            return "bruger/login";
        } else {
            return "forretningsudvikler/forretningsudviklerSide";
        }
    }


    @GetMapping("/loginButton")
    public String login() {
        return "bruger/login";
    }

    @GetMapping("/opretBrugerSide")
    public String opretBrugerSide(){
        return "bruger/opretBruger";
    }
}
