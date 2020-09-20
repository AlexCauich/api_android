package com.example.tekifest_api.model;

public class Automoviles {
    private String uid;
    private String brand;
    private String model;
    private String placa;
    private String verificate;

    public Automoviles() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getVerificate() {
        return verificate;
    }

    public void setVerificate(String verificate) {
        this.verificate = verificate;
    }

    @Override
    public String toString() {
        return placa;
    }
}
