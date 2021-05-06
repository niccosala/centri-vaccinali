package com.uninsubria.serverCV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Semaphore;
import java.io.*;

public class Skeleton extends Thread  {

    private BufferedReader in = null;
    private PrintWriter out = null;
    private Semaphore sem;
    private Socket socket;
    private String username, password;

    public Skeleton(Socket socket, Semaphore semaphore, String username, String password) {
        this.socket = socket;
        this.username = username;
        this.password = password;
        sem = semaphore;
        start();
    }

    public void run() {
        try {
            sem.acquire();
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        try (Connection c = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres", username, password)) {
            System.out.println("Connessione riuscita!");

            try {
                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())
                        ),
                        true
                );
                serveClient(in, out, c);
            } catch(IOException e) {
                 if (out != null) {
                    out.close();
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private void serveClient(BufferedReader in, PrintWriter out, Connection c) throws IOException, SQLException {

        String operation;
        DBhelper dBhelper = new DBhelper(in, out, c);

        while((operation = in.readLine())!= null) {

            switch (operation) {
                case "insert" : dBhelper.insertDb();
                break;
                case "insert1" : dBhelper.populateCentriVaccinali();
                break;
                case "search_user" : dBhelper.searchUser();
                break;
                case "find" : dBhelper.filter();
                break;
                case "searchSintomi" : dBhelper.getSintomi();
                break;
                case "login" : dBhelper.login();
                break;
                default: break;
            }
            dBhelper.close(socket);
        }
        sem.release();
    }
}

