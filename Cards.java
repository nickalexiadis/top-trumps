package commandline;

public class Cards {
		//private attributes
			private String description;
			private int size;
			private int speed;
			private int cargo;
			private int range;
			private int firepower;
		
			//cards constructor	
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
		public String getSize() {
			return String.valueOf(size);
		}
		public String getSpeed() {
			return String.valueOf(speed);
		}
		public String getCargo() {
			return String.valueOf(cargo);
		}
		public String getRange() {
			return String.valueOf(range);
		}
		public String getFirepower() {
			return String.valueOf(firepower);
		}
		
		
		

}