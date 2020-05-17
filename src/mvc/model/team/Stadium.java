package mvc.model.team;

public class Stadium {
	private String name;
	private String city;
	private int capacity;

	public Stadium(String name, String city, int capacity) {
		this.name = name;
		this.city = city;
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String location) {
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
		return "Stadium: " + this.name + "\n\tCity: " + this.city + "\n\tCapacity: " + this.capacity;
	}
}