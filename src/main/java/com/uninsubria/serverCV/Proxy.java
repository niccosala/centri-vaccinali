package com.uninsubria.serverCV;

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
    public String[] searchUser(String query) throws IOException {
        out.println("search_user");
        out.println(query);
        String password = in.readLine();
        String CF = in.readLine();

        return new String[]{password, CF};
    }

    @Override
    public ArrayList<String> getSintomi(String query) throws IOException, SQLException {
        ArrayList<String> sintomi = new ArrayList<>();

        out.println("searchSintomi");
        out.println(query);

        while (true) {
            String mex = in.readLine();
            if (mex.equals("exit"))
                break;
            else {
                sintomi.add(mex);
            }
        }
        return sintomi;
    }

    @Override
    public void insertDb(String query) throws IOException, SQLException {
        out.println("insert");
        out.println(query);
    }

    @Override
    public void populateCentriVaccinali(String query, String nomeTabella) throws IOException, SQLException {
        out.println("insert1");
        out.println(query);
        out.println("create table vaccinati_" + nomeTabella + " ( nomecognomecittadino varchar(50), codfisc varchar(50) PRIMARY KEY, data DATE, vaccino char(1), idvaccino smallint)");
    }

    @Override
    public void filter(String query) throws IOException, SQLException {
        out.println("find");
        out.println(query);
    }

    @Override
    public Boolean login(String query, String User) throws IOException {

        out.println("login");
        out.println(query);
        out.println(User);

        boolean find = Boolean.parseBoolean(in.readLine());

        if(!find)
            return false;
        else {
            isOperatore = Boolean.parseBoolean(in.readLine());
            return true;
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

