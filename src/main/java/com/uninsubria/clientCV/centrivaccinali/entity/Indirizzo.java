package com.uninsubria.clientCV.centrivaccinali.entity;

import com.uninsubria.clientCV.condivisa.Util;

public class Indirizzo {

    private String strada, comune, provincia, civico, CAP;
    private Qualificatore qualificatore;
    private Util util = new Util();

    public Indirizzo(Qualificatore qualificatore,
                     String strada,
                     String civico,
                     String comune,
                     String provincia,
                     String CAP) {

        this.qualificatore = qualificatore;
        this.strada = strada;
        this.civico = civico;
        this.comune = comune;
        this.provincia = provincia;

        if(Integer.parseInt(CAP) > 9 && Integer.parseInt(CAP) < 98101 && CAP.length() == 5)
            this.CAP = CAP;
        else
            this.CAP = "00010";
    }

    @Override
    public String toString() {
        return util.lowercaseNotFirst(qualificatore.toString()) +
                " " +
                strada +
                " " +
                civico +
                " " +
                comune +
                " (" +
                provincia +
                "), " +
                CAP;
    }
}
