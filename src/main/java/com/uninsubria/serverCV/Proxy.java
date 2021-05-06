package com.uninsubria.serverCV;

import java.awt.List;
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
        socket = new Socket(addr, IServerCommands.PORT);

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

    public String[] searchUser(Socket socket, String query) throws IOException {

        out.println("search_user");
        out.println(query);
        String password = in.readLine();
        String c_fiscale = in.readLine();

        return new String[]{password, c_fiscale};
    }


    public void uploadToDb(Socket socket, String query) throws IOException {

        out.println("insert");
        out.println(query);

    }

    public void uploadToDb1(Socket socket, String query, String nome_tabella) throws IOException {

        out.println("insert1");
        out.println(query);
        out.println("create table vaccinati_" + nome_tabella + " ( nomecognomecittadino varchar(50), codfisc varchar(50) PRIMARY KEY, data DATE, vaccino char(1), idvaccino smallint)");

    }


    public void findByName(Socket socket, String query) throws IOException {

        out.println("find");
        out.println(query);


    }


    public void findbyComune_Tipologia(Socket socket, String query) throws IOException {

        out.println("find");
        out.println(query);

    }

    @SuppressWarnings("unchecked")
    public ArrayList<String> getSintomi(Socket socket, String query) throws IOException {

        ArrayList Sintomi = new ArrayList<String>();

        out.println("searchSintomi");
        out.println(query);

        for (int i = 0; ; i++) {
            String mex = in.readLine();
            if (mex.equals("exit"))
                break;
            else {
                Sintomi.add(mex);

            }
        }
        return Sintomi;
    }
}

