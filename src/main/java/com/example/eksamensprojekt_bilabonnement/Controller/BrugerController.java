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

    @PostMapping("adminSide")
    public String adminSide(Model model){
        List<Bruger> brugerListe = brugerService.hentBrugerListe();
        model.addAttribute("brugerListe", brugerListe);
        return "bruger/brugerliste";
    }

    @PostMapping("loginBruger")
    public String logBrugerInd(@ModelAttribute Bruger bruger, HttpSession session) {
        if (brugerService.loginBruger(bruger)){
            session.setAttribute("bruger", bruger);
            Bruger bTemp = (Bruger) session.getAttribute("bruger");
//            System.out.println("Bruger id: " + bTemp.getBruger_id() + "\nBrugernavn: "
//                    + bTemp.getBrugernavn() + "\nkode:  " + bTemp.getKode());     // UDSKRIFT TIL KONSOL
            return "redirect:/";
        }else {
            return "bruger/loginFejl";
        }
    }

    @GetMapping("logBrugerUd")
    public String logBrugerUd(HttpSession session) {
        session.removeAttribute("bruger");
        return "redirect:/";
    }

    @PostMapping("opretBruger")
    public String opretBruger(@ModelAttribute Bruger bruger, HttpSession session) {
        boolean brugerOprettet = brugerService.opretBruger(bruger);
        if (brugerOprettet) {
            //session.setAttribute("bruger", bruger); // Logger brugeren ind med det samme
            return "bruger/opretBrugerSuccess";
        } else {
            return "bruger/opretBrugerFejl";
        }
    }

    @PostMapping("sletBruger")
    public String sletBruger(Bruger bruger){
        boolean brugerSlettet = brugerService.sletBruger(bruger);
        if (brugerSlettet){
            return "redirect:/";
        } else{
            return "brugerlisteFejl";
        }
    }

    @PostMapping("sletBrugerSide")
    public String sletBrugerConfirm(){
        return "bruger/sletBrugerConfirm";
    }
}
