package com.example.eksamensprojekt_bilabonnement.Model;

public class Bil {
    private int vognnummer;
    private String stelnummer;
    private int model_id;
    private double stålpris;
    private double registrerings_afgift;
    private double CO2_udledning;
    private BilTilstand bilTilstand;

    public Bil() {
    }

    public Bil(int vognnummer, String stelnummer, int model_id, double stålpris, double registrerings_afgift, double CO2_udledning, BilTilstand bilTilstand) {
        this.vognnummer = vognnummer;
        this.stelnummer = stelnummer;
        this.model_id = model_id;
        this.stålpris = stålpris;
        this.registrerings_afgift = registrerings_afgift;
        this.CO2_udledning = CO2_udledning;
        this.bilTilstand = bilTilstand;
    }

    public int getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(int vognnummer) {
        this.vognnummer = vognnummer;
    }

    public String getStelnummer() {
        return stelnummer;
    }

    public void setStelnummer(String stelnummer) {
        this.stelnummer = stelnummer;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public double getStålpris() {
        return stålpris;
    }

    public void setStålpris(double stålpris) {
        this.stålpris = stålpris;
    }

    public double getRegistrerings_afgift() {
        return registrerings_afgift;
    }

    public void setRegistrerings_afgift(double registrerings_afgift) {
        this.registrerings_afgift = registrerings_afgift;
    }

    public double getCO2_udledning() {
        return CO2_udledning;
    }

    public void setCO2_udledning(double CO2_udledning) {
        this.CO2_udledning = CO2_udledning;
    }

    public BilTilstand getBilTilstand() {
        return bilTilstand;
    }

    public void setBilTilstand(BilTilstand bilTilstand) {
        this.bilTilstand = bilTilstand;
    }
}
