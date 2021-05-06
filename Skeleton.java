package Piattaforma_cv;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Semaphore;
import java.io.*;
import java.net.Socket;

public class Skeleton extends Thread  {
	
	BufferedReader in=null;
	PrintWriter out=null;
	Semaphore sem;
	Socket socket;
	Server server = new Server();

	public Skeleton(Socket socket, Semaphore semaphore) {
		this.socket = socket;
		sem=semaphore;
		start();
	}
	
	public void run() {
		
		try {
			sem.acquire();
		} 
		catch (InterruptedException e3) {
			e3.printStackTrace();
		}
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        try (Connection c = DriverManager.getConnection(
                 "jdbc:postgresql://localhost:5432/postgres","postgres", "pino")) {
             System.out.println("Connessione riuscita!");
		try {
				 in = new BufferedReader(
						new InputStreamReader(socket.getInputStream())
						);
				 out = new PrintWriter(
						new BufferedWriter(
								new OutputStreamWriter(socket.getOutputStream())
								),
						true
						);
				serveClient(in, out, c);	
		}
		 catch(IOException e) {
				
				if (out != null) {
					out.close();
				}
				if (in != null) {
					try {
						in.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
        } 
        catch (SQLException e1) {
				e1.printStackTrace();
			}
		}


	private void serveClient(BufferedReader in, PrintWriter out, Connection c) throws IOException, SQLException {
		
		String operation;
		Statement stmt;
		Statement stmt1;
		
		while ((operation = in.readLine()) != null) {
			int result = 0;
			if (operation.equals("insert")) {
				
				String query= in.readLine();
				stmt = c.createStatement();
				try {
					stmt.executeUpdate(query);
				} catch(Exception e) {
					e.printStackTrace();
				}
				close();
				
 		}
			else if (operation.equals("insert1")) {
				
				String query= in.readLine();
				String query1= in.readLine();
				
				stmt = c.createStatement();
				stmt1= c.createStatement();
				try {
					stmt.executeUpdate(query);
					stmt1.executeUpdate(query1);
				} catch(Exception e) {
					e.printStackTrace();
				}
				close();
				
 		}
			else if (operation.equals("login")) {
				
				String query= in.readLine();
				String User=in.readLine();
				stmt= c.createStatement();
				
				ResultSet rs = stmt.executeQuery(query);
				int i=0;
				while (rs.next()) {
					i++;
					System.out.println(i);
				}

				
				//int i=0;
			/*	while (rs.next()) {
					i++;
				}*/
				if(i!=0) {
					String query1= "select userid from utentiregistrati where userid='"+User+"' intersect select userid from cittadiniregistrati where userid='"+User+"'";
					Statement st= c.createStatement();
					ResultSet r= st.executeQuery(query1);
					int j=0;
					while(r.next()) {
						j++;
					}
					out.println("j");
					out.println(j);
				}
				
				else
					out.println("i");
					
				
				
				
			}
			
			else if(operation.equals("search_user")) {
		
				String query= in.readLine();
				System.out.println(query);
				stmt= c.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					out.println(rs.getString("password"));
					out.println(rs.getString("codfisc"));
				}
				close();
 	
			}
			
			else if(operation.equals("find")) {
		
				String query= in.readLine();
				System.out.println(query);
				stmt= c.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					System.out.println("NOME: " + rs.getString("nome"));
					System.out.println("tipologia: " + rs.getString("tipologia"));
					System.out.println("indirizzo: " + rs.getString("indirizzo"));
				}
				close();
			}
			
			else if(operation.equals("searchSintomi")) {

				String query= in.readLine();
				System.out.println(query);
				stmt= c.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				try {
					while (rs.next()) {
						out.println(rs.getString("sintomo"));
					}
					out.println("exit");
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			else {
				System.err.println("Operation not recognized: " + operation);
			}
			out.println(result);
			close();
		}
	
		sem.release();
		
	}
	
	private void close() throws IOException {
		in.close();
		out.close();
		socket.close();
		
	}
}

