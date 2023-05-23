package com.example.eksamensprojekt_bilabonnement;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Service.BilService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BilServiceTest {

    @Test
    public void testHentStaalpriser() {
        List<Bil> bilList = new ArrayList<>();
        Bil b1 = new Bil();
        Bil b2 = new Bil();
        Bil b3 = new Bil();

        b1.setStaalpris(100.0);
        bilList.add(b1);
        b2.setStaalpris(200.0);
        bilList.add(b2);
        b3.setStaalpris(300.0);
        bilList.add(b3);

        BilService bs = new BilService();
        List<Double> staalprisliste = bs.hentStaalpriser(bilList);

        assertEquals(3, staalprisliste.size());
        assertEquals(100.0, staalprisliste.get(0), 0.01);
        assertEquals(200.0, staalprisliste.get(1), 0.01);
        assertEquals(300.0, staalprisliste.get(2), 0.01);
    }
}
