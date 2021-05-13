/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
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

    public String getStrada() {
        return strada;
    }

    public String getComune() {
        return comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCivico() {
        return civico;
    }

    public String getCAP() {
        return CAP;
    }

    public Qualificatore getQualificatore() {
        return qualificatore;
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
