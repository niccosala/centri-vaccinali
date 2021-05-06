package com.uninsubria.clientCV.centrivaccinali.entity;

import com.uninsubria.clientCV.condivisa.entity.Persona;
import java.sql.Date;

public class Vaccinato extends Persona {

    private String centroVaccinale;
    private Vaccino vaccino;
    private Date data;
    private int idVaccinazione;


    public Vaccinato (String nome,
                     String cognome,
                     String CF,
                     String centroVaccinale,
                     Date data,
                     Vaccino vaccino,
                     int idVaccinazione) {

        super(nome, cognome, CF);

        this.centroVaccinale = centroVaccinale;
        this.data = data;
        this.idVaccinazione = idVaccinazione;
        this.vaccino = vaccino;
    }


}
