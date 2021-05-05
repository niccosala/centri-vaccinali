package Prove_concorrenza;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import Cittadini.proxy;

public class altro_thread extends Thread {
	
	public void run() {
		Socket socket = null;
		proxy proxy= null;
		try {
			proxy= new proxy();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		socket = proxy.getSocket();
	    String query=  "SELECT * FROM test_table WHERE nome= 'nome'"; 
	    try {
			proxy.findByName(socket, query);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
