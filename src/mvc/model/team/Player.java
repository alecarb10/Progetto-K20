package it.unipv.ingsw.k20.model.team;

public class Player {
	private String name;
	private String surname;
	private int number;
	private boolean holder;
	private PlayerPositionType position;

	public Player(String name, String surname, int number, PlayerPositionType position, boolean holder) {
		this.name = name;
		this.surname = surname;
		this.number = number;
		this.position = position;
		this.holder = holder;
	}

	public PlayerPositionType getPosition() {
		return position;
	}

	public void setPosition(PlayerPositionType position) {
		this.position = position;
	}

	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void setHolder(boolean holder) {
		this.holder = holder;
	}

	public boolean getHolder() {
		return this.holder;
	}

	@Override
	public String toString() {
		return "Player: " + this.name + " " + this.surname + "\n\tNumber: " + this.number + "\n\tPosition: " + this.position;
	}
}
