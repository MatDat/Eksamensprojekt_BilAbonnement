package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Bruger;
import com.example.eksamensprojekt_bilabonnement.Service.BrugerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BrugerController {

    @Autowired
    BrugerService brugerService;


    @PostMapping ("/adminSide")
    public String adminSide(Model model) {
        //Går ind på adminsiden som indeholder bla. en liste over alle brugere
        List<Bruger> brugerListe = brugerService.hentBrugerListe();
        model.addAttribute("brugerListe", brugerListe);
        return "bruger/adminSide";
    }

    @PostMapping("/adminLogin")
    public String adminLogin() {
        //Går ind på siden hvor Admin kan logge ind
        return "bruger/logIndAdmin";
    }

    @PostMapping("/adminLoggedInd")
    public String adminSide(@ModelAttribute Bruger bruger, Model model) {
        //Tjekker om Admins log ind informationer er korrekt og logger Admin ind hvis korrekt
        // og ellers giver en fejlside
        if (brugerService.logIndAdmin(bruger)) {
            List<Bruger> brugerListe = brugerService.hentBrugerListe();
            model.addAttribute("brugerListe", brugerListe);
            return "bruger/adminSide";
        } else {
            return "bruger/logIndAdminFejl";
        }
    }

    @PostMapping("/logBrugerInd")
    public String logBrugerInd(@ModelAttribute Bruger bruger) {
        //Tjekker om brugerens log ind informationer er korrekt og logger bruger ind hvis korrekt
        // og ellers giver en fejlside
        if (brugerService.logIndBruger(bruger)) {
            return "redirect:/";
        } else {
            return "bruger/loginFejl";
        }
    }

    @GetMapping("/logBrugerUd")
    public String logBrugerUd(HttpSession session) {
        //Logger brugeren ud af programmet
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/opretBruger")
    public String opretBruger(@ModelAttribute Bruger bruger) {
        //Opretter en ny bruger i databasen
        boolean brugerOprettet = brugerService.opretBruger(bruger);
        if (brugerOprettet) {
            return "redirect:adminSide";
        } else {
            return "bruger/opretBrugerFejl";
        }
    }

    @PostMapping("/sletBruger")
    public String sletBruger(@ModelAttribute Bruger bruger) {
        //Sletter en bruger fra databasen
        if (brugerService.sletBruger(bruger)) {
            return "redirect:adminSide";
        } else {
            return "bruger/adminSideFejl";
        }
    }

    @PostMapping("/sletBrugerSide")
    public String sletBrugerConfirm() {
        //Går ind på en side som gør at man lige skal dobbelttjekke hvem man ville slette
        return "bruger/sletBrugerBekraeft";
    }
}
