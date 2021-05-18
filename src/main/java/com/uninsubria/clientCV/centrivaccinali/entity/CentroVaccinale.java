/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.entity;

/**
 * The type Centro vaccinale.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class CentroVaccinale {

    private String nome;
    private Indirizzo indirizzo;
    private Tipologia tipologia;

    /**
     * Instantiates a new Centro vaccinale.
     *
     * @param nome      the nome
     * @param indirizzo the indirizzo
     * @param tipologia the tipologia
     */
    public CentroVaccinale(String nome, Indirizzo indirizzo, Tipologia tipologia) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.tipologia = tipologia;
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
     * Gets indirizzo.
     *
     * @return the indirizzo
     */
    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    /**
     * Gets tipologia.
     *
     * @return the tipologia
     */
    public Tipologia getTipologia() {
        return tipologia;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Sets indirizzo.
     *
     * @param indirizzo the indirizzo
     */
    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Sets tipologia.
     *
     * @param tipologia the tipologia
     */
    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }
}
