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
    public String indexLoggedInAs(Model model, @NotNull HttpSession session) {
        Bruger bruger = (Bruger) session.getAttribute("bruger");
        if (bruger != null) {
            String loggedInUser = "{Logged in as: " + bruger.getBrugernavn() + "}";
            model.addAttribute("loggedInUser", loggedInUser);
            System.out.println("{Logged ind som: " + bruger.getBrugernavn() + "}");     //UDSKRIFT TIL KONSOL
        } else {
            String loggedInUser = "{Ikke logged ind}";
            model.addAttribute("loggedInUser", loggedInUser);
            System.out.println("{Ikke logged ind}");                                    //UDSKRIFT TIL KONSOL
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
