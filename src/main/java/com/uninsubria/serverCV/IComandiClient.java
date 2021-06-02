/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.serverCV;

import com.uninsubria.clientCV.centrivaccinali.entity.CentroVaccinale;
import com.uninsubria.clientCV.centrivaccinali.entity.Segnalazione;
import com.uninsubria.clientCV.centrivaccinali.entity.Sintomo;
import com.uninsubria.clientCV.centrivaccinali.entity.Vaccinato;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The interface Comandi client: client commands available
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public interface IComandiClient {

    /**
     * Gets sintomi from DB.
     *
     * @param query the query
     * @return the sintomi
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    ArrayList<Sintomo> getSintomi(String query) throws IOException, SQLException;

    /**
     * Gets requested single values from DB.
     *
     * @param query       the query
     * @param columnLabel the column label
     * @return the single values
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    ArrayList<String> getSingleValues(String query, String columnLabel) throws IOException, SQLException;

    /**
     * Gets segnalazione from DB.
     *
     * @param query the query
     * @return the segnalazione
     * @throws IOException the io exception
     */
    ArrayList<Segnalazione> getSegnalazione (String query) throws IOException;

    /**
     * Gets vaccinati from DB.
     *
     * @param query the query
     * @return the vaccinati
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    ArrayList<Vaccinato> getVaccinati(String query) throws IOException, SQLException;

    /**
     * Login utente registrato.
     *
     * @param query the query
     * @param user  the user
     * @return the utente registrato
     * @throws IOException the io exception
     */
    UtenteRegistrato login(String query, String user) throws IOException;

    /**
     * Insert into DB.
     *
     * @param query the query
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void insertDb(String query) throws IOException, SQLException;

    /**
     * Populate centri vaccinali.
     *
     * @param nomeTabella the nome tabella
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void populateCentriVaccinali(String nomeTabella) throws IOException, SQLException;

    /**
     * Filter array list.
     *
     * @param query the query
     * @return the array list
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    ArrayList<CentroVaccinale> filter(String query) throws IOException, SQLException;

    /**
     * Close connection.
     *
     * @throws IOException the io exception
     */
    void close() throws IOException;
}
