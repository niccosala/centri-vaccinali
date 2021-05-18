/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
*/
package com.uninsubria.clientCV.condivisa.entity;

/**
 * The type Persona.
 */
public abstract class Persona {

    private String nome;
    private String cognome;
    private String CF;

    /**
     * Instantiates a new Persona.
     *
     * @param nome    the nome
     * @param cognome the cognome
     * @param CF      the cf
     */
    public Persona(String nome, String cognome, String CF) {
        this.nome = nome;
        this.cognome = cognome;
        this.CF = CF;
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
     * Gets cognome.
     *
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Gets cf.
     *
     * @return the cf
     */
    public String getCF() {
        return CF;
    }
}
