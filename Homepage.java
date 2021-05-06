package Cittadini;
import java.io.IOException;
import java.net.Socket;
import java.text.ParseException;
import java.util.Scanner;

import Piattaforma_cv.Client;

public class Homepage {
	
	public static void main(String args[]) throws IOException, ParseException, InterruptedException {
		
		Scanner in= new Scanner(System.in);
		String operatore;
		String query;
		Socket socket;
		proxy proxy=null;
		String User;
		
		do {
			
		System.out.println("Username: ");
		User= in.nextLine();
		System.out.println("Password: ");
		String Password= in.nextLine();
		
	 proxy= new proxy();
		socket=proxy.getSocket();
		System.out.println("ok");
		query= "select * from utentiregistrati where userid='"+User+"'and password='"+Password+"'";
		
		} while(!proxy.login(socket, query, User));
		
		operatore= proxy.getOperatore();
		System.out.println("ok");
		if(operatore.equals("0")) {
			Client client=new Client();
			
		}
		
		else if(operatore.equals("1")) {
			Cittadini cittadino= new Cittadini();
			
		}
	}
	}


