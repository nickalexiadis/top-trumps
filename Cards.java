package commandline;

import java.util.ArrayList;

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
		
//		Creates an array composed of the card attributes
		public ArrayList<Integer> attributeArray() {
			ArrayList<Integer> attrArr = new ArrayList<Integer>();
			attrArr.add(size);
			attrArr.add(speed);
			attrArr.add(cargo);
			attrArr.add(range);
			attrArr.add(firepower);
			
			return attrArr;
		}
		
		
//		Returns the highest attribute of a card 
		public int getMax() {
			
			ArrayList<Integer> attrArr = attributeArray();
			int max = attrArr.get(0);
			int maxIndex = 0;
			
			for (int i = 0; i < attrArr.size(); i++) {
				// wert  1 = 5 wert 2 = 7 wert 3 = 6
				if(attrArr.get(i) > max) {
					maxIndex = i;
					// this was missing, how can you do it without it?
					max = attrArr.get(i);
				}
				
			}
			
			return maxIndex;
		}
		
		public ArrayList<String> attributeNames() {
			ArrayList<String> arr = new ArrayList<String>();
			arr.add("size");
			arr.add("speed");
			arr.add("cargo");
			arr.add("range");
			arr.add("firepower");
			return arr;
		}

		
		public String toString() {
			String cardvalues = " Description: " + this.getDescription() + " Cargo: " + this.getCargo()  + " Firepower: " + this.getFirepower() + " Range: " + this.getRange() + " Speed: " + this.getSpeed() + " Size: " + this.getSize() + " ";
			return cardvalues;
		}

}