package Piattaforma_cv;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.Semaphore;



public class Server {

	
	
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		
		String User;
		String Password;
		Scanner in = new Scanner(System.in);
		Semaphore sem= new Semaphore(100);
		ServerSocket server = new ServerSocket(8888);
		
		do {
			System.out.println("Inserire credenziali di accesso al db/nUser: ");
			User= in.nextLine();
			System.out.println("Password: ");
			Password= in.nextLine();
		} while(!User.equals("postgres") && !Password.equals("pino"));
		
		try {
			System.out.println("Started " + server);
			
			while(true) {
				Socket socket = server.accept();
				System.out.println("Connection accepted: ");
				new Skeleton(socket, sem);
			}
		}
		catch(Exception e)  {
			e.printStackTrace();
		}
	}
			
}			




