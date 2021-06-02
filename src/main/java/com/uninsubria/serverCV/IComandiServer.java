/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.serverCV;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * The interface Comandi server: server commands available
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public interface IComandiServer {

    /**
     * The constant PORT.
     */
    int PORT = 8888;

    /**
     * Gets sintomi from DB.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void getSintomi() throws IOException, SQLException;

    /**
     * Gets segnalazione from DB.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void getSegnalazione() throws IOException, SQLException;

    /**
     * Gets requested single values from DB.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void getSingleValues() throws IOException, SQLException;

    /**
     * Gets vaccinati from DB.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void getVaccinati() throws IOException, SQLException;

    /**
     * Insert into DB.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void insertDb() throws IOException, SQLException;

    /**
     * Populate centri vaccinali.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void populateCentriVaccinali() throws IOException, SQLException;

    /**
     * Filter.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void filter() throws IOException, SQLException;

    /**
     * Login.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void login() throws IOException, SQLException;

    /**
     * Close connection.
     *
     * @param socket the socket
     * @throws IOException the io exception
     */
    void close(Socket socket) throws IOException;

}
