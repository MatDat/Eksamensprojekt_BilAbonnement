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

        List<Bil> biler = bilService.hentBilerMedTilstand("RAPPORTKLAR");
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
        int skaderapport_id = skadeService.hentSkaderapporter().size() + 2; //Der står +2 da listen starter fra 1 og
        // ikke 0. Den går ud fra den nuværende størrelse og ikke størrelsen med rapporten du er igang med at tilføje

        model.addAttribute("srID", skaderapport_id);
        skadeService.nySkadeRapport(skaderapport);

//        FIXME: Hvis man trykker afslut rapport uden at have trykke submit først så bliver skaden ikke oprettet.

        return "skadeOgUdbedring/opretSkade";
    }

    @PostMapping("/opretSkade")
    public String opretSkade(@ModelAttribute Skade skade, Model model) { //Vi får vognnummer med fra html i hidden form

        //Her giver vi igen rapport ID, og det er +1 nu i stedet for +2 da listen ER blevet oprettet.
        int skaderapport_id = skadeService.hentSkaderapporter().size() + 1;
        model.addAttribute("srID", skaderapport_id);

        skadeService.nySkade(skade);

        //Vi laver en liste over skader for at kunne få alle det tilføjede skader vist efter hver tilføjelse af ny skade.
        List<Skade> skader = skadeService.hentSkader(skade.getSkaderapport_id());
        model.addAttribute("skader", skader);

        return "skadeOgUdbedring/opretSkade";
    }

}
