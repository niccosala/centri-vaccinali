package com.uninsubria.clientCV.centrivaccinali.entity;

import com.uninsubria.clientCV.condivisa.entity.Persona;

public class Operatore extends Persona {

    private String username;

    public Operatore(String nome, String cognome, String CF, String username) {
        super(nome, cognome, CF);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
