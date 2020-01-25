package commandline;

public class Player {
	private String playerID;
	private int winCounter;
	private int numberOfPlayers;
	private Cards[] hand;

//constructor for the player and how many cards the player has
	public Player(String playerID, Cards[] hand) {
		this.playerID = playerID;
		this.hand = hand;

	}

//getter for name
	public String getPlayerID() {
		return playerID;
	}
	
//getter for winCounter
	public int getWinCounter() {
		return this.winCounter;
	}
	
// getter for handsize
	public int getHandSize() {
		return this.hand.length;
	}

//string value of name
	public String toString() {
		return getPlayerID();
	}

//places all players' cards at the bottom of the deck IF one of the players is the winner of the round
	public void placeCard() {

	}
}
