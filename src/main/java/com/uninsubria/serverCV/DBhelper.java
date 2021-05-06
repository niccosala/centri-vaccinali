package com.uninsubria.serverCV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBhelper implements IComandiServer {

    private BufferedReader in;
    private PrintWriter out;
    private Connection connection;

    public DBhelper (BufferedReader in, PrintWriter out, Connection connection) {
        this.in = in;
        this.out = out;
        this.connection = connection;
    }


    @Override
    public void searchUser() throws IOException, SQLException {
        String query= in.readLine();
        System.out.println(query);
        Statement stmt= connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            out.println(rs.getString("password"));
            out.println(rs.getString("codfisc"));
        }
    }

    @Override
    public void getSintomi() throws IOException, SQLException {
        String query= in.readLine();
        System.out.println(query);
        Statement stmt= connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        try {
            while (rs.next()) {
                out.println(rs.getString("sintomo"));
            }
            out.println("exit");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertDb() throws IOException, SQLException {
        String query= in.readLine();
        Statement stmt = connection.createStatement();
        try {
            stmt.executeUpdate(query);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void populateCentriVaccinali() throws IOException, SQLException {
        String query= in.readLine();
        String query1= in.readLine();

        Statement stmt = connection.createStatement();
        Statement stmt1= connection.createStatement();
        try {
            stmt.executeUpdate(query);
            stmt1.executeUpdate(query1);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login() throws IOException, SQLException {
        String query= in.readLine();
        String User=in.readLine();
        Statement stmt= connection.createStatement();

        ResultSet rs = stmt.executeQuery(query);
        boolean isRegistered = false;
        if (rs.getRow() != 0) {
            isRegistered = true;
            System.out.println(rs.getRow());
        }

        if(isRegistered) {
            String query1= "select userid from utentiregistrati where userid='"+User+"' intersect select userid from cittadiniregistrati where userid='"+User+"'";
            Statement st= connection.createStatement();
            ResultSet r= st.executeQuery(query1);
            boolean isOperatore = false;

            if (r.getRow() != 0) {
                isOperatore = true;
            }
            out.println("true");
            out.println(isOperatore);
        }
        else
            out.println("false");
    }

    @Override
    public void filter() throws IOException, SQLException {
        String query= in.readLine();
        System.out.println(query);
        Statement stmt= connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            System.out.println("NOME: " + rs.getString("nome"));
            System.out.println("tipologia: " + rs.getString("tipologia"));
            System.out.println("indirizzo: " + rs.getString("indirizzo"));
        }
    }

    @Override
    public void close(Socket socket) throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
