package com.example.eksamensprojekt_bilabonnement.Model;

public class Bruger {//COMMENT

    private int bruger_id;
    private String brugernavn;
    private String kode;

    public Bruger() {
    }

    public int getBruger_id() {
        return bruger_id;
    }

    public Bruger(int bruger_id, String brugernavn, String kodeord) {
        this.bruger_id = bruger_id;
        this.brugernavn = brugernavn;
        this.kode = kodeord;
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

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
}
