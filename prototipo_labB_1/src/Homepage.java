import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import Cittadini.Cittadini;
import Piattaforma_cv.Client;

public class Homepage {
	
	public static void main(String args[]) throws IOException, ParseException {
		
		Scanner in= new Scanner(System.in);
		
		while(true) {
		System.out.println("Sei un cittadino(1) o un operatore sanitario(2)?");
		
		int scelta= in.nextInt();
		
		if(scelta==2) {
			Client client=new Client();
			
		}
		
		else if(scelta==1) {
			Cittadini cittadino= new Cittadini();
			
		}
	}
	}

}
