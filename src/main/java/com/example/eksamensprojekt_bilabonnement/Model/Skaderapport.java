package com.example.eksamensprojekt_bilabonnement.Model;

public class Skaderapport {//COMMENT
    private int skaderapport_id;
    private String skaderapport_dato; //FIXME: SKULLE DEN HER VÆRE DATE ELLER STRING?
    private int bruger_id;
    private int kontrakt_id;

    public Skaderapport() {

    }

    public Skaderapport(int skaderapport_id, String skaderapport_dato, int bruger_id, int kontrakt_id) {
        this.skaderapport_id = skaderapport_id;
        this.skaderapport_dato = skaderapport_dato;
        this.bruger_id = bruger_id;
        this.kontrakt_id = kontrakt_id;
    }

    public int getKontrakt_id() {
        return kontrakt_id;
    }

    public void setKontrakt_id(int kontrakt_id) {
        this.kontrakt_id = kontrakt_id;
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

    public int getBruger_id() {
        return bruger_id;
    }

    public void setBruger_id(int bruger_id) {
        this.bruger_id = bruger_id;
    }
}
