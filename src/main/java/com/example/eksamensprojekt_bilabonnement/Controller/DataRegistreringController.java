package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Kontrakt;
import com.example.eksamensprojekt_bilabonnement.Service.KontraktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DataRegistreringController {

    @Autowired
    KontraktService kontraktService;

    @GetMapping("/opretLejeaftale")
    public String opretLejeaftale() {
        return "dataRegistrering/opretLejeaftale";
    }

    @PostMapping("/saveLejeaftale")
    public String saveLejeaftale(@ModelAttribute Kontrakt kontrakt) {
        if (kontraktService.addKontrakt(kontrakt)) {
            System.out.println("DEBUG: " + kontrakt);
            System.out.println("DEBUG: Kontrakt tilføjet til DB");
        }
        return ("dataRegistrering/dataregistrering");
    }

}
