package com.uninsubria.serverCV;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


class Homepage {

    public static void main(String[] args) throws IOException, ParseException, InterruptedException, SQLException {

        Scanner in= new Scanner(System.in);
        boolean operatore;
        String query;
        Proxy proxy;
        String User;

        do {

            System.out.println("Username: ");
            User= in.nextLine();
            System.out.println("Password: ");
            String Password= in.nextLine();
            proxy = new Proxy();
            System.out.println("ok");
            query= "select * from utentiregistrati where userid='"+User+"'and password='"+Password+"'";

        } while(proxy.login(query, User) != null);

        operatore= proxy.getOperatore();
        System.out.println("ok");
        Client client;
        Cittadini cittadini;

        if(operatore)
            client = new Client();
        else
            cittadini = new Cittadini();

    }
}
