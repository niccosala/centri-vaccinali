/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.entity;

/**
 * The type Sintomo.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class Sintomo {

    private String nome, descrizione;
    private int idevento;

    /**
     * Instantiates a new Sintomo.
     *
     * @param idevento    the idevento
     * @param nome        the nome
     * @param descrizione the descrizione
     */
    public Sintomo(int idevento, String nome, String descrizione) {
        this.idevento = idevento;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
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
     * Gets idevento.
     *
     * @return the idevento
     */
    public int getIdevento() {
        return idevento;
    }

}
