package domain.team;

/**
 * Stadium class.
 * 
 */
public class Stadium {
	private String name;
	private String city;
	private int capacity;

	/**
	 * Stadium constructor.
	 * @param name 
	 * @param city City where it's located
	 * @param capacity Places avaliable for fans
	 * 
	 */
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

	public void setCity(String city) {
		this.city = city;
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