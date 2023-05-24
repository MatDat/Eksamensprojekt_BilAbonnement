package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.*;
import com.example.eksamensprojekt_bilabonnement.Service.BilService;
import com.example.eksamensprojekt_bilabonnement.Service.KontraktService;
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

    @Autowired
    KontraktService kontraktService;

    @GetMapping("/bilerKlarTilRapport")
    public String printBilerKlarTilSkadesRapport(Model model) {
        List<Bil> bilListe = bilService.hentBilerMedTilstand(BilTilstand.RAPPORTKLAR);
        model.addAttribute("biler", bilListe);
        return "skadeOgUdbedring/bilerKlarTilRapport";
    }

    @GetMapping("/rapportForm/{vognnummer}")
    public String rapportForm(@PathVariable("vognnummer") int vognnummer, Model model) {
        model.addAttribute("vognnummer1", vognnummer);
        return "skadeOgUdbedring/rapportForm";
    }

    @PostMapping("/indsendRapportForm")
    public String indsendRapportForm(@ModelAttribute Skaderapport skaderapport, Model model, WebRequest wr,
                                     HttpSession session) {
        //Vi får vognnummer med fra html i hidden form

        int vognnummer2a = Integer.parseInt(wr.getParameter("vognnummer1"));
        List<Integer> kontraktIdListe = kontraktService.hentKontraktIdFraVognnummer(vognnummer2a);
        skaderapport.setKontrakt_id(kontraktIdListe.get(0));

        //Bruger session til at modtage bruger ID
        Bruger bruger = (Bruger) session.getAttribute("bruger");
        skaderapport.setBruger_id(bruger.getBruger_id());

        skadeService.tilfoejSkadeRapport(skaderapport);
        model.addAttribute("vognnummer2a", vognnummer2a);
        //Hvis man trykker afslut rapport uden at have trykke submit først så bliver skaden ikke oprettet.

        return "skadeOgUdbedring/opretSkade";
    }

    @PostMapping("/opretSkade")
    public String opretSkade(@ModelAttribute Skade skade, Model model, WebRequest wr) {
        //Vi får vognnummer med fra html i hidden form

        int vognnummer;
        if (wr.getParameter("vognnummer2b") != null && !wr.getParameter("vognnummer2b").equals("")) {
            vognnummer = Integer.parseInt(wr.getParameter("vognnummer2b"));
        } else {
            vognnummer = Integer.parseInt(wr.getParameter("vognnummer2a"));
        }

        List<Integer> kontrakt_ider = kontraktService.hentKontraktIdFraVognnummer(vognnummer);
        skade.setSkaderapport_id(skadeService.hentSkaderapportIdFraKontraktId(kontrakt_ider.get(0)));

        skadeService.tilfoejSkade(skade);

        //Vi laver en liste over skader for at kunne få alle de tilføjede skader vist efter hver tilføjelse af ny skade.
        List<Skade> skader = skadeService.hentSkaderFraSkaderapportId(skade.getSkaderapport_id());
        model.addAttribute("skader", skader);

        model.addAttribute("vognnummer2b", vognnummer);
        return "skadeOgUdbedring/opretSkade";
    }


    @PostMapping("/afslutRapport")
    public String afslutRapport(WebRequest wr) {
        int vognnummer;
        if (wr.getParameter("vognnummer2a") != null && !wr.getParameter("vognnummer2a").equals("")) {
            vognnummer = Integer.parseInt(wr.getParameter("vognnummer2a"));
        } else {
            vognnummer = Integer.parseInt(wr.getParameter("vognnummer2b"));
        }
        List<Integer> kontraktIdListe = kontraktService.hentKontraktIdFraVognnummer(vognnummer);
        int skaderapport_id = skadeService.hentSkaderapportIdFraKontraktId(kontraktIdListe.get(0));

        if (skadeService.bilErSkadet(skaderapport_id)) {
            bilService.opdaterBilTilstand(BilTilstand.SKADET, vognnummer);
        } else {
            bilService.opdaterBilTilstand(BilTilstand.LEJEKLAR, vognnummer);
        }
        return "home/index";
    }

    @GetMapping("/printRapporter")
    public String printRapporter(Model model) {
        List<Skaderapport> skaderapportListe = skadeService.hentSkaderapporter();
        model.addAttribute("skaderapporter", skaderapportListe);
        return "skadeOgUdbedring/printRapporter";
    }

    @PostMapping("/printRapporterMedSortering")
    public String printRapporterMedSortering(@RequestParam("sortMuligheder") String valgtMulighed, Model model) {
        List<Skaderapport> skaderapporter = null;

        switch (valgtMulighed) {
            case "skaderapport_id" -> skaderapporter = skadeService.hentSkaderapporterMedSortering("skaderapport_id");
            case "skaderapport_dato" ->
                    skaderapporter = skadeService.hentSkaderapporterMedSortering("skaderapport_dato");
            case "bruger_id" -> skaderapporter = skadeService.hentSkaderapporterMedSortering("bruger_id");
            case "kontrakt_id" -> skaderapporter = skadeService.hentSkaderapporterMedSortering("kontrakt_id");
        }
        model.addAttribute("skaderapporter", skaderapporter);
        return "skadeOgUdbedring/printRapporter";
    }
}