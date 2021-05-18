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

/**
 * The type Server.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class Server {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Semaphore sem= new Semaphore(100);
        ServerSocket server = new ServerSocket(IComandiServer.PORT);

        do {
            System.out.println("Connessione al server in corso...");
        } while(!tryConnection(ServerInfo.PG_USERNAME,
                ServerInfo.PG_PASSWORD,
                ServerInfo.IP_SERVER,
                ServerInfo.PORT,
                ServerInfo.DB_NAME));

        try {
            System.out.println("Started " + server);

            while(true) {
                Socket socket = server.accept();
                new Skeleton(socket, sem, ServerInfo.PG_USERNAME, ServerInfo.PG_PASSWORD);
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
