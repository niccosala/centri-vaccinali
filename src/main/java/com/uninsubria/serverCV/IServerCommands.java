package com.uninsubria.serverCV;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public interface IServerCommands {

    static final int PORT = 8888;

    Socket initializeConnection() throws IOException;
    String[] searchUser(Socket socket, String query) throws IOException;
    ArrayList<String> getSintomi(Socket socket, String query) throws IOException;
    void uploadToDb(Socket socket, String query) throws IOException;
    void uploadToDb1(Socket socket, String query, String nometabella) throws IOException;
    void findByName(Socket socket, String query) throws IOException;
    void findbyComune_Tipologia(Socket socket, String query) throws IOException;
    void close() throws IOException;

}
