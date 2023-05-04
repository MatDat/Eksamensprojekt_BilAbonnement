package com.example.eksamensprojekt_bilabonnement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataRegistreringController {

    @GetMapping("/opretLejeaftale")
    public String opretLejeaftale(){
        return "dataRegistrering/opretLejeaftale";
    }

}
