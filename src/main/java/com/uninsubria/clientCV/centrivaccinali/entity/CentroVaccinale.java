/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.entity;

public class CentroVaccinale {

    private String nome;
    private Indirizzo indirizzo;
    private Tipologia tipologia;

    public CentroVaccinale(String nome, Indirizzo indirizzo, Tipologia tipologia) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.tipologia = tipologia;
    }

    public String getNome() {
        return nome;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public Tipologia getTipologia() {
        return tipologia;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setTipologia(Tipologia tipologia) {
        this.tipologia = tipologia;
    }
}
