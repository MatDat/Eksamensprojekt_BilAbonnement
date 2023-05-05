package com.example.eksamensprojekt_bilabonnement.Model;

public class Kontrakt {

    private int kontrakt_id;
    private String start_dato;
    private String slut_dato;
    private int kunde_id;
    private int afhentningslokation_id;
    private int afleveringslokation_id;

    private double total_pris;

    private int bruger_id;
    private int vognnummer;


    public Kontrakt() {

    }

    public Kontrakt(int kontrakt_id, String start_dato, String slut_dato, int kunde_id,
                    int afhentningslokation_id, int afleveringslokation_id, double total_pris, int bruger_id, int vognnummer) {
        this.kontrakt_id = kontrakt_id;
        this.start_dato = start_dato;
        this.slut_dato = slut_dato;
        this.kunde_id = kunde_id;
        this.afhentningslokation_id = afhentningslokation_id;
        this.afleveringslokation_id = afleveringslokation_id;
        this.total_pris = total_pris;
        this.bruger_id = bruger_id;
        this.vognnummer = vognnummer;
    }

    public double getTotal_pris() {
        return total_pris;
    }

    public void setTotal_pris(double total_pris) {
        this.total_pris = total_pris;
    }

    public int getBruger_id() {
        return bruger_id;
    }

    public void setBruger_id(int bruger_id) {
        this.bruger_id = bruger_id;
    }

    public int getKontrakt_id() {
        return kontrakt_id;
    }

    public void setKontrakt_id(int kontrakt_id) {
        this.kontrakt_id = kontrakt_id;
    }

    public String getStart_dato() {
        return start_dato;
    }

    public void setStart_dato(String start_dato) {
        this.start_dato = start_dato;
    }

    public String getSlut_dato() {
        return slut_dato;
    }

    public void setSlut_dato(String slut_dato) {
        this.slut_dato = slut_dato;
    }

    public int getKunde_id() {
        return kunde_id;
    }

    public void setKunde_id(int kunde_id) {
        this.kunde_id = kunde_id;
    }

    public int getAfhentningslokation_id() {
        return afhentningslokation_id;
    }

    public void setAfhentningslokation_id(int afhentningslokation_id) {
        this.afhentningslokation_id = afhentningslokation_id;
    }

    public int getAfleveringslokation_id() {
        return afleveringslokation_id;
    }

    public void setAfleveringslokation_id(int afleveringslokation_id) {
        this.afleveringslokation_id = afleveringslokation_id;
    }

    public int getVognnummer() {
        return vognnummer;
    }

    public void setVognnummer(int vognnummer) {
        this.vognnummer = vognnummer;
    }

    @Override
    public String toString() {
        return "Kontrakt{" +
                "kontrakt_id=" + kontrakt_id +
                ", start_dato=" + start_dato +
                ", slut_dato=" + slut_dato +
                ", kunde_id=" + kunde_id +
                ", afhentningslokation_id=" + afhentningslokation_id +
                ", afleveringslokation_id=" + afleveringslokation_id +
                ", total_pris=" + total_pris +
                ", bruger_id=" + bruger_id +
                ", vognnummer=" + vognnummer +
                '}';
    }
}
