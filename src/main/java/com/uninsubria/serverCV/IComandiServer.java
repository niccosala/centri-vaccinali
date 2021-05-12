package com.uninsubria.serverCV;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public interface IComandiServer {

    int PORT = 8888;

    //Socket initializeConnection() throws IOException;
    void pickCentro() throws IOException, SQLException;
    void getSintomi() throws IOException, SQLException;
    void getCentri() throws IOException, SQLException;
    void insertDb() throws IOException, SQLException;
    void populateCentriVaccinali() throws IOException, SQLException;
    void filter() throws IOException, SQLException;
    void login() throws IOException, SQLException;
    void close(Socket socket) throws IOException;

}
