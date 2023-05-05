package com.example.eksamensprojekt_bilabonnement.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForretningsudviklerController {


    @PostMapping("/forretningsudviklerSide")
    public String forretningsudviklerSide(){
        return "forretningsudvikler/forretningsudviklerSide";
    }

    @PostMapping("/seBiler")
    public String seBiler(){



        return "forretningsudvikler/seBiler";
    }




}
