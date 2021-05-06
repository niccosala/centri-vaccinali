package com.uninsubria.clientCV.centrivaccinali.entity;

public class EventoAvverso {

    private String sintomo, descrizione;

    public EventoAvverso(String sintomo, String descrizione) {
        this.sintomo = sintomo;
        this.descrizione = descrizione;
    }

    public String getSintomo() {
        return sintomo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setSintomo(String sintomo) {
        this.sintomo = sintomo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
