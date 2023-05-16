package com.example.eksamensprojekt_bilabonnement.Model;

public class Skade {//COMMENT
    private int skade_id;
    private String beskrivelse;
    private double pris;
    private int skaderapport_id;

    public Skade() {
    }
    public Skade(int skade_id, String beskrivelse, double pris, int vognnummer, int skaderapport_id) {
        this.skade_id = skade_id;
        this.beskrivelse = beskrivelse;
        this.pris = pris;
        this.skaderapport_id = skaderapport_id;
    }

    public int getSkade_id() {
        return skade_id;
    }

    public void setSkade_id(int skade_id) {
        this.skade_id = skade_id;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }
    
    public int getSkaderapport_id() {
        return skaderapport_id;
    }

    public void setSkaderapport_id(int skaderapport_id) {
        this.skaderapport_id = skaderapport_id;
    }
}
