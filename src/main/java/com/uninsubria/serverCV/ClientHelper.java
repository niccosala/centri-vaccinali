package com.uninsubria.serverCV;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHelper implements IComandiClient{


    @Override
    public void initializeConnection() {

    }

    @Override
    public String[] searchUser(String query) {
        return new String[0];
    }

    @Override
    public void getSintomi(Socket socket) throws IOException, SQLException {

    }

    @Override
    public void insertDb(Socket socket) throws IOException, SQLException {

    }

    @Override
    public void populateCentriVaccinali(Socket socket) throws IOException, SQLException {

    }

    @Override
    public void filter(Socket socket) throws IOException, SQLException {

    }

    @Override
    public void close(Socket socket) throws IOException {

    }
}
