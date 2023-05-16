package com.example.eksamensprojekt_bilabonnement.Model;

public class Lokation {//COMMENT
    private int lokation_id;
    private String adresse;
    private String lokation_navn;

    public Lokation() {
    }

    public Lokation(int lokation_id, String adresse, String lokation_navn) {
        this.lokation_id = lokation_id;
        this.adresse = adresse;
        this.lokation_navn = lokation_navn;
    }

    public int getLokation_id() {
        return lokation_id;
    }

    public void setLokation_id(int lokation_id) {
        this.lokation_id = lokation_id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLokation_navn() {
        return lokation_navn;
    }

    public void setLokation_navn(String lokation_navn) {
        this.lokation_navn = lokation_navn;
    }
}
