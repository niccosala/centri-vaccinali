/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.serverCV;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public interface IComandiServer {

    int PORT = 8888;

    void getSintomi() throws IOException, SQLException;
    void getSegnalazione() throws IOException, SQLException;
    void getSingleValues() throws IOException, SQLException;
    void getVaccinati() throws IOException, SQLException;
    void insertDb() throws IOException, SQLException;
    void populateCentriVaccinali() throws IOException, SQLException;
    void filter() throws IOException, SQLException;
    void login() throws IOException, SQLException;
    void close(Socket socket) throws IOException;

}
