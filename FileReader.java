package commandline;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import online.GUI.Card;
public class FileReader {
	public static void main(String[] args) throws FileNotFoundException {
	    Scanner input = new Scanner(new File("StarCitizenDeck.txt"));
	    input.useDelimiter("-|\n");

	    Card[] cards = new Card[0];
	    while(input.hasNext()) {
	        String description = input.next();
	        int size = input.nextInt();
	        int speed = input.nextInt();
	        int cargo = input.nextInt();
	        int range = input.nextInt();
	        int firepower = input.nextInt();

	        Card newCard = new Card(description, size, speed, cargo, range, firepower);
	        cards = addCard(cards, newCard);
	    }

	    for (Card card : cards) {
	        System.out.println(card);
	    }
	}

	private static Card[] addCard(Card[] cards, Card cardToAdd) {
	    Card[] newCards = new Card[cards.length + 1];
	    System.arraycopy(cards, 0, newCards, 0, cards.length);
	    newCards[newCards.length - 1] = cardToAdd;

	    return newCards;
	}
	public static class Card{
		
		//private attributes
		private String description;
		private int size;
		private int speed;
		private int cargo;
		private int range;
		private int firepower;
		
		//cards constructor	
		public Card(String description, int size, int speed, int cargo, int range, int firepower) {
					        
					        this.description = description;
					        this.size = size;
					        this.speed = speed;
					        this.cargo = cargo;
					        this.range = range;
					        this.firepower = firepower;
					    }
		 public String toString() {
		        return String.format("Description: \r\nSize: \r\nSpeed: \r\nCargo: \r\nRange: \r\nFirepower: \r\n", 
		                description, size, speed, cargo, range, firepower);
		    }
	}

}
