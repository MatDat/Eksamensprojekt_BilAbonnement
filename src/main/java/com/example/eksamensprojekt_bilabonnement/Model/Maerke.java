package com.example.eksamensprojekt_bilabonnement.Model;

public class Maerke{

    private int maerke_id;
    private String maerke_navn;

    public Maerke() {
    }

    public Maerke(int maerke_id, String maerke_navn) {
        this.maerke_id = maerke_id;
        this.maerke_navn = maerke_navn;
    }

    public int getMaerke_id() {
        return maerke_id;
    }

    public void setMaerke_id(int maerke_id) {
        this.maerke_id = maerke_id;
    }

    public String getMaerke_navn() {
        return maerke_navn;
    }

    public void setMaerke_navn(String maerke_navn) {
        this.maerke_navn = maerke_navn;
    }
}
