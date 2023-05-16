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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BrugerController {
//COMMENT
    @Autowired
    BrugerService brugerService;


    @GetMapping("adminSide")
    public String adminSide(Model model){
        List<Bruger> brugerListe = brugerService.hentBrugerListe();
        model.addAttribute("brugerListe", brugerListe);
        return "bruger/brugerliste";
    }

    @PostMapping("adminLogin")
    public String adminLogin() {
        return "bruger/logIndAdmin";
    }

    @PostMapping("adminLoggedInd")
    public String adminSide(@RequestParam("admin_adgangskode") String adgangskode, HttpSession session) {
        String korrektAdgangskode = "Jegvilind";
        if (adgangskode.equals(korrektAdgangskode)) {
            session.setAttribute("loggedInUser", "admin"); // Gem det logget ind som "admin" i sessionen
            return "redirect:adminSide";
        } else {
            return "bruger/logIndAdminFejl";
        }
    }


    @PostMapping("loginBruger")
    public String logBrugerInd(@ModelAttribute Bruger bruger, HttpSession session, Model model) {
        if (brugerService.loginBruger(bruger)) {
            session.setAttribute("bruger", bruger);
            return "redirect:/";
        } else {

            return "bruger/loginFejl";
        }
    }

    @GetMapping("logBrugerUd")
    public String logBrugerUd(HttpSession session, Model model) {
        session.removeAttribute("bruger");
        return "redirect:/";
    }



    @PostMapping("opretBruger")
    public String opretBruger(@ModelAttribute Bruger bruger, HttpSession session) {
        boolean brugerOprettet = brugerService.opretBruger(bruger);
        if (brugerOprettet) {
            return "redirect:adminSide";
        } else {
            return "bruger/opretBrugerFejl";
        }
    }

    @PostMapping("sletBruger")
    public String sletBruger(Bruger bruger){
        boolean brugerSlettet = brugerService.sletBruger(bruger);
        if (brugerSlettet){
            return "redirect:adminSide";
        } else{
            return "brugerlisteFejl";
        }
    }

    @PostMapping("sletBrugerSide")
    public String sletBrugerConfirm(){
        return "bruger/sletBrugerConfirm";
    }
}
