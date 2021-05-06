package com.uninsubria.serverCV;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Logged {

    public Socket socket;

    public Logged(String c_fiscale, Socket socket) throws IOException {
        this.socket=socket;
        start(c_fiscale);
    }

    private void start(String c_fiscale) throws IOException {

        Scanner in = new Scanner(System.in);
        Proxy proxy= new Proxy();

        while(true) {

            System.out.println("Vuoi dare una valutazione(1) oppure tornare indietro(2)? ");
            String scelta= in.nextLine();

            if(scelta.equals("1")) {
                String query= "SELECT * FROM eventiavversi";
                ArrayList<String> sintomi= proxy.getSintomi(query);

                for(int i=0; i<sintomi.size();i++)
                    System.out.println((i+1)+": " + sintomi.get(i));

                System.out.println("Inserisci il numero corrispondente al sintomo: ");
                String sintomo= in.nextLine();
                Socket socket=  proxy.initializeConnection();
                System.out.println("Inserisci una valutazione numerica da 1 a 5");
                String severita= in.nextLine();

                String query1= "INSERT INTO segnalano VALUES('"+c_fiscale+"','"+sintomo+"','"+severita+"')";
                proxy.uploadToDb(socket, query1);
            }
            else
                break;
        }
    }




}
