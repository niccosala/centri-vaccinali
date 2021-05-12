package com.uninsubria.serverCV;

import com.uninsubria.clientCV.centrivaccinali.entity.*;
import com.uninsubria.clientCV.cittadini.entity.CittadinoRegistrato;
import com.uninsubria.clientCV.condivisa.entity.UtenteRegistrato;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Proxy implements IComandiClient{

    private final Socket socket;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private boolean isOperatore = false;

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
    public CentroVaccinale pickCentro(String query) throws IOException {

        CentroVaccinale centroVaccinale = null;

        out.println("pickCentro");
        out.println(query);

        while (true) {

            String mex = in.readLine();

            if(mex.equals("exit"))
                break;
            else {
                String nome = mex;
                Tipologia tipologia = Tipologia.valueOf(in.readLine());
                Qualificatore qualificatore = Qualificatore.valueOf(in.readLine());
                String strada = in.readLine();
                String civico = in.readLine();
                String comune = in.readLine();
                String provincia = in.readLine();
                String cap = in.readLine();

                centroVaccinale = new CentroVaccinale(
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
                );
            }
        }
        return centroVaccinale;
    }

    @Override
    public ArrayList<Sintomo> getSintomi(String query) throws IOException, SQLException {

        ArrayList<Sintomo> sintomi = new ArrayList<>();

        out.println("searchSintomi");
        out.println(query);

        while (true) {

            String mex = in.readLine();

            if (mex.equals("exit"))
                break;
            else {
                String nomeSintomo = mex;
                String descrizione = in.readLine();

                System.out.println(nomeSintomo);
                System.out.println(descrizione);

                sintomi.add(new Sintomo(
                        nomeSintomo,
                        descrizione
                ));
            }
        }
        return sintomi;
    }

    @Override
    public ArrayList<String> getCentri(String query) throws IOException, SQLException {
        ArrayList<String> centriVaccinali = new ArrayList<>();

        out.println("searchCentri");
        out.println(query);

        while (true) {
            String mex = in.readLine();
            if (mex.equals("exit"))
                break;
            else {
                centriVaccinali.add(mex);
            }
        }
        return centriVaccinali;
    }

    @Override
    public void insertDb(String query) throws IOException, SQLException {
        out.println("insertDb");
        out.println(query);
    }

    @Override
    public void populateCentriVaccinali(String query, String nomeCentro) throws IOException, SQLException {
        out.println("populateCentriVaccinali");
        out.println(nomeCentro);
        out.println("create table vaccinati_" + nomeCentro + " ( nomecittadino varchar(50), cognomecittadino varchar(50), codfisc varchar(50) PRIMARY KEY, data DATE, vaccino varchar(20), idvaccino SERIAL)");
        out.println(query);
    }

    @Override
    public ArrayList<CentroVaccinale> filter(String query) throws IOException, SQLException {
        out.println("filter");
        out.println(query);
        ArrayList<CentroVaccinale> centrivaccinali = new ArrayList<>();

        while (true) {

            String mex = in.readLine();

            if(mex.equals("exit"))
                break;
            else {
                String nome = mex;
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
        System.out.println("STEP 1");
        out.println("login");
        out.println(query);
        out.println(User);

        boolean find = Boolean.parseBoolean(in.readLine());
        System.out.println("Found? " + find);

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
                System.out.println("op: " + u.getUsername());
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

                System.out.println(nome + " " + cognome + " " + CF + " " + email + " " + username + " " + password);

                CittadinoRegistrato u = new CittadinoRegistrato(
                        nome,
                        cognome,
                        CF,
                        email,
                        username,
                        password,
                        idvacc
                );
                System.out.println("reg: " + u.getNome() + " " + u.getCognome() + " " + u.getCF() + " " + u.getEmail() + " " + u.getUsername() + " " + u.getPassword() + u.getIdVaccinazione());
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

    public Socket getSocket() {
        return socket;
    }

    public boolean getOperatore() {
        return isOperatore;
    }
}

