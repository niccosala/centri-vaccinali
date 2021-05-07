package com.uninsubria.serverCV;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Logged {

    public Socket socket;

    public Logged(String CF, Socket socket) throws IOException, SQLException {
        this.socket=socket;
        start(CF);
    }

    private void start(String CF) throws IOException, SQLException {

        Scanner in = new Scanner(System.in);
        Proxy proxy= new Proxy();

        while(true) {

            Proxy proxySegnalano = new Proxy();

            System.out.println("Vuoi dare una valutazione(1) oppure tornare indietro(2)? ");
            String scelta= in.nextLine();

            if(scelta.equals("1")) {
                String query= "SELECT * FROM eventiavversi";
                ArrayList<String> sintomi = proxy.getSintomi(query);

                for(int i=0; i<sintomi.size();i++)
                    System.out.println((i)+": " + sintomi.get(i));

                System.out.println("Inserisci il numero corrispondente al sintomo: ");
                String sintomo= in.nextLine();
                System.out.println("Inserisci una valutazione numerica da 1 a 5");
                String severita= in.nextLine();

                String query1= "INSERT INTO segnalano VALUES('"+ CF +"'," + Integer.parseInt(sintomo) + ","+ Integer.parseInt(severita) +")";
                proxySegnalano.insertDb(query1);
            }
            else
                break;
        }
    }




}
