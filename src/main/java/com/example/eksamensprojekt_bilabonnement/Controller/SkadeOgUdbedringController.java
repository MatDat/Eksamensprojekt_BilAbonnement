package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Model.Skade;
import com.example.eksamensprojekt_bilabonnement.Model.Skaderapport;
import com.example.eksamensprojekt_bilabonnement.Service.BilService;
import com.example.eksamensprojekt_bilabonnement.Service.SkadeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class SkadeOgUdbedringController {

    @Autowired
    BilService bilService;
    @Autowired
    SkadeService skadeService;

    @PostMapping("bilerKlarTilRapport")
    public String printBilerKlarTilSkadesRapport(@ModelAttribute Bil bil, Model model) {

        List<Bil> biler = bilService.hentBiler();
        model.addAttribute("biler", biler);
        return "skadeOgUdbedring/bilerKlarTilRapport";
    }

    @GetMapping("/rapportForm/{vognnummer}")
    public String rapportForm(@PathVariable ("vognnummer") int vognnummer, Model model) {
        model.addAttribute("vn", vognnummer);
        //TODO: Tjek om rapport allerede findes med det valgte vognnummer??

        return "skadeOgUdbedring/rapportForm";
    }

    @PostMapping("/indsendRapportForm")
    public String indsendRapportForm(@ModelAttribute Skaderapport skaderapport, Model model) { //Vi får vognnummer med fra html i hidden form
        int skaderapport_id = skadeService.hentSkaderapporter().size() + 2;
        model.addAttribute("srID", skaderapport_id);
        skadeService.nySkadeRapport(skaderapport);
//        Man kunne måske lave en fetch på alle skaderapporter, og sige at skaderapport id skal være
//        lig størrelsen at listen da skaderapport_id er auto increment
//        model.addAttribute("srID", skaderapport_id);
//        Hvis man trykker afslut rapport uden at have trykke submit først så bliver skaden ikke oprettet.

        return "skadeOgUdbedring/opretSkade";
    }

    @PostMapping("/opretSkade")
    public String opretSkade(@ModelAttribute Skade skade) { //Vi får vognnummer med fra html i hidden form
        //TODO: Man skal kunne blive ved med at tilføje skader
        skadeService.nySkade(skade);

        return "skadeOgUdbedring/opretSkade";
    }

}
