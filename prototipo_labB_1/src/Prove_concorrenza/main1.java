package Prove_concorrenza;

public class main1 {
	
	

		public static void main(String[] args) {
			
			altro_thread[] clienti= new altro_thread[1000];
			
			for(int i=0;i<1000;i++) {
				clienti[i]= new altro_thread();
			}
			
			for(altro_thread cliente : clienti) {
				cliente.start();
			}

		

	}


}
