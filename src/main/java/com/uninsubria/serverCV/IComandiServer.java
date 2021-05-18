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
 * The interface Comandi server.
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
     * Gets sintomi.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void getSintomi() throws IOException, SQLException;

    /**
     * Gets segnalazione.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void getSegnalazione() throws IOException, SQLException;

    /**
     * Gets single values.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void getSingleValues() throws IOException, SQLException;

    /**
     * Gets vaccinati.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void getVaccinati() throws IOException, SQLException;

    /**
     * Insert db.
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
     * Close.
     *
     * @param socket the socket
     * @throws IOException the io exception
     */
    void close(Socket socket) throws IOException;

}
