package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Kontrakt;
import com.example.eksamensprojekt_bilabonnement.Model.Kunde;
import com.example.eksamensprojekt_bilabonnement.Model.Lokation;
import com.example.eksamensprojekt_bilabonnement.Service.DataService;
import com.example.eksamensprojekt_bilabonnement.Service.KontraktService;
import com.example.eksamensprojekt_bilabonnement.Service.KundeService;
import com.example.eksamensprojekt_bilabonnement.Service.LokationService;
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
    DataService dataService;

    @Autowired
    LokationService lokationService;

    @Autowired
    KundeService kundeService;

    @GetMapping("/opretLejeaftale")
    public String opretLejeaftale() {
        return "dataRegistrering/opretLejeaftale";
    }

    @PostMapping("/saveLejeaftale")
    public String saveLejeaftale(@ModelAttribute Kontrakt kontrakt, Model model) {
        List<String> fejlBeskeder = kontraktService.validerOgTilføjKontrakt(kontrakt);
        if (fejlBeskeder.isEmpty()) {
            return ("dataRegistrering/dataregistrering");
        } else {
            model.addAttribute("toggle", true);
            model.addAttribute("fejlBeskeder", fejlBeskeder);
            return "dataRegistrering/opretLejeaftale";
        }
    }

    @GetMapping("/seLejeaftaler")
    public String seLejeafaler(@RequestParam(name = "toggle", defaultValue = "0") int toggle,
                               @RequestParam(value = "sortMuligheder", required = false) String selectedOption,
                               Model model) {
        model.addAttribute("toggle", toggle);
        model.addAttribute("sortMuligheder", selectedOption);
        if (toggle == 0) {
            model.addAttribute("kontraktListe", kontraktService.hentKontrakter(true, selectedOption));
        } else if (toggle == 1) {
            model.addAttribute("kontraktListe", kontraktService.hentKontrakter(false, selectedOption));
        }
        return "dataregistrering/seLejeaftaler";
    }

    @PostMapping("/visLejeaftale")
    public String visLejeaftale(@RequestParam("kontrakt_id") int kontrakt_id, Model model) {

        Kontrakt kontrakt = kontraktService.hentKontraktMedId(kontrakt_id);
        System.out.println(kontrakt.getAfhentningslokation_id());
        Lokation afhentningslokation = lokationService.hentLokationFraId(kontrakt.getAfhentningslokation_id()).get(0);
        Lokation afleveringslokation = lokationService.hentLokationFraId(kontrakt.getAfleveringslokation_id()).get(0);
        Kunde kunde = kundeService.hentKundeFraId(kontrakt.getKunde_id()).get(0);

        model.addAttribute("kontrakt", kontrakt);
        model.addAttribute("afhentningslokation", afhentningslokation);
        model.addAttribute("afleveringslokation", afleveringslokation);
        model.addAttribute("kunde", kunde);

        return "dataregistrering/visLejeaftale";
    }

}
