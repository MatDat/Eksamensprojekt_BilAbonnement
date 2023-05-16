package com.example.eksamensprojekt_bilabonnement.Model;

public class Kunde {//COMMENT
    private int kunde_id;
    private String fornavn;
    private String efternavn;
    private String email;
    private String mobil;

    public Kunde() {
    }

    public Kunde(int kunde_id, String fornavn, String efternavn, String email, String mobil) {
        this.kunde_id = kunde_id;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.email = email;
        this.mobil = mobil;
    }

    public int getKunde_id() {
        return kunde_id;
    }

    public void setKunde_id(int kunde_id) {
        this.kunde_id = kunde_id;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }
}
