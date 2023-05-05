package com.example.eksamensprojekt_bilabonnement.Controller;

import com.example.eksamensprojekt_bilabonnement.Model.Kontrakt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DataRegistreringController {

    @GetMapping("/opretLejeaftale")
    public String opretLejeaftale(){
        return "dataRegistrering/opretLejeaftale";
    }
    @PostMapping("/saveLejeaftale")
    public String saveLejeaftale(@ModelAttribute Kontrakt kontrakt){
        System.out.println(kontrakt);
        return ("dataRegistrering/dataregistrering");
    }

}
