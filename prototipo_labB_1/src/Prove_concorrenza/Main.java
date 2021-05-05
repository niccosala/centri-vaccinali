package Prove_concorrenza;

public class Main {

	public static void main(String[] args) {
		
		Client_thread[] clienti= new Client_thread[500];
		
		
		for(int i=0;i<500;i++) {
			clienti[i]= new Client_thread();
			
			
			

		}
		
		for(Client_thread cliente : clienti) {
			cliente.start();
		}

	}

}
