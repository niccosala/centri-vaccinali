/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.serverCV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;

/**
 * DBhelper provides all the database functionalities
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class DBhelper implements IComandiServer {

    private BufferedReader in;
    private PrintWriter out;
    private Connection connection;

    /**
     * Instantiates a new DBbhelper.
     *
     * @param in         the in
     * @param out        the out
     * @param connection the connection
     */
    public DBhelper (BufferedReader in, PrintWriter out, Connection connection) {
        this.in = in;
        this.out = out;
        this.connection = connection;
    }

    @Override
    public void getSintomi() throws IOException, SQLException {
        String query= in.readLine();
        Statement stmt= connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        try {
            while (rs.next()) {
                out.println(rs.getString("sintomo"));
                out.println(rs.getInt("idevento"));
                out.println(rs.getString("descrizione"));
            }
            out.println("exit");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getSegnalazione() throws IOException, SQLException {
        String query = in.readLine();
        Statement stmt= connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        try {
            while (rs.next()) {
                out.println(rs.getString("centrovaccinale"));
                out.println(rs.getString("userid"));
                out.println(rs.getString("sintomo"));
                out.println(rs.getString("severita"));
                out.println(rs.getString("descrizione"));
            }
            out.println("exit");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getSingleValues() throws IOException, SQLException {
        String query= in.readLine();
        String columnLabel = in.readLine();
        Statement stmt= connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        try {
            while (rs.next()) {
                out.println(rs.getString(columnLabel));
            }
            out.println("exit");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getVaccinati() throws IOException, SQLException {
        String query = in.readLine();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        try {
            while (rs.next()) {
                out.println(rs.getString("nomecittadino"));
                out.println(rs.getString("cognomecittadino"));
                out.println(rs.getString("codicefiscale"));
                out.println(rs.getString("vaccino"));
                out.println(rs.getString("idvacc"));
            }
            out.println("exit");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertDb() throws IOException, SQLException {
        String query = in.readLine();
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

        Statement stmt = connection.createStatement();
        try {
            DatabaseMetaData dbm = connection.getMetaData();
            // check if "vaccinati_nomecentro" table exist
            ResultSet tables = dbm.getTables(null, null, "vaccinati_" +nomeCentro, null);
            if (!tables.next()) {
                // Table does no exists
                stmt.executeUpdate(createTableQuery);
            }
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
        boolean isRegistered = false;
        if(rs.next())
            isRegistered = true;

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
        Statement stmt= connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        try {
            while (rs.next()) {
                //Mando in output al Proxy.filter i campi per CentroVaccinale
                out.println(rs.getString("nome"));
                out.println(rs.getString("tipologia"));
                out.println(rs.getString("qualificatore"));
                out.println(rs.getString("strada"));
                out.println(rs.getString("civico"));
                out.println(rs.getString("comune"));
                out.println(rs.getString("provincia"));
                out.println(rs.getString("cap"));
            }
            out.println("exit");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close(Socket socket) throws IOException {
        in.close();
        out.close();
        socket.close();
    }

}
