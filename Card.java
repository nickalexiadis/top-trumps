package commandline;

/*
a card has 6 attributes: description (string) and
 an array of 5 attribute values (int)
a 


*/
public class Card {
		//private attributes
			private String description;
			private int size;
			private int speed;
			private int cargo;
			private int range;
			private int firepower;
		
			//Card constructor	
		public Card(String description, int size, int speed, int cargo, int range, int firepower) {
			        
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
		
		/*a method that returns the value
		of the card attribute played through getters
		*/
		public int getValue( int i){
			if (i=1){
				return getSize();
			}
			if (i=2){
				return getSpeed();
			}
			if (i=3){
				return getCargo();
			}
			if (i=4){
				return getRange();
			}
			if (i=5){
				return getFirepower();
			}

		}
		
		

}