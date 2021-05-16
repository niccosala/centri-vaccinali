/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.entity;

public class Segnalazione {

    private int severita;
    private String userid, descrizione, centroVaccinale, sintomo;

    public Segnalazione(String centroVaccinale,
                        String userid,
                        String sintomo,
                        int severita,
                        String descrizione) {

        this.userid = userid;
        this.descrizione = descrizione;
        this.sintomo = sintomo;
        this.centroVaccinale = centroVaccinale;
        this.severita = severita;
    }

    public String getSintomo() {
        return sintomo;
    }

    public String getCentroVaccinale() {
        return centroVaccinale;
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

    public void setSeverita(int severita) {
        this.severita = severita;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
