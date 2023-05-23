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


    @GetMapping("/adminSide")
    public String adminSide(Model model) {
        List<Bruger> brugerListe = brugerService.hentBrugerListe();
        model.addAttribute("brugerListe", brugerListe);
        return "bruger/adminSide";
    }

    @PostMapping("/adminLogin")
    public String adminLogin() {
        return "bruger/logIndAdmin";
    }

    @PostMapping("/adminLoggedInd")
    public String adminSide(@ModelAttribute Bruger bruger) {
        if (brugerService.logIndAdmin(bruger)) {
            return "redirect:/adminSide";
        } else {
            return "bruger/logIndAdminFejl";
        }
    }

    @PostMapping("/loginBruger")
    public String logBrugerInd(@ModelAttribute Bruger bruger) {
        if (brugerService.logIndBruger(bruger)) {
            return "redirect:/";
        } else {
            return "bruger/loginFejl";
        }
    }

    @GetMapping("/logBrugerUd")
    public String logBrugerUd(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


    @PostMapping("/opretBruger")
    public String opretBruger(@ModelAttribute Bruger bruger) {
        boolean brugerOprettet = brugerService.opretBruger(bruger);
        if (brugerOprettet) {
            return "redirect:adminSide";
        } else {
            return "bruger/opretBrugerFejl";
        }
    }

    @PostMapping("/sletBruger")
    public String sletBruger(@ModelAttribute Bruger bruger) {
        if (brugerService.sletBruger(bruger)) {
            return "redirect:adminSide";
        } else {
            return "bruger/adminSideFejl";
        }
    }

    @PostMapping("/sletBrugerSide")
    public String sletBrugerConfirm() {
        return "bruger/sletBrugerBekraeft";
    }
}
