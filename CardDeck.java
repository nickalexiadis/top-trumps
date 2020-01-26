package commandline;

import java.util.ArrayList;
import java.util.logging.Level;

public class CardDeck {
	/*
	 * This class will shuffle the card deck. This class will also check for a draw
	 * by comparing the 5 cards attributes, and will further place the cards that
	 * were satisfying the draw condition within the common pile.
	 */
//////////////////////////////////added by Linh//////////////////////////////////////////////////////
		//A deck contains an array of Cards
		private Cards[] cardsInDeck = new Cards[40];
////////////////////////////////////////////////////////////////////////////////////////////////////
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
			
				
/////////////////////////////added by Linh//////////////////////////////////////////////////////
				//this method can be used on a Hand, on a Deck on a communal pile as long as you give the corresponding array of cards as a parameter - it will convert all the cards in an Array of Strings
				public static String[] readDeckContents(Cards[] cd) {
					String[] deckContentArray;
					deckContentArray = new String[40];
					for (int i = 0; i < cd.length; i++) {
						deckContentArray[i] = cd[i].toString();
					}
					return deckContentArray;
				}
				
				
				//Convert the Array of Strings to one String with all the Deck Content Information for logging purposes
				public static String addDeckContentsStringsToOneString(Cards[] cd) {
					 StringBuffer sb = new StringBuffer(""); //String, where you can add stuff at the end, but it's the same object
					 String[] deckAsStrings = readDeckContents(cd);
					 for (String cardString : deckAsStrings) {//for each element in the the deckAsStrings array
						sb.append(cardString);
					}
					String completeDeckAsOneString = sb.toString();
					return completeDeckAsOneString;
				}
				
				//just Standard getters and setters, might have to be modified
				public Cards[] getCardsInDeck() {
					return cardsInDeck;
				}
				//just Standard getters and setters, might have to be modified
				public void setCardsInDeck(Cards[] cardsInDeck) {
					this.cardsInDeck = cardsInDeck;
				}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
}


