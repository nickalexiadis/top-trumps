package commandline;

public class Cards {
	// private attributes
	private String description;
	private int size;
	private int speed;
	private int cargo;
	private int range;
	private int firepower;

	// cards constructor
	public Cards(String description, int size, int speed, int cargo, int range, int firepower) {

		this.description = description;
		this.size = size;
		this.speed = speed;
		this.cargo = cargo;
		this.range = range;
		this.firepower = firepower;
	}

//getters
	public String getDescription() {
		return description;
	}

	public int getSize() {
		return size;
	}

	public int getSpeed() {
		return speed;
	}

	public int getCargo() {
		return cargo;
	}

	public int getRange() {
		return range;
	}

	public int getFirepower() {
		return firepower;
	}
	
	//toString Method needed added by Linh
	public String toString() {
		String cardvalues = " Cargo: " + this.getCargo() + " Description: " + this.getDescription() + " Firepower: " + this.getFirepower() + " Range: " + this.getRange() + " Speed: " + this.getSpeed() + " Size: " + this.getSize() + " ";
		return cardvalues;
	}
}
