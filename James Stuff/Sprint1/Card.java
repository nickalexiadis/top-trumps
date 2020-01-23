package Sprint1;

public class Card {
	
		// Attributes
		private String cardName;
		private int size;
		private int speed;
		private int range;
		private int cargo;
		private int firepower;

		// Constructors
		public Card (String cardName, int size, int speed, int range, int cargo, int firepower) {
			
			this.cardName = cardName;
			this.size = size;
			this.speed = speed;
			this.range = range;
			this.cargo = cargo;
			this.firepower = firepower;
		}
	
		//Getters
		public String getCardName() {
			return cardName;
		}
		
		public int getSize() {
			return size;
		}
		
		public int getSpeed() {
			return speed;
		}
		
		public int getRange() {
			return range;
		}
		
		public int getCargo() {
			return cargo;
		}
		
		public int getFirepower() {
			return firepower;
		}
		
		// ToString: I think the toString goes in the Deck class
		// It will use getters from this class to construct it.
//		}
}
