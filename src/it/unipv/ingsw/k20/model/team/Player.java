package it.unipv.ingsw.k20.model.team;

public class Player {
	private String name;
	private String surname;
	private int number;
	private boolean holder;
	private PlayerPositionType position;
	
public Player (String name, String surname, int number, String position, boolean holder) {
	   
	this.name = name;
	this.surname=surname;
	this.number = number;
	this.holder = holder;
	
	//I check the correctness of the data
	if (name==null)
		throw new RuntimeException("the name cannot be null! Object not created");
	if (number < 1) 
	        throw new RuntimeException("the number must be greater than or equal to one! Object not created");
	if (!(position.equals("portiere") || position.equals("difensore") || position.equals("centrocampista") || position.equals("attaccante")))
	        throw new RuntimeException("Il valore del ruolo e' errato! Object not create");
	if (number==1 && !position.equals("portiere"))
	        throw new RuntimeException("Only the goalkeeper cha have the number one !Object not create");
	    
	   
	}
	
	public PlayerPositionType getPosition() {
	return position;
	}
	
	public void setPosition(PlayerPositionType position) {
		this.position = position;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setHolder(boolean holder) {
	    this.holder = holder;
	}
	
	
	// check the correctness of the number with respect to the role
	public void setNumber(int number) {
	    if (number==1 && !PlayerPositionType.CB.equals("goalkeeper"))
	        throw new RuntimeException("Only the goalkeeper cha have the number one!update is not performed");
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
	public PlayerPositionType getposition() {
		return this.position;
	}
	public String toString() {
		return "-name-"+this.name+"-surname-" +this.surname +"-number-"+this.number+"-position-"+this.position+"-holder-"+this.holder;
	}
	


}
