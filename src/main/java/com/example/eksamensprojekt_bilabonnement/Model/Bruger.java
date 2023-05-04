package com.example.eksamensprojekt_bilabonnement.Model;

public class Bruger {

    private int bruger_id;
    private String brugernavn;
    private String kodeord;

    public Bruger() {
    }

    public int getBruger_id() {
        return bruger_id;
    }

    public Bruger(int bruger_id, String brugernavn, String kodeord) {
        this.bruger_id = bruger_id;
        this.brugernavn = brugernavn;
        this.kodeord = kodeord;
    }

    public void setBruger_id(int bruger_id) {
        this.bruger_id = bruger_id;
    }

    public String getBrugernavn() {
        return brugernavn;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public String getKodeord() {
        return kodeord;
    }

    public void setKodeord(String kodeord) {
        this.kodeord = kodeord;
    }
}
