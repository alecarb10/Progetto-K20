package mvc.model.team;

public class Player {
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

	@Override
	public String toString() {
		return "Player: " + this.name + " " + this.surname + "\n\tNumber: " + this.number + "\n\tPosition: " + this.position;
	}
}
