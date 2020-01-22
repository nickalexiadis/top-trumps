package commandline;


import java.util.Random;

public class CardDeck {
	        
			//shuffles the card deck, <<reference needed>>
			public void shuffleCards(int cards[], int n) {
				
				Random random = new Random();
				
				for (int i = 0; i < n; i++){
					
					int randomShuffle = i + random.nextInt(40 - i);
					int temp = cards[randomShuffle]; //used to iterate the value of the Card array
					cards[randomShuffle] = cards[i];
					cards[i] = temp;
				}	
			}
			
			//if there is a draw, a card is taken and placed in the common pile
			public void commonPile() {
				
			}
			

			//deals the card deck (in the GamePlay) <<reference needed>>
			public Cards[] dealCards() {
				
				Cards[] drawnCards = new Cards[numCards];
			    
				for(int index = 0; index < numCards; index ++) {
			            drawnCards[index] = cards[topCard];
			            topCard++;
			        }
			        return drawnCards;
			}
			 
}