package com.example.eksamensprojekt_bilabonnement.Model;

public class Skaderapport {
    private int skaderapport_id;
    private String skaderapport_dato; //FIXME: SKULLE DEN HER VÆRE DATE ELLER STRING?
    private int kunde_id;
    private int vognnummer;
    private int bruger_id;

    public Skaderapport() {

    }

    public Skaderapport(int skaderapport_id, String skaderapport_dato, int kunde_id, int vognnummer, int bruger_id) {
        this.skaderapport_id = skaderapport_id;
        this.skaderapport_dato = skaderapport_dato;
        this.kunde_id = kunde_id;
        this.vognnummer = vognnummer;
        this.bruger_id = bruger_id;
    }

    public int getSkaderapport_id() {
        return skaderapport_id;
    }

    public void setSkaderapport_id(int skaderapport_id) {
        this.skaderapport_id = skaderapport_id;
    }

    public String getSkaderapport_dato() {
        return skaderapport_dato;
    }

    public void setSkaderapport_dato(String skaderapport_dato) {
        this.skaderapport_dato = skaderapport_dato;
    }

    public int getKunde_id() {
        return kunde_id;
    }

    public void setKunde_id(int kunde_id) {
        this.kunde_id = kunde_id;
    }

    public int getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(int vognnummer) {
        this.vognnummer = vognnummer;
    }

    public int getBruger_id() {
        return bruger_id;
    }

    public void setBruger_id(int bruger_id) {
        this.bruger_id = bruger_id;
    }
}