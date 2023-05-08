package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Bruger;
import com.example.eksamensprojekt_bilabonnement.Service.BrugerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BrugerController {

    @Autowired
    BrugerService brugerService;

    @PostMapping("loginBruger")
    public String logBrugerInd(@ModelAttribute Bruger bruger, HttpSession session) {
        if (brugerService.loginBruger(bruger)){
            session.setAttribute("bruger", bruger);
            Bruger bTemp = (Bruger) session.getAttribute("bruger");
//            System.out.println("Bruger id: " + bTemp.getBruger_id() + "\nBrugernavn: "
//                    + bTemp.getBrugernavn() + "\nkode:  " + bTemp.getKode());
            return "redirect:/";
        }else {
            return "bruger/loginNaegtet";
        }
    }
}
