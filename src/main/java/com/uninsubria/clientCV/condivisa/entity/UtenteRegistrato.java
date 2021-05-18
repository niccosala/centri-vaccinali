/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.condivisa.entity;

/**
 * The type Utente registrato.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class UtenteRegistrato extends Persona {

    private String username, password;

    /**
     * Instantiates a new Utente registrato.
     *
     * @param nome     the nome
     * @param cognome  the cognome
     * @param CF       the cf
     * @param username the username
     * @param password the password
     */
    public UtenteRegistrato(String nome,
                            String cognome,
                            String CF,
                            String username,
                            String password) {
        super(nome, cognome, CF);
        this.username = username;
        this.password = password;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
