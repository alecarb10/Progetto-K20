package model.team;

public class Player {
	
	private int id;
	private String name;
	private String surname;
	private int number;
	private PlayerPositionType position;

	public Player(String name, String surname, int number, PlayerPositionType position) {
		this.name = name;
		this.surname = surname;
		this.number = number;
		this.position = position;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
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
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname=surname;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return this.number;
	}

	@Override
	public String toString() {
		return "Player: " + this.name + " " + this.surname + "\n\tNumber: " + this.number + "\n\tPosition: " + this.position;
	}
	
	@Override
	public int hashCode() {
		return ((Integer)id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		Player player = (Player) obj;
		return player.getId() == this.getId();
	}
}
