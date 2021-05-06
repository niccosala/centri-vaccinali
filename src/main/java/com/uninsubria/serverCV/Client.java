package com.uninsubria.serverCV;

import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Client {

    public Client() throws ParseException {

        try {
            start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void start() throws IOException, ParseException {

        Scanner in= new Scanner(System.in);
        Proxy proxy= new Proxy();

        while(true) {
            System.out.println("Cosa vuoi fare? Registra centro Vaccinale(1) oppure Registra vaccinato(2) oppure Tornare indietro(3)");

            String scelta= in.nextLine();

            if(scelta.equals("3"))
                break;

            else {

                String query;
                Socket socket = proxy.initializeConnection();

                if(scelta.equals("1")) {

                    System.out.println("Inserire nome: ");
                    String nome= in.nextLine();
                    System.out.println("Inserire indirizzo: ");
                    String indirizzo= in.nextLine();
                    System.out.println("Inserire tipologia: ");
                    String tipologia= in.nextLine();

                    query=  "INSERT INTO centrivaccinali VALUES('"+nome+"','"+indirizzo+"','"+tipologia+"')";

                    proxy.uploadToDb1(socket, query, nome);
                }

                if(scelta.equals("2")) {

                    System.out.println("Inserire nome: ");
                    String nome= in.nextLine();
                    System.out.println("Inserire nome/cognome: ");
                    String nome_cognome= in.nextLine();
                    System.out.println("Inserire c_fiscale: ");
                    String c_fiscale= in.nextLine();
                    System.out.println("Inserire data: ");

                    String data= in.nextLine();
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date myDate = formatter.parse(data);
                    java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

                    System.out.println("Inserire nome_vacc: ");
                    String nome_vacc= in.nextLine();
                    System.out.println("Inserire id_vacc: ");
                    String id_vacc= in.nextLine();

                    query=  "INSERT INTO vaccinati_"+nome+" VALUES('"+nome_cognome+"','"+c_fiscale+"','"+sqlDate+"','"+nome_vacc+"','"+id_vacc+"')";

                    proxy.uploadToDb(socket, query);
                }

                proxy.close();

            }
        }
    }
}



