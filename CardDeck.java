package commandline;

import java.util.ArrayList;
import java.util.logging.Level;

public class CardDeck {
	/*
	 * This class will shuffle the card deck. This class will also check for a draw
	 * by comparing the 5 cards attributes, and will further place the cards that
	 * were satisfying the draw condition within the common pile.
	 */
		
		//deck size is always 40
	    private int DECK_SIZE=40;
	    //initial deck, empty
	 	private ArrayList<Integer> deck = new ArrayList<Integer>();
				
		       //shuffles the card deck, reference: https://stackoverflow.com/questions/39557701/shuffle-a-deck-of-cards-in-java
				public void shuffleCards() {
				
			        //fill the deck
					for (int i = 0; i < DECK_SIZE; ++i) {
			            deck.add(i);
			        }
					//the shuffled deck array list where the shuffled deck will be stored 
			        ArrayList<Integer> shuffledDeck = new ArrayList<Integer>();
			        //shuffles the initial deck
			        while (deck.size() > 0) {
			            int index = (int) (Math.random() * deck.size());
			            shuffledDeck.add(deck.remove(index));
			        }
//					if (TopTrumpsCLIApplication.writingTestLogs==true) {
//						TopTrumpsCLIApplication.testlog.log(Level.FINE, "The contents of the complete deck after it has been shuffled" + "getDeckContent");
//					}
				}
				
				//method to check for draw
				public static boolean checkForDraw(Cards[] card) {	
					//dummy return
					return false;
				}
		
				
				//the cards that were checked in the previous method are now placed in the common pile
				public void addCommonPile() {//also removeCommonPile has to be added and a method to get contents from CommonPile
				}
				
				//public void initialize CardDeck
				//... - Methods to initialize Card Deck
				//Write into Testlog, if active
				//if (TopTrumpsCLIApplication.writingTestLogs==true) {
				//				TopTrumpsCLIApplication.testlog.log(Level.FINE, "The contents of the complete deck once it has been read in and constructed" + "getDeckContent");
				//}
				
				//public String[]/String getDeckContent
				// Move through the Deck and read the card objects and add their content to a String-Array or a String-Buffer
				//A long String would be prefered, so only one log entry is needed
				
}


