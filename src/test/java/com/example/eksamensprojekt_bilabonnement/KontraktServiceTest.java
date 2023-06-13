package com.example.eksamensprojekt_bilabonnement;

import com.example.eksamensprojekt_bilabonnement.Model.Bil;
import com.example.eksamensprojekt_bilabonnement.Model.BilTilstand;
import com.example.eksamensprojekt_bilabonnement.Model.Kontrakt;
import com.example.eksamensprojekt_bilabonnement.Service.KontraktService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KontraktServiceTest {

    @Test
    public void testVognnummerOgBilTilstandErValid() {
//      Testen tjekker, om metoden returnerer true, hvilket betyder, at vognnummeret
//      og biltilstanden er gyldige i forhold til kontrakten.
//      Hvis metoden returnerer true, så består testen.
        Kontrakt kontrakt = new Kontrakt();
        kontrakt.setVognnummer(1234);
        Kontrakt k2 = new Kontrakt();
        k2.setVognnummer(4321);

        List<Bil> bilListe = new ArrayList<>();
        List<Bil> bilListe2 = new ArrayList<>();

        Bil b1 = new Bil();
        b1.setVognnummer(1234);
        b1.setBilTilstand(BilTilstand.LEJEKLAR);

        Bil b2 = new Bil();
        b2.setVognnummer(4321);
        b2.setBilTilstand(BilTilstand.SKADET);

        bilListe.add(b1);
        bilListe2.add(b2);

        KontraktService ks = new KontraktService();

        boolean resultat = ks.vognnummerOgBilTilstandErValid(kontrakt, bilListe);
        boolean resultat2 = ks.vognnummerOgBilTilstandErValid(k2, bilListe2);

        assertTrue(resultat);
        assertFalse(resultat2);
    }
}
