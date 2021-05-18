/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.serverCV;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Semaphore;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        final int PORT = 7070;
        final String PG_USERNAME = "postgres";
        final String PG_PASSWORD = "pass";
        final String DB_NAME = "cv";
        final String IP_SERVER = "192.168.1.195";

        Semaphore sem= new Semaphore(100);
        ServerSocket server = new ServerSocket(IComandiServer.PORT);

        do {
            System.out.println("Connessione al server in corso...");
        } while(!tryConnection(PG_USERNAME, PG_PASSWORD, IP_SERVER, PORT, DB_NAME));

        try {
            System.out.println("Started " + server);

            while(true) {
                Socket socket = server.accept();
                System.out.println("Connection accepted: ");
                new Skeleton(socket, sem, PG_USERNAME, PG_PASSWORD);
            }
        } catch(Exception e)  {
            e.printStackTrace();
        }
    }

    private static Boolean tryConnection(String user, String password, String ip, int port, String dbname) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:postgresql://" + ip + ":" + port + "/" + dbname + "?&useUnicode=true&characterEncoding=utf8", user, password);
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }

}
