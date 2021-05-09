package com.uninsubria.serverCV;

import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;

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
        String query = in.readLine();
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
    public void getCentri() throws IOException, SQLException {
        String query= in.readLine();
        System.out.println(query);
        Statement stmt= connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        try {
            while (rs.next()) {
                out.println(rs.getString("nome"));
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
        String nomeCentro = in.readLine();
        String createTableQuery= in.readLine();
        String insertVaccinatedUserQuery= in.readLine();

        Statement stmt = connection.createStatement();
        Statement stmt1= connection.createStatement();
        try {
            DatabaseMetaData dbm = connection.getMetaData();
            // check if "vaccinati_nomecentro" table exist
            ResultSet tables = dbm.getTables(null, null, "vaccinati_" +nomeCentro, null);
            if (!tables.next()) {
                // Table does no exists
                stmt.executeUpdate(createTableQuery);
            }
            stmt1.executeUpdate(insertVaccinatedUserQuery);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login() throws IOException, SQLException {
        String query = in.readLine();
        String user = in.readLine();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(query);
        System.out.println("query-rset: " + query);
        boolean isRegistered = false;
        if(rs.next())
            isRegistered = true;
        System.out.println("registrato: " + isRegistered);

        if(isRegistered) {
            String query1 = "SELECT * FROM cittadiniregistrati JOIN utentiregistrati ON cittadiniregistrati.userid = utentiregistrati.userid WHERE utentiregistrati.userid = '" + user + "'";
            Statement st = connection.createStatement();
            ResultSet r = st.executeQuery(query1);
            boolean isCittadino = false;
            out.println("true");

            if (r.next()) {
                // Operatore? > No
                out.println("false");
                isCittadino = true;

            } else
                // Operatore? > Si
                out.println("true");

            out.println(rs.getString("nome"));
            out.println(rs.getString("cognome"));
            out.println(rs.getString("codicefiscale"));
            out.println(rs.getString("userid"));
            out.println(rs.getString("pword"));
            if(isCittadino)
                out.println(r.getString("email"));
            if(isCittadino)
                out.println(r.getString("idvacc"));
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
            System.out.println("indirizzo: " +
                    rs.getString("qualificatore") + " " +
                    rs.getString("strada") + " " +
                    rs.getString("civico") + " " +
                    rs.getString("comune") + " " +
                    rs.getString("provincia") + " " +
                    rs.getString("cap"));


            //Mando in output al Proxy.filter i campi per CentroVaccinale
            /*out.println(rs.getString("nome"));
            out.println(rs.getString("tipologia"));
            out.println(rs.getString("qualificatore"))
            out.println(rs.getString("strada"))
            out.println(rs.getString("civico"))
            out.println(rs.getString("comune"))
            out.println(rs.getString("provincia"))
            out.println(rs.getString("cap"))*/

        }
    }

    @Override
    public void close(Socket socket) throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
