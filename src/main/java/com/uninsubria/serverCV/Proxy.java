/*
Franchi   Matteo    740760   VA
Magaudda  Giovanni  740962   VA
Sala      Niccolò   742545   VA
 */
package com.uninsubria.serverCV;

import com.uninsubria.clientCV.centrivaccinali.entity.*;
import com.uninsubria.clientCV.cittadini.entity.CittadinoRegistrato;
import com.uninsubria.clientCV.condivisa.Util;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Proxy.
 *
 * @author Franchi Matteo 740760 VA
 * @author Magaudda Giovanni 740962 VA
 * @author Sala Niccolò 742545 VA
 */
public class Proxy implements IComandiClient{

    private final Socket socket;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private boolean isOperatore = false;

    /**
     * Instantiates a new Proxy.
     *
     * @throws IOException the io exception
     */
    public Proxy() throws IOException {

        InetAddress addr = InetAddress.getByName(null);
        socket = new Socket(addr, IComandiServer.PORT);

        try {
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())),
                    true);
        } catch (IOException e) {

            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            throw e;
        }
    }

    @Override
    public ArrayList<Sintomo> getSintomi(String query) throws IOException, SQLException {

        ArrayList<Sintomo> sintomi = new ArrayList<>();

        out.println("searchSintomi");
        out.println(query);

        while (true) {

            String nomeSintomo = in.readLine();

            if (nomeSintomo.equals("exit"))
                break;
            else {
                int idevento = Integer.parseInt(in.readLine());
                String descrizione = in.readLine();
                sintomi.add(new Sintomo(
                        idevento,
                        nomeSintomo,
                        descrizione
                ));
            }
        }

        return sintomi;
    }

    @Override
    public ArrayList<String> getSingleValues(String query, String columnLabel) throws IOException {
        ArrayList<String> values = new ArrayList<>();

        out.println("getSingleValues");
        out.println(query);
        out.println(columnLabel);

        while (true) {
            String value = in.readLine();
            if (value.equals("exit"))
                break;
            else {
                values.add(value);
            }
        }
        return values;
    }

    @Override
    public ArrayList<Segnalazione> getSegnalazione(String query) throws IOException {
        ArrayList<Segnalazione> segnalazioni = new ArrayList<>();

        out.println("getSegnalazione");
        out.println(query);

        while (true) {
            String centroVaccinale = in.readLine();

            if (centroVaccinale.equals("exit"))
                break;
            else {
                String userid = in.readLine();
                String sintomo = in.readLine();
                int severita = Integer.parseInt(in.readLine());
                String descrizione = in.readLine();

                segnalazioni.add(new Segnalazione(
                        centroVaccinale,
                        userid,
                        sintomo,
                        severita,
                        descrizione
                ));
            }
        }
        return segnalazioni;
    }

    @Override
    public ArrayList<Vaccinato> getVaccinati(String query) throws IOException, SQLException {
        ArrayList<Vaccinato> vaccinati = new ArrayList<>();

        out.println("getVaccinati");
        out.println(query);

        while (true) {
            String nomecittadino = in.readLine();

            if (nomecittadino.equals("exit"))
                break;
            else {
                String cognomecittadino = in.readLine();
                String codfisc = in.readLine();
                String vaccino = in.readLine();
                int idvacc = Integer.parseInt(in.readLine());

                vaccinati.add(new Vaccinato(
                        nomecittadino,
                        cognomecittadino,
                        codfisc,
                        null,
                        null,
                        Vaccino.valueOf(vaccino),
                        idvacc
                ));
            }
        }
        return vaccinati;
    }


    @Override
    public void insertDb(String query) throws IOException, SQLException {
        out.println("insertDb");
        out.println(query);
    }

    @Override
    public void populateCentriVaccinali(String nomeCentro) throws IOException, SQLException {
        Util util = new Util();
        out.println("populateCentriVaccinali");
        out.println(nomeCentro);
        out.println("CREATE TABLE vaccinati_" + util.formatTableName(nomeCentro) +
                " (" +
                "nomecittadino VARCHAR(50), " +
                "cognomecittadino VARCHAR(50), " +
                "codicefiscale VARCHAR(50), " +
                "data DATE, vaccino VARCHAR(20), " +
                "idvacc SMALLINT, " +
                "PRIMARY KEY(codicefiscale), " +
                "FOREIGN KEY(idvacc) REFERENCES idunivoci(idvacc), " +
                "FOREIGN KEY(codicefiscale) REFERENCES idunivoci(codicefiscale)" +
                ")");
    }

    @Override
    public ArrayList<CentroVaccinale> filter(String query) throws IOException, SQLException {
        out.println("filter");
        out.println(query);
        ArrayList<CentroVaccinale> centrivaccinali = new ArrayList<>();

        while (true) {

            String nome = in.readLine();

            if(nome.equals("exit"))
                break;
            else {
                Tipologia tipologia = Tipologia.valueOf(in.readLine());
                Qualificatore qualificatore = Qualificatore.valueOf(in.readLine());
                String strada = in.readLine();
                String civico = in.readLine();
                String comune = in.readLine();
                String provincia = in.readLine();
                String cap = in.readLine();

                centrivaccinali.add(new CentroVaccinale(
                        nome,
                        new Indirizzo(
                                qualificatore,
                                strada,
                                civico,
                                comune,
                                provincia,
                                cap
                        ),
                        tipologia
                ));
            }
        }

        return centrivaccinali;
    }

    @Override
    public UtenteRegistrato login(String query, String User) throws IOException {
        out.println("login");
        out.println(query);
        out.println(User);

        boolean find = Boolean.parseBoolean(in.readLine());

        if(!find)
            return null;
        else {
            isOperatore = Boolean.parseBoolean(in.readLine());
            if(isOperatore) {
                String nome = in.readLine();
                String cognome = in.readLine();
                String CF = in.readLine();
                String username = in.readLine();
                String password = in.readLine();

                UtenteRegistrato u = new UtenteRegistrato(
                        nome,
                        cognome,
                        CF,
                        username,
                        password
                );
                return u;
            }
            else {
                String nome = in.readLine();
                String cognome = in.readLine();
                String CF = in.readLine();
                String username = in.readLine();
                String password = in.readLine();
                String email = in.readLine();
                int idvacc = Integer.parseInt(in.readLine());

                CittadinoRegistrato u = new CittadinoRegistrato(
                        nome,
                        cognome,
                        CF,
                        email,
                        username,
                        password,
                        idvacc
                );
                return u;
            }
        }
    }

    @Override
    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }

    /**
     * Gets current socket.
     *
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Check if is operatore.
     *
     * @return the operatore
     */
    public boolean getOperatore() {
        return isOperatore;
    }
}
