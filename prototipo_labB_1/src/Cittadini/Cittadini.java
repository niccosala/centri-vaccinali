package Cittadini;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cittadini {
	
	public Cittadini() throws IOException {
		
		start();
		
	}
	
	private void start() throws IOException {
		
		Scanner in= new Scanner(System.in);
		proxy proxy= new proxy();
		
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
					proxy.findByName(socket, query);
					proxy.close();
				}
				
				else {
					System.out.println("Inserisci il comune: ");
					String comune= in.nextLine();
					System.out.println("Inserisci la tipologia: ");
					String tipologia= in.nextLine();
					query= "SELECT * FROM centrivaccinali WHERE indirizzo='"+comune+"' AND tipologia='"+tipologia+"'";
					proxy.findbyComune_Tipologia(socket, query);
					proxy.close();
				}
				
				
				
			}
			
			if(scelta.equals("2")) {
				
				System.out.println("Inserire nome/cognome: ");
				String nome_cognome= in.nextLine();
				System.out.println("Inserire codice fiscale: ");
				String c_fisc= in.nextLine();
				System.out.println("Inserire email: ");
				String email= in.nextLine();
				System.out.println("Inserire user_id: ");
				String user_id= in.nextLine();
				System.out.println("Inserire passwd: ");
				String passwd= in.nextLine();
				System.out.println("Inserire id_vacc: ");
				String id_vacc= in.nextLine();
				
				query=  "INSERT INTO cittadiniregistrati VALUES('"+nome_cognome+"','"+id_vacc+"','"+c_fisc+"','"+user_id+"','"+email+"','"+passwd+"')"; 
				proxy.uploadToDb(socket, query);
				proxy.close();
				
			}
			
			if(scelta.equals("3")) {
				
				System.out.println("User: ");
				String User= in.nextLine();
				query=  "SELECT * FROM cittadiniregistrati WHERE userid='"+User+"'"; 
				String info[]= proxy.searchUser(socket, query);
				proxy.close();
				String pass;
				
				do {
					System.out.println("Password: ");
					 pass= in.nextLine();
					
				} while(!pass.equals(info[0]));
				Logged l= new Logged(info[1], socket);
				
			}
		}
		
	}

	}	
		

}
