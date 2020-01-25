package commandline;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/* This class will shuffle the card deck. This class will also check for a draw by comparing the 5 cards attributes, and will further place the
 *  cards that were satisfying the draw condition within the common pile. 
 */
public class CardDeck {
	
	//deck size is always 40
    private int DECK_SIZE=40;
    //initial deck, empty
 	private ArrayList<Cards> deck;
			
 			public CardDeck(ArrayList<Cards> deck) {
 				this.deck = deck;
 			}
 			
 		
 			public void initializeDeck() {
 				File text = new File("");
 				Scanner t = new Scanner(text);
 				
 				int i = 0;
 				while(t.hasNextLine()) {
// 				or while(i <= 40)
// 					Problem with add for some reason!!
 					deck.get(i).add(new Cards(t.next(),t.nextInt(),t.nextInt(),t.nextInt(),t.nextInt(),t.nextInt()));
// 					t.nextLine(); ?
 					i++;
 				}
 				
 		
 			}
 	
 			
//	       shuffles the card deck, reference: https://stackoverflow.com/questions/39557701/shuffle-a-deck-of-cards-in-java
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
			}
			
			//method to check for draw
//			public static boolean checkForDraw(Cards[] card) {
//				
//				
//			}
//	
			public void dealCards(int cardsPerPlayer, Player player) {
				for(int i = 0; i<= cardsPerPlayer; i++) {
					player.addCard(deck.get(i));
				}
				for(int i = 0; i <= cardsPerPlayer; i++) {
					deck.remove(i);
				}
			}
			//the cards that were checked in the previous method are now placed in the common pile
			public void addCommonPile() {
				
			}
			 
}
