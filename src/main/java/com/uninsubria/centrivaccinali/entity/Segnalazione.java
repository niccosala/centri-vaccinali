package com.uninsubria.centrivaccinali.entity;

public class Segnalazione {

    private int idVaccinazione, idEvento, severità;
    private String descrizione;

    public Segnalazione(int idVaccinazione,
                        int idEvento,
                        int severità,
                        String descrizione) {

        this.descrizione = descrizione;
        this.idEvento = idEvento;
        this.idVaccinazione = idVaccinazione;
        this.severità = severità;
    }

    public int getIdVaccinazione() {
        return idVaccinazione;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public int getSeverità() {
        return severità;
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

    public void setSeverità(int severità) {
        this.severità = severità;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
