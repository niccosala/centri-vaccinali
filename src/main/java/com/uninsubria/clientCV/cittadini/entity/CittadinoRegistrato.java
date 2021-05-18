/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.cittadini.entity;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;

/**
 * The type Cittadino registrato.
 */
public class CittadinoRegistrato extends UtenteRegistrato {

    private String email;
    private int idVaccinazione;

    /**
     * Instantiates a new Cittadino registrato.
     *
     * @param nome           the nome
     * @param cognome        the cognome
     * @param CF             the cf
     * @param email          the email
     * @param username       the username
     * @param password       the password
     * @param idVaccinazione the id vaccinazione
     */
    public CittadinoRegistrato(
            String nome,
            String cognome,
            String CF,
            String email,
            String username,
            String password,
            int idVaccinazione) {

        super(nome, cognome, CF, username, password);
        this.email = email;
        this.idVaccinazione = idVaccinazione;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
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
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
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
