package com.uninsubria.clientCV.centrivaccinali.entity;

public class Sintomo {

    private String nome, descrizione;

    public Sintomo(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
