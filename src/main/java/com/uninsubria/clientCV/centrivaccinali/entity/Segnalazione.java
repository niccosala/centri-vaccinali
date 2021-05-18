/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.entity;

/**
 * The type Segnalazione.
 */
public class Segnalazione {

    private int severita;
    private String userid, descrizione, centroVaccinale, sintomo;

    /**
     * Instantiates a new Segnalazione.
     *
     * @author Franchi Matteo 740760 VA
     * @author Magaudda Giovanni 740962 VA
     * @author Sala Niccolò 742545 VA
     *
     * @param centroVaccinale the centro vaccinale
     * @param userid          the userid
     * @param sintomo         the sintomo
     * @param severita        the severita
     * @param descrizione     the descrizione
     */
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

    /**
     * Gets sintomo.
     *
     * @return the sintomo
     */
    public String getSintomo() {
        return sintomo;
    }

    /**
     * Gets centro vaccinale.
     *
     * @return the centro vaccinale
     */
    public String getCentroVaccinale() {
        return centroVaccinale;
    }

    /**
     * Gets severita.
     *
     * @return the severita
     */
    public int getSeverita() {
        return severita;
    }

    /**
     * Gets descrizione.
     *
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Sets centro vaccinale.
     *
     * @param centroVaccinale the centro vaccinale
     */
    public void setCentroVaccinale(String centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
    }

    /**
     * Sets severita.
     *
     * @param severita the severita
     */
    public void setSeverita(int severita) {
        this.severita = severita;
    }

    /**
     * Sets descrizione.
     *
     * @param descrizione the descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Gets userid.
     *
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * Sets userid.
     *
     * @param userid the userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
}
