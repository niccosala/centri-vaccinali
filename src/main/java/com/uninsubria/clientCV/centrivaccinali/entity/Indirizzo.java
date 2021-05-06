package com.uninsubria.clientCV.centrivaccinali.entity;

public class Indirizzo {

    private String nome, comune, provincia, civico, CAP;
    private Qualificatore qualificatore;

    public Indirizzo(Qualificatore qualificatore,
                     String nome,
                     String civico,
                     String comune,
                     String provincia,
                     String CAP) {

        this.qualificatore =qualificatore;
        this.nome = nome;
        this.civico = civico;
        this.comune = comune;
        this.provincia = provincia;

        if(Integer.parseInt(CAP) > 9 && Integer.parseInt(CAP) < 98101 && CAP.length() == 5) {
            this.CAP = CAP;
        }
        else
            this.CAP = "00010";
    }
}
