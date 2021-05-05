package Piattaforma_cv;


public class Centro_vaccinale {
	
	private String nome;
	private String  location;
	private String hub;
	
	public Centro_vaccinale(String n, String l, String h) {
		
		nome= n;
		location=l;
		hub=h;
	}
	
	public String toString() {
		
		return nome +"  "+location.toString()+"  "+hub;
		
	}
	
	public String getnome() {
		return nome;
	}
	
	public String getlocation() {
		return location;
	}
	
	public String gethub() {
		return hub;
	}

}
