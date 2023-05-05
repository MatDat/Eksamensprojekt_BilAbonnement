package com.example.eksamensprojekt_bilabonnement.Controller;


import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Service.BilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ForretningsudviklerController {

    @Autowired
    BilService bilService;


    @PostMapping("/forretningsudviklerSide")
    public String forretningsudviklerSide(){
        return "forretningsudvikler/forretningsudviklerSide";
    }

    @PostMapping("/seBiler")
    public String seBiler(Model model){

        List<Bil> biler = bilService.hentAlleBiler();
        model.addAttribute("biler", biler);

        return "forretningsudvikler/seBiler";
    }




}
