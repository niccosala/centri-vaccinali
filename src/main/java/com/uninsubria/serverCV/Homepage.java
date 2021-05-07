package com.uninsubria.serverCV;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Homepage {

    public static void main(String[] args) throws IOException, ParseException, InterruptedException, SQLException {

        Scanner in = new Scanner(System.in);
        boolean operatore;
        String query, user;
        Proxy proxy;

        do {
            System.out.print("Username: ");
            user = in.nextLine();
            System.out.print("Password: ");
            String Password= in.nextLine();
            proxy = new Proxy();
            System.out.println("ok");
            query= "select * from utentiregistrati where userid = '" + user + "' and password='" + Password + "'";
            System.out.println("query utenti-reg: " + query);
        } while(!proxy.login(query, user));

        operatore = proxy.getOperatore();
        System.out.println("ok");
        Client client;
        Cittadini cittadini;

        System.out.println(operatore);
        if(operatore)
            client = new Client();
        else
            cittadini = new Cittadini();

    }
}
