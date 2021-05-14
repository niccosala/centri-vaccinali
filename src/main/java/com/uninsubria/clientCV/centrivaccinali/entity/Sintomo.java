/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.entity;

public class Sintomo {

    private String nome, descrizione;
    private int idevento;

    public Sintomo(int idevento, String nome, String descrizione) {
        this.idevento = idevento;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getIdevento() {
        return idevento;
    }

}
