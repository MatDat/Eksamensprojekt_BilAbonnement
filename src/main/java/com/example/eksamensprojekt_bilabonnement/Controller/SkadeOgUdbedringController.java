package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Model.BilTilstand;
import com.example.eksamensprojekt_bilabonnement.Model.Skade;
import com.example.eksamensprojekt_bilabonnement.Model.Skaderapport;
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
import java.util.Objects;

@Controller
public class SkadeOgUdbedringController {

    @Autowired
    BilService bilService;
    @Autowired
    SkadeService skadeService;

    @Autowired
    KontraktService kontraktService;

    @PostMapping("bilerKlarTilRapport")
    public String printBilerKlarTilSkadesRapport(Model model) {

        List<Bil> biler = bilService.hentBilerMedTilstand("RAPPORTKLAR");
        model.addAttribute("biler", biler);
        return "skadeOgUdbedring/bilerKlarTilRapport";
    }

    @GetMapping("/rapportForm/{vognnummer}")
    public String rapportForm(@PathVariable ("vognnummer") int vognnummer, Model model) {
        model.addAttribute("vognnummer1", vognnummer);
        //TODO: Tjek om rapport allerede findes med det valgte vognnummer??

        return "skadeOgUdbedring/rapportForm";
    }

    @PostMapping("/indsendRapportForm")
    public String indsendRapportForm(@ModelAttribute Skaderapport skaderapport, Model model, WebRequest wr) { //Vi får vognnummer med fra html i hidden form

        int vognnummer2a =Integer.parseInt(wr.getParameter("vognnummer1"));

        List<Integer> kontrakt_ider = kontraktService.hentKontraktIDFraVognnummer(vognnummer2a);
        skaderapport.setKontrakt_id(kontrakt_ider.get(0));


        skadeService.nySkadeRapport(skaderapport);

        model.addAttribute("vognnummer2a",vognnummer2a);



//        FIXME: Hvis man trykker afslut rapport uden at have trykke submit først så bliver skaden ikke oprettet.

        return "skadeOgUdbedring/opretSkade";
    }

    @PostMapping("/opretSkade")
    public String opretSkade(@ModelAttribute Skade skade, Model model, WebRequest wr) { //Vi får vognnummer med fra html i hidden form


        int vognnummer;

        if(wr.getParameter("vognnummer2b") != null && !wr.getParameter("vognnummer2b").equals("")){
            vognnummer = Integer.parseInt(wr.getParameter("vognnummer2b"));
        }else{
            vognnummer = Integer.parseInt(wr.getParameter("vognnummer2a"));
        }

//        int vognnummer2b = Integer.parseInt(wr.getParameter("vognnummer2a"));

        List<Integer> kontrakt_ider = kontraktService.hentKontraktIDFraVognnummer(vognnummer);
        skade.setSkaderapport_id(skadeService.hentSkaderapportIDFraKontraktID(kontrakt_ider.get(0)));


        skadeService.nySkade(skade);

        //Vi laver en liste over skader for at kunne få alle det tilføjede skader vist efter hver tilføjelse af ny skade.
        List<Skade> skader = skadeService.hentSkader(skade.getSkaderapport_id());
        model.addAttribute("skader", skader);


        model.addAttribute("vognnummer2b", vognnummer);

//        System.out.println(vognnummer);


        return "skadeOgUdbedring/opretSkade";
    }


    @PostMapping("/afslutRapport")
    public String afslutRapport(WebRequest wr) {  //TODO: VOGNNUMMER WWWWTTTTTFFFFF?

        int vognnummer;

        if(wr.getParameter("vognnummer2a") != null && !wr.getParameter("vognnummer2a").equals("")){
            vognnummer = Integer.parseInt(wr.getParameter("vognnummer2a"));
        }else{
            vognnummer = Integer.parseInt(wr.getParameter("vognnummer2b"));
        }






        List<Integer> kontrakt_ider = kontraktService.hentKontraktIDFraVognnummer(vognnummer);
        int skaderapport_id = skadeService.hentSkaderapportIDFraKontraktID(kontrakt_ider.get(0));

        if (skadeService.bilErSkadet(skaderapport_id)) {
            bilService.opdaterBilTilstand(BilTilstand.SKADET, vognnummer);
        } else {
            bilService.opdaterBilTilstand(BilTilstand.LEJEKLAR, vognnummer);
        }
        return "home/index";
    }


    // TODO: Metode hvor uafsluttede rapporter bliver printet: Metoden skal tjekke bilen som høre til den rapports
    // tilstand, hvis tilstanden er rapportklar skal den vises, alt andet så skal den ikke. Når man så trykker afslut
    // rapport skal bilens tilstand jo også ændres til klar til leje ellet whatever, noget andet i hvert fald

    @PostMapping("printRapporter") //fejl med sortMuligheder
    public String printRapporter(Model model) {
        List<Skaderapport> skaderapporter = skadeService.hentSkaderapporter();
        model.addAttribute("skaderapporter", skaderapporter);
        return "skadeOgUdbedring/printRapporter";
    }

    @PostMapping("printRapporterSORT") //TODO: Skal have tilføjet nogle skadesrapporter så man kan sortere
    public String printRapporterSORT(@RequestParam("sortMuligheder") String selectedOption, Model model) {
        List<Skaderapport> skaderapporter = null;

        // Lav switch med hvordan man vil sortere
        switch (selectedOption) { //De her virker
            case "skaderapport_id" -> skaderapporter = skadeService.hentSkaderapporterSORT("skaderapport_id");
            case "skaderapport_dato" -> skaderapporter = skadeService.hentSkaderapporterSORT("skaderapport_dato");
            case "bruger_id" -> skaderapporter = skadeService.hentSkaderapporterSORT("bruger_id");
            case "kontrakt_id" -> skaderapporter = skadeService.hentSkaderapporterSORT("kontrakt_id");
        }
        model.addAttribute("skaderapporter", skaderapporter);
        return "skadeOgUdbedring/printRapporter";
    }


}
