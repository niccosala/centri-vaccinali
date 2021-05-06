package com.uninsubria.centrivaccinali.entity;

public class Segnalazione {

    private int idVaccinazione, idEvento, severita;
    private String descrizione;

    public Segnalazione(int idVaccinazione,
                        int idEvento,
                        int severita,
                        String descrizione) {

        this.descrizione = descrizione;
        this.idEvento = idEvento;
        this.idVaccinazione = idVaccinazione;
        this.severita = severita;
    }

    public int getIdVaccinazione() {
        return idVaccinazione;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public int getSeverita() {
        return severita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setIdVaccinazione(int idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public void setSeverita(int severita) {
        this.severita = severita;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
