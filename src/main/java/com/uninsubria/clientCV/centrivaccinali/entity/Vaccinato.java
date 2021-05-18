/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.entity;

import com.uninsubria.clientCV.condivisa.entity.Persona;
import java.sql.Date;

/**
 * The type Vaccinato.
 */
public class Vaccinato extends Persona {

    private String centroVaccinale;
    private Vaccino vaccino;
    private Date data;
    private int idVaccinazione;


    /**
     * Instantiates a new Vaccinato.
     *
     * @param nome            the nome
     * @param cognome         the cognome
     * @param CF              the cf
     * @param centroVaccinale the centro vaccinale
     * @param data            the data
     * @param vaccino         the vaccino
     * @param idVaccinazione  the id vaccinazione
     */
    public Vaccinato (String nome,
                     String cognome,
                     String CF,
                     String centroVaccinale,
                     Date data,
                     Vaccino vaccino,
                     int idVaccinazione) {

        super(nome, cognome, CF);

        this.centroVaccinale = centroVaccinale;
        this.data = data;
        this.idVaccinazione = idVaccinazione;
        this.vaccino = vaccino;
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
     * Gets vaccino.
     *
     * @return the vaccino
     */
    public Vaccino getVaccino() {
        return vaccino;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * Gets id vaccinazione.
     *
     * @return the id vaccinazione
     */
    public int getIdVaccinazione() {
        return idVaccinazione;
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
     * Sets vaccino.
     *
     * @param vaccino the vaccino
     */
    public void setVaccino(Vaccino vaccino) {
        this.vaccino = vaccino;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Sets id vaccinazione.
     *
     * @param idVaccinazione the id vaccinazione
     */
    public void setIdVaccinazione(int idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }
}
