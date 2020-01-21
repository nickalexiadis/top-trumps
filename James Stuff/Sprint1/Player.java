package Sprint1;

public class Player {

	// Attributes
	private String name;
	private Card[] hand;
	private int numberOfPlayers; // Move to the class where the player's user input is taken.
	private int winCounter;
	private boolean hasCards;

	// Constructor
	public Player(String name) {

		this.name = name;
		hand = new Card[0];
		hasCards = true;
		winCounter = 0;
		numberOfPlayers = 0; 
	}

	// Getters
	public String getName() {
		return name;
	}

	public Card[] getHand() {
		return hand;
	}

	public int getHandNumber() {
		return hand.length;
	}
	
	public Card getTopCard() {
		return hand[0];
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public int getWinCounter() {
		return winCounter;
	}

	public boolean getHasCards() {
		return hasCards;
	}

	

	// Method to check if Player has no cards left
	public boolean hasZeroCards() {
		if (getHandNumber() == 0)
			hasCards = false;
		return hasCards;
	}

	// Method to play the player's top card and update their hand array
	public void playTopCard() {

		getTopCard(); // Returns top Card before hand is updated.
		Card[] newArray = new Card[getHandNumber() - 1];

		// The loop fills up the new hand array with the same cards except for the top
		// card
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = hand[i + 1];
		}
		
		this.hand = newArray;
	}
}