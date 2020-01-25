

public class Player {
		
			private int playerID;
			private int winCounter; 
			private int numberOfPlayers;
			private CardDeck hand; 
			private Card topCard; 
			
		//constructor for the player and how many cards the player has
		public Player(int playerID, CardDeck hand){
			this.playerID = playerID;
			this.hand = hand;
				
		}	
			
		//getter for playerID which is index of the players in the game
		public int getPlayerID(){
			return playerID;
		}
//		
//		//string value of name:
//		public String toString(){
//			return getPlayerID();
//		}
		
		
		//places all players' cards at the bottom of the deck IF one of the players is the winner of the round
		public void placeCard() {
			
		}

		public CardDeck getHand() {
			return hand;
		}

		public void setHand(CardDeck hand) {
			this.hand = hand;
		}
		public Card getTopCard() {
			return hand.getTopCard();
			
		}

		
	
		
}