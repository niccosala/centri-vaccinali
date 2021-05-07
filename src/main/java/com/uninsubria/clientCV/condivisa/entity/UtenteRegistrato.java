package com.uninsubria.clientCV.condivisa.entity;

public class UtenteRegistrato extends Persona {

    private String username, password;

    public UtenteRegistrato(String nome,
                            String cognome,
                            String CF,
                            String username,
                            String password) {
        super(nome, cognome, CF);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
