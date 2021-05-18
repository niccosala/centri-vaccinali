/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.entity;

/**
 * The type Evento avverso.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class EventoAvverso {

    private String sintomo, descrizione;

    /**
     * Instantiates a new Evento avverso.
     *
     * @param sintomo     the sintomo
     * @param descrizione the descrizione
     */
    public EventoAvverso(String sintomo, String descrizione) {
        this.sintomo = sintomo;
        this.descrizione = descrizione;
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
     * Gets descrizione.
     *
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Sets sintomo.
     *
     * @param sintomo the sintomo
     */
    public void setSintomo(String sintomo) {
        this.sintomo = sintomo;
    }

    /**
     * Sets descrizione.
     *
     * @param descrizione the descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
