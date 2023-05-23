package com.example.eksamensprojekt_bilabonnement.Model;

public class Bil {//COMMENT
    private int vognnummer;
    private String stelnummer;
    private int model_id;
    private double staalpris;
    private double registrerings_afgift;
    private double CO2_udledning;
    private BilTilstand bilTilstand;

    private String maerke_navn;

    private String model_navn;

    private GearType gearType;
    private Braendstof braendstof;


    public Bil() {
    }

    public Bil(int vognnummer, String stelnummer, int model_id, double staalpris, double registrerings_afgift,
               double CO2_udledning, BilTilstand bilTilstand, String maerke_navn, String model_navn, GearType gearType,
               Braendstof braendstof) {
        this.vognnummer = vognnummer;
        this.stelnummer = stelnummer;
        this.model_id = model_id;
        this.staalpris = staalpris;
        this.registrerings_afgift = registrerings_afgift;
        this.CO2_udledning = CO2_udledning;
        this.bilTilstand = bilTilstand;
        this.maerke_navn = maerke_navn;
        this.model_navn = model_navn;
        this.gearType = gearType;
        this.braendstof = braendstof;
    }

    public String getMaerke_navn() {
        return maerke_navn;
    }

    public void setMaerke_navn(String maerke_navn) {
        this.maerke_navn = maerke_navn;
    }

    public String getModel_navn() {
        return model_navn;
    }

    public void setModel_navn(String model_navn) {
        this.model_navn = model_navn;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public Braendstof getBraendstof() {
        return braendstof;
    }

    public void setBraendstof(Braendstof braendstof) {
        this.braendstof = braendstof;
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

    public double getStaalpris() {
        return staalpris;
    }

    public void setStaalpris(double staalpris) {
        this.staalpris = staalpris;
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
