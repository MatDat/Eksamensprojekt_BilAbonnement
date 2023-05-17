package com.example.eksamensprojekt_bilabonnement.Model;

public class Lokation {//COMMENT
    private int lokation_id;
    private String adresse;
    private String lokation_navn;

    private String by_navn;
    private int postnummer;



    public Lokation() {
    }

    public Lokation(int lokation_id, String adresse, String lokation_navn, String by_navn, int postnummer) {
        this.lokation_id = lokation_id;
        this.adresse = adresse;
        this.lokation_navn = lokation_navn;
        this.by_navn = by_navn;
        this.postnummer = postnummer;
    }


    public String getBy_navn() {
        return by_navn;
    }

    public void setBy_navn(String by_navn) {
        this.by_navn = by_navn;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
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
