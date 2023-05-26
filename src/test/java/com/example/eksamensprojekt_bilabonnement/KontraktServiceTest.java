package com.example.eksamensprojekt_bilabonnement;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Model.BilTilstand;
import com.example.eksamensprojekt_bilabonnement.Model.Kontrakt;
import com.example.eksamensprojekt_bilabonnement.Service.KontraktService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class KontraktServiceTest {

    @Test
    public void testVognnummerOgBilTilstandErValid() {
//      Testen tjekker, om metoden returnerer true, hvilket betyder, at vognnummeret
//      og biltilstanden er gyldige i forhold til kontrakten.
//      Hvis metoden returnerer true, så består testen.
        Kontrakt kontrakt = new Kontrakt();
        kontrakt.setVognnummer(1234);

        List<Bil> bilListe = new ArrayList<>();

        Bil b1 = new Bil();
        b1.setVognnummer(1234);
        b1.setBilTilstand(BilTilstand.LEJEKLAR);

        Bil b2 = new Bil();
        b2.setVognnummer(4321);
        b2.setBilTilstand(BilTilstand.SKADET);

        bilListe.add(b1);
        bilListe.add(b2);

        KontraktService ks = new KontraktService();
        boolean resultat = ks.vognnummerOgBilTilstandErValid(kontrakt, bilListe);

        assertTrue(resultat);
    }
}