/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.clientCV.centrivaccinali.entity;

import com.uninsubria.clientCV.condivisa.Util;

/**
 * The type Indirizzo.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class Indirizzo {

    private String strada, comune, provincia, civico, CAP;
    private Qualificatore qualificatore;
    private Util util = new Util();

    /**
     * Instantiates a new Indirizzo.
     *
     * @param qualificatore the qualificatore
     * @param strada        the strada
     * @param civico        the civico
     * @param comune        the comune
     * @param provincia     the provincia
     * @param CAP           the cap
     */
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

    /**
     * Gets strada.
     *
     * @return the strada
     */
    public String getStrada() {
        return strada;
    }

    /**
     * Gets comune.
     *
     * @return the comune
     */
    public String getComune() {
        return comune;
    }

    /**
     * Gets provincia.
     *
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Gets civico.
     *
     * @return the civico
     */
    public String getCivico() {
        return civico;
    }

    /**
     * Gets cap.
     *
     * @return the cap
     */
    public String getCAP() {
        return CAP;
    }

    /**
     * Gets qualificatore.
     *
     * @return the qualificatore
     */
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
                " - " +
                comune +
                " (" +
                provincia +
                "), " +
                CAP;
    }
}
