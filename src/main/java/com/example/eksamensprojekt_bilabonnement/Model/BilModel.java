package com.example.eksamensprojekt_bilabonnement.Model;

public class BilModel {
    private int model_id;
    private String model_navn;

    public BilModel() {
    }

    public BilModel(int model_id, String model_navn) {
        this.model_id = model_id;
        this.model_navn = model_navn;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getModel_navn() {
        return model_navn;
    }

    public void setModel_navn(String model_navn) {
        this.model_navn = model_navn;
    }
}
