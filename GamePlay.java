package commandline;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GamePlay {

	// Declare Variables
	private int round, choice, drawCounter;
	private boolean draw, logCheck;
	private Player user, player1, player2, player3, player4;
	private Player gameWinner, roundVictor, activePlayer;
	private Cards winCard;
	private ArrayList<Player> players, endGameArray;
	private ArrayList<Cards> commonPile;
	private Scanner s;

	// Constructor
	public GamePlay() {
		round = 1;
		commonPile = new ArrayList<Cards>();
		players = new ArrayList<Player>();
		s = new Scanner(System.in);
	}

	// playGame Method runs the whole game
	public void playGame() throws SQLException {
		// Needs a while(userWantsToQuit = false) loop to go back to the beginning
		// after a game is completed.
		gameOrStats();
		createPlayers(askForAIPlayers());
		dealCards();
		playRounds();
		endOfGame();
		playGame();
	}
	

	// Log Check method
	public void logCheck() {
		if (TopTrumpsCLIApplication.writingTestLogs == true) {
			logCheck = true;
		}
	}

	// Game Selection and Statistics Methods:

	// Lets the player choose whether to print statistics or play the game
	public void gameOrStats() throws SQLException {
		int res = 0;
		while (res != 1 && res != 2) {
			System.out.print("Do you want to see past results or play a game?\n" + "   1: Print Game Statistics\n"
					+ "   2: Play game\n" + "Enter the number for your selection: ");
			res = s.nextInt();
		}
		if (res == 1) {
			printStats();
		}
	}

	// Prints statistics queries from the database
	public void printStats() throws SQLException {

		TopTrumpsDatabase stats = new TopTrumpsDatabase();

		stats.getAI1Wins();
		stats.getAI2Wins();
		stats.getAI3Wins();
		stats.getAI4Wins();
		stats.getHumanWins();
		stats.getNumberOfDraws();
		stats.getNumberOfRoundsPlayedInGame();

	}

	// Inserts game data into the database
	public void insertDatabase() {

		TopTrumpsDatabase stats = new TopTrumpsDatabase();
		// Currently only works with 4 Players, AutoIncrement for GameID is not
		// implemented
		stats.insertDatabase(drawCounter, gameWinner.toString(), round, user.getWinCounter(), player1.getWinCounter(),
				player2.getWinCounter(), player3.getWinCounter(), player4.getWinCounter());

	}

	// Player Creation Methods:

	// Asks User for Number of AI Players
	public int askForAIPlayers() {
		int numAI = 0;
		while (numAI != 1 && numAI != 2 && numAI != 3 && numAI != 4) {
			System.out.println("How many players do you want to play against? Choose between 1 and 4: ");
			numAI = s.nextInt();
		}
		return numAI;
	}

//	Creates an array to be used for the scores at the end of the game
	@SuppressWarnings("unchecked")
	public void endGameArray() {
		endGameArray = (ArrayList<Player>) players.clone();
	}

	// Creates the User player and the appropriate number of AI players based on
	// user choice, and adds them to the player's array
	public void createPlayers(int num) {

		// Creates User Player
		user = new Player("You", new ArrayList<Cards>());
		players.add(user);

		// Creates AI Players after User has decided how many AI to play against
		int numPlayers = num;

		if (numPlayers == 1) {
			player1 = new Player("AI Player 1", new ArrayList<Cards>());
			players.add(player1);
		} else if (numPlayers == 2) {
			player1 = new Player("AI Player 1", new ArrayList<Cards>());
			player2 = new Player("AI Player 2", new ArrayList<Cards>());
			players.add(player1);
			players.add(player2);

		} else if (numPlayers == 3) {
			player1 = new Player("AI Player 1", new ArrayList<Cards>());
			player2 = new Player("AI Player 2", new ArrayList<Cards>());
			player3 = new Player("AI Player 3", new ArrayList<Cards>());
			players.add(player1);
			players.add(player2);
			players.add(player3);
		} else if (numPlayers == 4) { // Should here be really else, what if the user puts in 5 or a negative Number?
			player1 = new Player("AI Player 1", new ArrayList<Cards>());
			player2 = new Player("AI Player 2", new ArrayList<Cards>());
			player3 = new Player("AI Player 3", new ArrayList<Cards>());
			player4 = new Player("AI Player 4", new ArrayList<Cards>());
			players.add(player1);
			players.add(player2);
			players.add(player3);
			players.add(player4);
		}
		// endGameArray is created here along with the players array (is a clone of the
		// players array)
		endGameArray(); // Not 100% sure it's doing anything
	}

	// Dealing Card Methods:

	// Deals the cards
	public void dealCards() {

		int cardsPerPlayer;
		ArrayList<Cards> deckList;
		CardDeck mainDeck = new CardDeck(new ArrayList<Cards>());

		// TestLog code
		mainDeck.initializeDeck();
		if (logCheck == true) {
			// TopTrumpsCLIApplication.testlog2.fine(
			TopTrumpsCLIApplication.testlog2
					.fine("The contents of the complete deck once it has been read in and constructed: ");
			deckList = mainDeck.getDeck();
			deckList.forEach(Card -> TopTrumpsCLIApplication.testlog2.fine(Card.toString()));
		}

		// Shuffle deck here after initialization
		Collections.shuffle(mainDeck.getDeck());
		if (logCheck == true) {
			TopTrumpsCLIApplication.testlog2.fine("The contents of the complete deck after it has been shuffled: ");
			deckList = mainDeck.getDeck();
			deckList.forEach(Card -> TopTrumpsCLIApplication.testlog2.fine(Card.toString()));
		}

		cardsPerPlayer = mainDeck.getDeck().size() / players.size();

		for (Player player : players) {
			mainDeck.dealCards(cardsPerPlayer, player);
		}
		// Logging of Player Decks
		if (logCheck == true) {
			TopTrumpsCLIApplication.testlog2
					.fine("The content s of the users deck and the computers deck(s) once they have been allocated: ");

			for (Player player : players) {
				TopTrumpsCLIApplication.testlog2.fine(player.getPlayerID());
				player.getHand().forEach(Card -> TopTrumpsCLIApplication.testlog2.fine(Card.toString()));
			}

		}
		// Shows each player's hand and how many cards they have at the start of the
		// game after dealing
		checkPlayerDeck();
	}

	// Checks for player's deck (round)
	public void checkPlayerDeck() {
		for (Player player : players) {
			for (Cards card : player.getHand()) {
				System.out.println(card.toString());
			}
			System.out.println(player.getHand().size());
		}
	}

	// Play a Round methods:

//	UserInputHandler -  Handles user input when they are the selected player,
//	and has AI players automatically choose the highest category on their card
	public void activeUserInputHandler(Player activePlayer, Player user) {
		String chosenCategory = null;
		int playerChoice;

		if (activePlayer.equals(user)) {
			System.out.println("It is your turn to select a category, the categories are: ");
			System.out.println("1: size");
			System.out.println("2: speed");
			System.out.println("3: cargo");
			System.out.println("4: range");
			System.out.println("5: firepower");
			do {
				System.out.println("Enter the number for your attribute: ");
				playerChoice = s.nextInt();
				if (TopTrumpsCLIApplication.writingTestLogs == true) {
					TopTrumpsCLIApplication.testlog2
							.fine("The category selected when a user or computer selects a category:");
					if (playerChoice == 1) {
						chosenCategory = " size ";
					} else if (playerChoice == 2) {
						chosenCategory = " speed ";
					} else if (playerChoice == 3) {
						chosenCategory = " cargo ";
					} else if (playerChoice == 4) {
						chosenCategory = " range ";
					} else if (playerChoice == 5) {
						chosenCategory = " firepower ";
					}

					TopTrumpsCLIApplication.testlog2.fine("Category Selected: " + chosenCategory);
					for (Player player : players) {
						TopTrumpsCLIApplication.testlog2.fine("Round" + round + "Player corresponding value of player"
								+ player.getPlayerID() + " " + player.getTopCard().toString());
					}

				}
			} while (playerChoice != 1 && playerChoice != 2 && playerChoice != 3 && playerChoice != 4
					&& playerChoice != 5);
			choice = playerChoice - 1;
			// c.close();
//			Handles AI choice if they are the selected player
		} else {
			choice = activePlayer.getTopCard().getMax();
//		//In Gameplay when Category is selected, this is added, and also based on the category the value needs to be represented
			if (TopTrumpsCLIApplication.writingTestLogs == true) {
				TopTrumpsCLIApplication.testlog2
						.fine("The category selected when a user or computer selects a category:");
				if (choice == 1) {
					chosenCategory = " size ";
				} else if (choice == 2) {
					chosenCategory = " speed ";
				} else if (choice == 3) {
					chosenCategory = " cargo ";
				} else if (choice == 4) {
					chosenCategory = " range ";
				} else if (choice == 5) {
					chosenCategory = " firepower ";
				}

				TopTrumpsCLIApplication.testlog2.fine("Category Selected: " + chosenCategory);
				for (Player player : players) {
					TopTrumpsCLIApplication.testlog2.fine("Round" + round + "Player corresponding value of player"
							+ player.getPlayerID() + " " + player.getTopCard().toString());
				}

			}
		}
	}

//	If the user is still in the game, displays his top card and remaining number of cards of their deck
	public void displayUserHand(Player player, Player user) {
		if (player.equals(user)) {
			System.out.println("You drew \'" + user.getTopCard().getDescription() + "\':");

			for (int i = 0; i < user.getTopCard().attributeNames().size(); i++) {
				System.out.println("> " + user.getTopCard().attributeNames().get(i) + ": "
						+ user.getTopCard().attributeArray().get(i));
			}
			System.out.println("There are " + user.getHand().size() + " cards in your deck");
		}
	}

//	Randomly selects a player
	public Player randomSelectPlayer() {

		Random rand = new Random();
		int randomPlayerIndex = rand.nextInt(players.size() - 1);

		Player selectedPlayer = players.get(randomPlayerIndex);
		return selectedPlayer;
	}

//  Assigns randomSelectPlayer result to activePlayer variable 	
	public void decideFirstTurn() {
		activePlayer = randomSelectPlayer();
	}

// Finds the Round Winner
	public void findRoundWinner() {
		roundVictor = players.get(0);
		for (Player player : players) {
			if (player.getTopCard().attributeArray().get(choice) >= roundVictor.getTopCard().attributeArray()
					.get(choice)) {
				roundVictor = player;
				winCard = roundVictor.getTopCard();
			}
		}

		// In Gameplay at the end of each round
		if (logCheck == true) {
			TopTrumpsCLIApplication.testlog2
					.fine("The content s of the users deck and the computers deck(s) once they have been allocated: ");
			for (Player player : players) {
				TopTrumpsCLIApplication.testlog2.fine(player.getPlayerID());
				player.getHand().forEach(Card -> TopTrumpsCLIApplication.testlog2.fine(Card.toString()));
			}
		}
	}

// Prints the winning card
	public void printWinCard(Cards winCard, int choice) {
		System.out.println("The winning card was \'" + winCard.getDescription() + "\' :");
		for (int i = 0; i < winCard.attributeNames().size(); i++) {
			if (i == choice) {
				System.out.println(
						"> " + winCard.attributeNames().get(i) + ": " + winCard.attributeArray().get(i) + " <--");
			} else {
				System.out.println("> " + winCard.attributeNames().get(i) + ": " + winCard.attributeArray().get(i));
			}
		}
	}

//	Checks for draw
	public void drawChecker() {
		draw = false;
		for (Player player : players) {
			if (!player.equals(roundVictor)) {
				if (player.getTopCard().attributeArray().get(choice) == winCard.attributeArray().get(choice)) {
					draw = true;
				}
			}
		}
		if (draw == false) {
			// System.out.println("It was a win");
			victoryHandler();

			roundVictor.incrementCounter(); // Taken from end of VictoryCounter
		} else {
			System.out.println("It was a draw");
			drawHandler();
		}
	}

// Handles draws when drawChecker finds the round is a draw
	public void drawHandler() {
		for (Player player : players) {
			commonPile.add(player.getTopCard());
			player.getHand().remove(0);
		}
		draw = false;
		System.out.println(
				"Round " + round + ": This round was a draw, common pile nows has " + commonPile.size() + " cards");

		// TestLog stuff for the method below

		if (logCheck == true) {
			TopTrumpsCLIApplication.testlog2
					.fine("The contents of the communal pile when cards are added or removed from it: ");
			commonPile.forEach(Card -> TopTrumpsCLIApplication.testlog2.fine(Card.toString()));
		}
	}

// 	Handles victories when drawChecker finds the round is not a draw
	public void victoryHandler() {
		System.out.println("Round " + round + ": Player " + roundVictor.toString() + " won this round");
		printWinCard(winCard, choice);

		for (Cards card : commonPile) {
			roundVictor.getHand().add(card);
		}
		int commonPileSize = commonPile.size();
		for (int i = 0; i < commonPileSize; i++) {
			commonPile.remove(0);
		}

		// TestLog code below

		// In Gameplay at the position when cards are compared - getCardsInPlay method
		// needed
		if (TopTrumpsCLIApplication.writingTestLogs == true) {
			TopTrumpsCLIApplication.testlog2.fine(
					"The contents of the current cards in play (the cards from the top of the users deck and the computers deck(s)):");
			for (Player player : players) {
				TopTrumpsCLIApplication.testlog2.fine(player.getHand().toString());
			}
		}

		for (Player player : players) {
			if (!player.equals(roundVictor)) {
				roundVictor.getHand().add(player.getTopCard());
				player.getHand().remove(0);
			}
		}
		activePlayer = roundVictor; // makes the game acknowledge when a round has a victory
	}

//		Removes any players with no cards left
	public void removeLosers() {
		ArrayList<Player> losers = new ArrayList<Player>();
		for (Player player : players) {
			if (player.getHand().size() == 0)
				losers.add(player);
		}

		for (Player player : losers) {
			players.remove(player);
		}
		losers.clear();
	}

	// Increments the round number and updates players' hands
	public int nextRound() {
		round++;
		for (Player player : players) {
			// Hinzufügen, dass aktuelle Karte nach hinten gepackt wird.
			Cards tempCard = player.getTopCard();
			player.getHand().remove(0);
			player.getHand().add(tempCard);
		}
		return round;
	}

	// Plays a Round
	public void playRounds() throws SQLException {

		decideFirstTurn();
		while (players.size() > 1) {
			System.out.println("\n" + "Round " + round + "\n" + "Round " + round + ": Players have drawn their cards");
			displayUserHand(players.get(0), user);
			activeUserInputHandler(activePlayer, user);
			findRoundWinner();
			drawChecker();
			removeLosers();
			nextRound(); // end of loop

			// By uncommenting this you can directly see how much every player has each
			// round
//				for (Player player : players) {
//					System.out.println(player.getPlayerID() + " " + player.getHand().size());
//				}
		}
	}

	// End of Game Methods:

//	Handles game end
	public void gameEndHandler() {
		round--;
		// Why players.get(0) this is hard coded, wouldn't lead that to always the same
		// winner
		gameWinner = players.get(0);
		System.out.println("Game End");
		System.out.println("The overall winner was " + gameWinner.toString());
		System.out.println("Scores:");

		for (Player player : endGameArray) {
			System.out.println(player.toString() + ": " + player.getWinCounter());
		}
	}

// Ends the game, inserts data from the game into the database, and closes the Scanner	
	public void endOfGame() {
		gameEndHandler();
		// insertDatabase(); // Uncomment this when database is connected
		//s.close(); needs to be moved
	}
}
