package Sprint1;

import java.util.Scanner;

public class Player {

	// Attributes
	private String playerID;
	private Card[] hand;
	private int numberOfPlayers; // Move to the class where the player's user input is taken.
	private int winCounter;
	private boolean hasCards;
	private boolean isHuman;

	// Constructor
	public Player(String playerID) {

		this.playerID = playerID;
		hand = new Card[0];
		winCounter = 0;
		numberOfPlayers = 0;
		hasCards = true;
		isHuman = false;
	}

	// Getters
	public String getPlayerID() {
		return playerID;
	}

	public Card[] getHand() {
		return hand;
	}

	public int getHandSize() {
		return hand.length;
	}

	public Card viewTopCard() {
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

	// Setters
	public void setIsHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

	// Method for players to pick categories
	public void chooseCategory() {
		int chosenCategory;
		Scanner s = new Scanner(System.in);
		if (isHuman = true) {
			System.out.println("Choose your category (enter an integer between 1-5");
			chosenCategory = s.nextInt(); 
		} else {
			for(int i = 0; i < categories.length; i++) { //need array for card categories
				chosenCategory = 1;
				if(categories[i] > chosenCategory) {
					chosenCategory = categories[i];
				}
			}
		}
	}

	// Method to check if Player has no cards left
	public boolean hasZeroCards() {
		if (getHandSize() == 0)
			hasCards = false;
		return hasCards;
	}

	// Method to play the player's top card and update their hand array
	public void playTopCard() {

		viewTopCard(); // Returns top Card before hand is updated.
		Card[] newArray = new Card[getHandSize() - 1];

		// The loop fills up the new hand array with the same cards except for the top
		// card
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = hand[i + 1];
		}

		this.hand = newArray;
	}
}