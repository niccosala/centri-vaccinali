package com.uninsubria.serverCV;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Proxy {

    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

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

    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }

    public Socket getSocket() {
        return socket;
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public Socket initializeConnection() throws IOException {
        InetAddress serverAddr = InetAddress.getByName(null);
        return new Socket(serverAddr, 8888);
    }

    public String[] searchUser(String query) throws IOException {
        out.println("search_user");
        out.println(query);
        String password = in.readLine();
        String CF = in.readLine();

        return new String[]{password, CF};
    }

    public void uploadToDb(String query) throws IOException {
        out.println("insert");
        out.println(query);
    }

    public void uploadToDb1(String query, String nomeTabella) throws IOException {
        out.println("insert1");
        out.println(query);
        out.println("create table vaccinati_" + nomeTabella + " ( nomecognomecittadino varchar(50), codfisc varchar(50) PRIMARY KEY, data DATE, vaccino char(1), idvaccino smallint)");
    }

    public void filterByName(String query) throws IOException {
        out.println("find");
        out.println(query);
    }


    public void filterByComuneTipologia(String query) throws IOException {
        out.println("find");
        out.println(query);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<String> getSintomi(String query) throws IOException {

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
}

