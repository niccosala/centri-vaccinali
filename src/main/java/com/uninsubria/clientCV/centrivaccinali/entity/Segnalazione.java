package com.uninsubria.clientCV.centrivaccinali.entity;

public class Segnalazione {

    private int IDevento, severita;
    private String descrizione, centroVaccinale;

    public Segnalazione(String centroVaccinale,
                        int IDevento,
                        int severita,
                        String descrizione) {

        this.descrizione = descrizione;
        this.IDevento = IDevento;
        this.centroVaccinale = centroVaccinale;
        this.severita = severita;
    }

    public String getCentroVaccinale() {
        return centroVaccinale;
    }

    public int getIDevento() {
        return IDevento;
    }

    public int getSeverita() {
        return severita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setCentroVaccinale(String centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
    }

    public void setIDevento(int IDevento) {
        this.IDevento = IDevento;
    }

    public void setSeverita(int severita) {
        this.severita = severita;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
