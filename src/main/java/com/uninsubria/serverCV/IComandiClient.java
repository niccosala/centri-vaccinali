/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.serverCV;

import com.uninsubria.clientCV.centrivaccinali.entity.CentroVaccinale;
import com.uninsubria.clientCV.centrivaccinali.entity.Segnalazione;
import com.uninsubria.clientCV.centrivaccinali.entity.Sintomo;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IComandiClient {

    CentroVaccinale pickCentro(String query) throws IOException;
    ArrayList<Sintomo> getSintomi(String query) throws IOException, SQLException;
    ArrayList<String> getCentri(String query) throws IOException, SQLException;
    ArrayList<Segnalazione> getSegnalazione (String query) throws IOException;
    UtenteRegistrato login(String query, String user) throws IOException;
    void insertDb(String query) throws IOException, SQLException;
    void populateCentriVaccinali(String query, String nomeTabella) throws IOException, SQLException;
    ArrayList<CentroVaccinale> filter(String query) throws IOException, SQLException;
    void close() throws IOException;
}
