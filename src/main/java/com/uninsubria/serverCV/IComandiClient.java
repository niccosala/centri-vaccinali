package com.uninsubria.serverCV;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public interface IComandiClient {

    void initializeConnection();
    String[] searchUser(String query);
    void getSintomi(Socket socket) throws IOException, SQLException;
    void insertDb(Socket socket) throws IOException, SQLException;
    void populateCentriVaccinali(Socket socket) throws IOException, SQLException;
    void filter(Socket socket) throws IOException, SQLException;
    void close(Socket socket) throws IOException;
}
