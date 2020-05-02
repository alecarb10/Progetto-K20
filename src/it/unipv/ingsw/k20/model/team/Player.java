package it.unipv.ingsw.k20.model.team;

public class Player {
	private String name;
	private int number;
	private String position;
	private boolean holder; //titolare 
	
	
public Player (String name, int number, String position, boolean holder) {
	   
		this.name = name;
		this.number = number;
		this.position = position;
		this.holder = holder;
		
	    if (name==null)
	        throw new RuntimeException("Il nome non puo' essere nullo! Oggetto non creato");
	    if (number < 1) 
	        throw new RuntimeException("Il numero deve essere maggiore o uguale di uno! Oggetto non creato");
	    if (!(position.equals("portiere") || position.equals("difensore") || position.equals("centrocampista") || position.equals("attaccante")))
	        throw new RuntimeException("Il valore del ruolo e' errato! Oggetto non creato");
	    if (number==1 && !position.equals("portiere"))
	        throw new RuntimeException("Solo il portiere puo' avere il numero UNO! Oggetto non creato");
	    
	   
	}
	
	public void impostHolder(boolean holder) {
	    this.holder = holder;
	}
	
	public void impostNumber(int number) {
	    if (number==1 && !this.position.equals("portiere"))
	        throw new RuntimeException("Solo il portiere puo' avere il numero UNO! Aggiornamento non effettuato");
	    this.number = number;
	}
	
	public boolean getHolder() {
	    return this.holder;
	}
	
	public  String getName() {
	    return this.name;
	}
	
	public int getNumber() {
	    return this.number;
	}
	public String getposition() {
		return this.position;
	}
	public String toString() {
		return "-name-"+this.name+"-number-"+this.number+"-position-"+this.position+"-holder-"+this.holder;
	}
	


}
