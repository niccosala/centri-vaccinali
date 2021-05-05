package com.uninsubria.cittadini.entity;

import com.uninsubria.condivisa.entity.Persona;

public class CittadinoRegistrato extends Persona {

    private String email, username, password;
    private int idVaccinazione;

    public CittadinoRegistrato(
            String nome,
            String cognome,
            String CF,
            String email,
            String username,
            String password,
            int idVaccinazione) {

        super(nome, cognome, CF);
        this.email = email;
        this.username = username;
        this.password = password;
        this.idVaccinazione = idVaccinazione;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getIdVaccinazione() {
        return idVaccinazione;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdVaccinazione(int idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }
}
