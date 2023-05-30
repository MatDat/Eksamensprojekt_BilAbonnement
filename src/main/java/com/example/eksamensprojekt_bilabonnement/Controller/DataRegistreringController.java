package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Bruger;
import com.example.eksamensprojekt_bilabonnement.Model.Kontrakt;
import com.example.eksamensprojekt_bilabonnement.Model.Kunde;
import com.example.eksamensprojekt_bilabonnement.Model.Lokation;
import com.example.eksamensprojekt_bilabonnement.Service.KontraktService;
import com.example.eksamensprojekt_bilabonnement.Service.KundeService;
import com.example.eksamensprojekt_bilabonnement.Service.LokationService;
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
public class DataRegistreringController {
    @Autowired
    KontraktService kontraktService;

    @Autowired
    LokationService lokationService;

    @Autowired
    KundeService kundeService;

    @GetMapping("/opretLejeaftale")
    public String opretLejeaftale() {
        return "dataRegistrering/opretLejeaftale";
    }

    @PostMapping("/gemLejeaftale")
    /*Metoden validerer og gemmer lejeaftaler/kontrakter.
    Metoden modtager user input fra opretLejeaftale.html. Inputtet bliver gemt i kontrakt objektet og valideret
    Hvis input ikke er korrekt, vil toggle blive true, og der bliver printet fejlbesked(er) til brugeren*/
    public String gemLejeaftale(@ModelAttribute Kontrakt kontrakt, Model model, HttpSession session) {
        Bruger bruger = (Bruger) session.getAttribute("bruger");
        kontrakt.setBruger_id(bruger.getBruger_id());
        List<String> fejlBeskeder = kontraktService.validerOgTilfoejKontrakt(kontrakt);
        if (fejlBeskeder.isEmpty()) {
            return ("dataRegistrering/dataregistrering");
        } else {
            model.addAttribute("toggle", true);
            model.addAttribute("fejlBeskeder", fejlBeskeder);
            return "dataRegistrering/opretLejeaftale";
        }
    }

    @GetMapping("/seLejeaftaler")
    //Metoden henter en liste af lejeaftaler (Kontrakter). Ved brug af toggle kan der skiftes mellem
    //Nuværende og afsluttede kontrakter. sortMuligheder bruges til at sorterer i kontrakt objekterne
    public String seLejeafaler(@RequestParam(name = "toggle", defaultValue = "0") int toggle,
                               @RequestParam(value = "sortMuligheder", required = false) String selectedOption,
                               Model model) {
        model.addAttribute("maxKontraktId", kontraktService.hentAlleKontrakter().size());
        model.addAttribute("toggle", toggle);
        model.addAttribute("sortMuligheder", selectedOption);
        if (toggle == 0) {
            model.addAttribute("kontraktListe", kontraktService.hentKontrakterMedSortering(true, selectedOption));
        } else if (toggle == 1) {
            model.addAttribute("kontraktListe", kontraktService.hentKontrakterMedSortering(false, selectedOption));
        }
        return "dataRegistrering/seLejeaftaler";
    }

    @PostMapping("/visLejeaftale")
    public String visLejeaftale(@RequestParam("kontrakt_id") int kontrakt_id, Model model) {
        //Metoden henter alt der har noget med en kontrakt afgøre. Metoden gør brug af flere Service klasser for at
        //kunne tilføje de nødvendige ting til model.
        Kontrakt kontrakt = kontraktService.hentKontraktMedId(kontrakt_id);
        Lokation afhentningslokation = lokationService.hentLokationFraId(kontrakt.getAfhentningslokation_id()).get(0);
        Lokation afleveringslokation = lokationService.hentLokationFraId(kontrakt.getAfleveringslokation_id()).get(0);
        Kunde kunde = kundeService.hentKundeFraId(kontrakt.getKunde_id()).get(0);

        model.addAttribute("kontrakt", kontrakt);
        model.addAttribute("afhentningslokation", afhentningslokation);
        model.addAttribute("afleveringslokation", afleveringslokation);
        model.addAttribute("kunde", kunde);

        return "dataRegistrering/visLejeaftale";
    }


    @PostMapping("/opretKundeForm")
    public String opretKundeForm() {
        return "dataRegistrering/opretKundeForm";
    }

    @PostMapping("/opretKunde")
    public String opretKunde(@ModelAttribute Kunde kunde) {
        kundeService.opretKunde(kunde);
        return "dataRegistrering/dataregistrering";
    }

    @PostMapping("tilfoejLokationForm")
    public String tilfoejLokationForm() {
        return "dataRegistrering/tilfoejLokation";
    }

    @PostMapping("/tilfoejLokation")
    public String tilfoejLokation(@ModelAttribute Lokation lokation) {
        lokationService.tilfoejLokation(lokation);
        return "dataRegistrering/dataregistrering";
    }

}
