package com.uninsubria.serverCV;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Server {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        Scanner in = new Scanner(System.in);
        Semaphore sem= new Semaphore(100);
        ServerSocket server = new ServerSocket(8888);
        String user;
        String password;

        do {
            System.out.println("Inserire credenziali di accesso al db/nUser: ");
            user = in.nextLine();
            System.out.println("Password: ");
            password = in.nextLine();
        } while(!tryConnection(user, password));

        try {
            System.out.println("Started " + server);

            while(true) {
                Socket socket = server.accept();
                System.out.println("Connection accepted: ");
                new Skeleton(socket, sem);
            }
        }
        catch(Exception e)  {
            e.printStackTrace();
        }
    }

    private static Boolean tryConnection(String user, String password) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", user, password);
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }

}
