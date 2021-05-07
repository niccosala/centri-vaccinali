package com.uninsubria.serverCV;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class Cittadini {

    public Cittadini() throws IOException, SQLException, InterruptedException {
        start();
    }

    private void start() throws IOException, SQLException, InterruptedException {

        Scanner in= new Scanner(System.in);
        Proxy proxy= new Proxy();

        while(true) {

            System.out.println("Cosa vuoi fare? Cercare o visualizzare informazioni centro vaccinale(1), Registrarti (2) oppure Loggarti(3) oppure tornare indietro(4)?");
            String scelta= in.nextLine();

            if(scelta.equals("4")) {
                break;
            }


            else {

                String query;
                Socket socket = proxy.getSocket();


                if(scelta.equals("1")) {

                    System.out.println("Vuoi effettuare la ricerca per nome(1) o per comune/tipologia(2)?");
                    String choice= in.nextLine();

                    if(choice.equals("1")) {
                        System.out.println("Inserisci il nome da cercare: ");
                        String name= in.nextLine();
                        query= "SELECT * FROM centrivaccinali WHERE nome='"+name+"'";
                        proxy.filter(query);
                        proxy.close();
                    }

                    else {
                        System.out.println("Inserisci il comune: ");
                        String comune= in.nextLine();
                        System.out.println("Inserisci la tipologia: ");
                        String tipologia= in.nextLine();
                        query= "SELECT * FROM centrivaccinali WHERE indirizzo='"+comune+"' AND tipologia='"+tipologia+"'";
                        proxy.filter(query);
                        proxy.close();
                    }



                }

                if(scelta.equals("2")) {

                    System.out.println("Inserire nome: ");
                    String nome = in.nextLine();
                    System.out.println("Inserire cognome: ");
                    String cognome = in.nextLine();
                    System.out.println("Inserire codice fiscale: ");
                    String c_fisc = in.nextLine();
                    System.out.println("Inserire email: ");
                    String email= in.nextLine();
                    System.out.println("Inserire user_id: ");
                    String user_id= in.nextLine();
                    System.out.println("Inserire passwd: ");
                    String passwd= in.nextLine();
                    System.out.println("Inserire id_vacc: ");
                    String id_vacc= in.nextLine();

                    query = "INSERT INTO utentiregistrati VALUES('"+user_id+"','"+passwd+"','"+c_fisc+"','"+nome+"','"+cognome+"')";
                    Proxy proxyUtenti = new Proxy();
                    proxyUtenti.insertDb(query);

                    Thread.sleep(100);

                    query=  "INSERT INTO cittadiniregistrati VALUES('"+id_vacc+"','"+user_id+"','"+email+"')";
                    Proxy proxyCittadini = new Proxy();
                    proxyCittadini.insertDb(query);

                    proxyUtenti.close();
                    proxyCittadini.close();
                }

                if(scelta.equals("3")) {
                    String CF = "SAANCL99T06F205F";
                    Logged logged = new Logged(CF, socket);

                    proxy.close();
                }
            }

        }

    }


}
