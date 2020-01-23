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
