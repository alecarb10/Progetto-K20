package it.unipv.ingsw.k20.model.team;

public class Stadium {
	private String name;
	private String city;
	private int capacity;

	public Stadium(String name, String location, int capacity) {
		this.name = name;
		this.city = location;
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return city;
	}

	public void setLocation(String location) {
		this.city = location;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Stadium: " + this.name + "\n\tLocation: " + this.city + "\n\tCapacity: " + this.capacity;
	}
}