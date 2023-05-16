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

import java.util.Arrays;
import java.util.List;

@Controller
public class DataRegistreringController {
//COMMENT
    @Autowired
    KontraktService kontraktService;

    @Autowired
    DataService dataService;

    @Autowired
    LokationService lokationService;

    @Autowired
    KundeService kundeService;

    @GetMapping("/opretLejeaftale")
    public String opretLejeaftale(@ModelAttribute Kontrakt kontrakt,Model model) {
        List<String> kolonneNavn = Arrays.asList("vognnummer", "stelnummer", "model_id", "staalpris", "registrerings_afgift", "CO2_udledning", "bil_tilstand");
        List<List<String>> data = dataService.hentAlleBilerSomString();
//        model.addAttribute("kontrakt", kontrakt); // til at gemme det user skriver i kontrakten. brug @ModelAttribute?
        model.addAttribute("kolonneNavne", kolonneNavn);
        model.addAttribute("data", data);
        return "dataRegistrering/opretLejeaftale";
    }

    @PostMapping("/saveLejeaftale")
    public String saveLejeaftale(@ModelAttribute Kontrakt kontrakt) {
        if (kontraktService.tilføjKontrakt(kontrakt)) {
            System.out.println("DEBUG: " + kontrakt);
            System.out.println("DEBUG: Kontrakt tilføjet til DB");
        }
        return ("dataRegistrering/dataregistrering");
    }
    @GetMapping("/seLejeaftaler")
    public String seLejeafaler(@RequestParam(name = "toggle", defaultValue = "0") int toggle, Model model){
        model.addAttribute("toggle",toggle);
        if (toggle == 0){
            model.addAttribute("kontraktListe", kontraktService.hentNuvaerendeKontrakter());
        }else if (toggle == 1){
            model.addAttribute("kontraktListe", kontraktService.hentAfsluttedeKontrakter());
        }
        return "dataregistrering/seLejeaftaler";
    }
    @PostMapping("/visLejeaftale")
    public String visLejeaftale(@RequestParam("kontrakt_id") int kontrakt_id, Model model){
        Kontrakt kontrakt = kontraktService.hentKontraktMedId(kontrakt_id);
        model.addAttribute("kontrakt", kontrakt);
        return "dataregistrering/visLejeaftale";
    }

    @PostMapping("/visLejeaftalePlus")
    public String visLejeaftalePlus(@RequestParam("kontrakt_id") int kontrakt_id, Model model){

        Kontrakt kontrakt = kontraktService.hentKontraktMedId(kontrakt_id);
        Lokation afhentningslokation = lokationService.hentLokationFraId(kontrakt.getAfhentningslokation_id());
        Lokation afleveringslokation = lokationService.hentLokationFraId(kontrakt.getAfleveringslokation_id());
        Kunde kunde = kundeService.hentKundeFraId(kontrakt.getKunde_id());

        model.addAttribute("kontrakt",kontrakt);
        model.addAttribute("afhentningslokation",afhentningslokation);
        model.addAttribute("afleveringslokation",afleveringslokation);
        model.addAttribute("kunde",kunde);

        return "dataregistrering/visLejeaftale";
    }

}
