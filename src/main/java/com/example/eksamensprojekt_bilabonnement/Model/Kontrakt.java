package com.example.eksamensprojekt_bilabonnement.Model;

import java.util.Date;
public class Kontrakt {

    private int kontrakt_id;
    private Date start_dato;
    private Date slut_dato;
    private int kunde_id;
    private int afhentningslokation_id;
    private int afleveringslokation_id;
    private int vognnummer;

    public Kontrakt(){

    }

    public Kontrakt(int kontrakt_id, Date start_dato, Date slut_dato, int kunde_id,
                    int afhentningslokation_id, int afleveringslokation_id, int vognnummer) {
        this.kontrakt_id = kontrakt_id;
        this.start_dato = start_dato;
        this.slut_dato = slut_dato;
        this.kunde_id = kunde_id;
        this.afhentningslokation_id = afhentningslokation_id;
        this.afleveringslokation_id = afleveringslokation_id;
        this.vognnummer = vognnummer;
    }

    public int getKontrakt_id() {
        return kontrakt_id;
    }

    public void setKontrakt_id(int kontrakt_id) {
        this.kontrakt_id = kontrakt_id;
    }

    public Date getStart_dato() {
        return start_dato;
    }

    public void setStart_dato(Date start_dato) {
        this.start_dato = start_dato;
    }

    public Date getSlut_dato() {
        return slut_dato;
    }

    public void setSlut_dato(Date slut_dato) {
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
}
