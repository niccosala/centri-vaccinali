package Prove_concorrenza;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import Cittadini.proxy;

public class Client_thread extends Thread {
	
	Scanner in= new Scanner(System.in);
	
	public void run() {
		Socket socket = null;
		proxy proxy = null;
		try {
			proxy = new proxy();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			socket = proxy.initializeConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String query= "INSERT INTO test_table_3 VALUES('ciao','1','ciao')"; 
		try {
			proxy.uploadToDb(socket, query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	

}
