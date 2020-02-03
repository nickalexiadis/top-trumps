package commandline;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;

import commandline.TopTrumpsCLIApplication;

public class GamePlay {

	// Declare Variables
	public static int round = 1;
	public static int choice = 0;
	public static boolean draw = false;
	public static Cards winCard = null;
	public static ArrayList<Cards> commonPile = new ArrayList<Cards>();
	public static ArrayList<Cards> deckList = null;
	public static ArrayList<Cards> userList, p1List, p2List, p3List, p4List;
	public static int drawCounter = 0;
	public static Player gameWinner;
	public static ArrayList<Player> losers = new ArrayList<Player>();
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static ArrayList<Player> endgameArray;
	public static Player roundVictor = null;
	public static Player activePlayer = null;
	public static int randomPlayerIndex = 0;
	public static Player user = null;
	public static int numPlayers = 0;
	public static Random rand = null;
	public static Player player1, player2, player3, player4;
	public static int mode;
	public static boolean gameOn;
	public static Scanner s = null;
	public static Scanner c = null;
	public static CardDeck mainDeck = new CardDeck(new ArrayList<Cards>());
	public static int cardsPerPlayer = 0;
	public static int playerChoice = 0;
	public static boolean logCheck = false;
	public static String chosenCategory ="";

	public static void main(String[] args) throws SecurityException, IOException, SQLException {

		if (TopTrumpsCLIApplication.writingTestLogs == true) {
			logCheck = true;
		}
		mode = gameOrStats();
		
///////////////added by Miruna///////////////////////////////////
		TopTrumpsDatabase stats = new TopTrumpsDatabase();
		

		if (mode == 1) {
			
			
			//needs work on getNumberOfGames, getWinner and getNumberOfRounds
		//	 stats.updateDatabase(numberOfGames, winner, getDrawCounter(), numberOfRounds);
			 
			 stats.getNumberOfGames();
			 stats.getComputerWins();
			 stats.getHumanWins();
			 stats.getNumberOfDraws();
			 stats.getNumberOfRoundsPlayedInGame();
			 
		}
////////////////////////////////////////////////////////////////
		gameOn = true;
		while (gameOn) {
			//Initialize variables
			round = 1;
			choice = 0;
			draw = false;
			Cards winCard = null;
			ArrayList<Cards> commonPile = null;
			ArrayList<Cards> deckList = null;
			ArrayList<Cards> userList, p1List, p2List, p3List, p4List;
			losers = new ArrayList<Player>();
			players = new ArrayList<Player>();
			endgameArray = null;
			roundVictor = null;
			activePlayer = null;
			user = null;
			numPlayers = 0;
			rand = null;
			player1=null;
			player2=null; 
			player3=null;
			player4=null;
			mainDeck = new CardDeck(new ArrayList<Cards>());
			cardsPerPlayer = 0;
			playerChoice = 0;
			logCheck = false;
			chosenCategory ="";
			//////////////////////////////////////////////////////////////////
//			While the players hasn't chosen number of AI players, asks for input
			numPlayers = AskForAIPlayers();
			
			
//			Creates the ArrayList of players, initializes the user player and adds them to the array
			user = new Player("You", new ArrayList<Cards>());
			players.add(user);

//			Creates the appropriate number of AI players based on user choice and adds them to the players array
			createAIPlayers(numPlayers);

//			Creates an array to be used for the scores at the end of the game
			endgameArray = (ArrayList<Player>) players.clone();

//			Randomly selects the player who will decide the first category
			activePlayer = randomSelectPlayer();

			System.out.println("Game Start");

//			Initialize deck
			mainDeck.initializeDeck();
			if (logCheck == true) {
				//TopTrumpsCLIApplication.testlog2.fine(
				TopTrumpsCLIApplication.testlog2.fine(
						"The contents of the complete deck once it has been read in and constructed: ");
				deckList = mainDeck.getDeck();
				deckList.forEach(Card -> TopTrumpsCLIApplication.testlog2.fine( Card.toString()));
			}

			// shuffle deck here after initialization
			Collections.shuffle(mainDeck.getDeck());
			if (logCheck == true) {
				TopTrumpsCLIApplication.testlog2.fine(
						"The contents of the complete deck after it has been shuffled: ");
				deckList = mainDeck.getDeck();
				deckList.forEach(Card -> TopTrumpsCLIApplication.testlog2.fine( Card.toString()));
			}

//			Deal cards to players - Needs work! - What is if this division does not work?
			cardsPerPlayer = mainDeck.getDeck().size() / players.size();

			for (Player player : players) {
				mainDeck.dealCards(cardsPerPlayer, player);
			}
			// Logging of Player Decks
			if (logCheck == true) {
				TopTrumpsCLIApplication.testlog2.fine(
						"The content s of the users deck and the computers deck(s) once they have been allocated: ");

				for (Player player : players) {
					TopTrumpsCLIApplication.testlog2.fine( player.getPlayerID());
					player.getHand().forEach(Card -> TopTrumpsCLIApplication.testlog2.fine( Card.toString()));
				}

			}

			// check for player's deck
			checkPlayerDeck();

//			The main game loop. The game will continue as long as there is one player left with cards
			while (players.size() > 1) {

				System.out.println("\n" + "Round " + round);
				
// By uncommenting this you can directly see how much every player has each round				
//				for (Player player : players) {
//					System.out.println(player.getPlayerID() + " " + player.getHand().size());
//				}
////////////////////////////////////////////////////////////////////////////////////////////////
				
				System.out.println("Round " + round + ": Players have drawn their cards");

//				If the user is still in the game, displays his top card and remaining number of cards of their deck
				displayUserHand(players.get(0), user);

//					Handles user input when they are the selected player
				activeUserInputHandler(activePlayer, user);

//					Determines round winner
				findRoundWinner();
//Checks for draw
				drawChecker();
// Remove Losers from Game
				removeLosers();
//Increments round
				round++;
				for (Player player : players) {
					//Hinzufügen, dass aktuelle Karte nach hinten gepackt wird.
					Cards tempCard = player.getHand().get(0);
					player.getHand().remove(0);
					player.getHand().add(tempCard);
					//Collections.shuffle(player.getHand());
				}
			}
// If there is only one 1 Player left we come to this point
			gameEndHandler();
			mode = gameOrStats();
		}
	}

// Method declaration part///////////////////////////////////
//	Handles user input when they are the selected player
	public static void activeUserInputHandler(Player activePlayer, Player user) {
		if (activePlayer.equals(user)) {
			System.out.println("It is your turn to select a category, the categories are: ");
			System.out.println("1: size");
			System.out.println("2: speed");
			System.out.println("3: cargo");
			System.out.println("4: range");
			System.out.println("5: firepower");
			c = new Scanner(System.in);
			do {
				System.out.println("Enter the number for your attribute: ");
				playerChoice = c.nextInt();
				if (TopTrumpsCLIApplication.writingTestLogs == true) {
					TopTrumpsCLIApplication.testlog2.fine(
							"The category selected when a user or computer selects a category:");
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
					TopTrumpsCLIApplication.testlog2.fine( "Category Selected: " + chosenCategory);
					for (Player player : players) {
						TopTrumpsCLIApplication.testlog2.fine(
								"Round" + round + "Player corresponding value of player" + player.getPlayerID() + " "
										+ player.getHand().get(0).toString());
					}

				}
			} while (playerChoice != 1 && playerChoice != 2 && playerChoice != 3 && playerChoice != 4
					&& playerChoice != 5);
			choice = playerChoice - 1;
//			Handles AI choice if they are the selected player
		} else {
			choice = activePlayer.getHand().get(0).getMax();
//		//In Gameplay when Category is selected, this is added, and also based on the category the value needs to be represented
			if (TopTrumpsCLIApplication.writingTestLogs == true) {
				TopTrumpsCLIApplication.testlog2.fine(
						"The category selected when a user or computer selects a category:");
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
				TopTrumpsCLIApplication.testlog2.fine( "Category Selected: " + chosenCategory);
				for (Player player : players) {
					TopTrumpsCLIApplication.testlog2.fine(
							"Round" + round + "Player corresponding value of player" + player.getPlayerID() + " "
									+ player.getHand().get(0).toString());
				}
			}
		}
	}

//	If the user is still in the game, displays his top card and remaining number of cards of their deck
	public static void displayUserHand(Player player, Player user) {
		if (player.equals(user)) {
			System.out.println("You drew \'" + user.getHand().get(0).getDescription() + "\':");

			for (int i = 0; i < user.getHand().get(0).attributeNames().size(); i++) {
				System.out.println("> " + user.getHand().get(0).attributeNames().get(i) + ": "
						+ user.getHand().get(0).attributeArray().get(i));
			}
			System.out.println("There are " + user.getHand().size() + " cards in your deck");
		}
	}

	// check for player's deck
	public static void checkPlayerDeck() {
		for (Player player : players) {
			for (Cards card : player.getHand()) {
				System.out.println(card.toString());
			}
			System.out.println(player.getHand().size());
		}
	}

//	Randomly selects the player who will decide the first category
	public static Player randomSelectPlayer() {
		rand = new Random();
		randomPlayerIndex = rand.nextInt(players.size() - 1);
		activePlayer = players.get(randomPlayerIndex);
		return activePlayer;
	}

//	Creates the appropriate number of AI players based on user choice and adds them to the players array
	public static void createAIPlayers(int numPlayers) {
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
	}

//	While the players hasn't chosen number of AI players, asks for input
	public static int AskForAIPlayers() {
		numPlayers = 0;
		s = new Scanner(System.in);
		while (numPlayers != 1 && numPlayers != 2 && numPlayers != 3 && numPlayers != 4) {
			System.out.println("How many players do you want to play against? Choose between 1 and 4: ");
			numPlayers = s.nextInt();
		}
		return numPlayers;
	}

//Find Round Winner
	public static void findRoundWinner() {
		roundVictor = players.get(0);
		for (Player player : players) {
			if (player.getHand().get(0).attributeArray().get(choice) >= roundVictor.getHand().get(0).attributeArray()
					.get(choice)) {
				roundVictor = player;
				winCard = roundVictor.getHand().get(0);
			}
		}
//		//In Gameplay at the end of each round
		if (logCheck == true) {
			TopTrumpsCLIApplication.testlog2.fine(
					"The content s of the users deck and the computers deck(s) once they have been allocated: ");
			for (Player player : players) {
				TopTrumpsCLIApplication.testlog2.fine( player.getPlayerID());
				player.getHand().forEach(Card -> TopTrumpsCLIApplication.testlog2.fine( Card.toString()));
			}
		}
	}

//	Checks for draw
	public static void drawChecker() {
		draw = false;
		for (Player player : players) {
			if (!player.equals(roundVictor)) {
				if (player.getHand().get(0).attributeArray().get(choice) == winCard.attributeArray().get(choice)) {
					draw = true;
					}
			}
		}
		if (draw == false) {
			//System.out.println("It was a win");
			victoryHandler();
		}
		if (draw == true) {
		//System.out.println("It was a draw");
		drawHandler();
		drawCounter++;
		}

	}
	//getter for draw counter
	public static int getDrawCounter() {
		return drawCounter++;
	}

	// DrawHandler
	public static void drawHandler() {
		// Why is the 5 hard coded? This is wrong. It has to get the size of the common
		// pile and use that number

		for (Player player : players) {
			commonPile.add(player.getHand().get(0));
			player.getHand().remove(0);
		}
		draw = false;
		System.out.println(
				"Round " + round + ": This round was a draw, common pile nows has " + commonPile.size() + " cards");
//		//In CardDecks in the Add/Remove CommonPileSection at the end
		if (logCheck == true) {
			TopTrumpsCLIApplication.testlog2.fine(
					"The contents of the communal pile when cards are added or removed from it: ");
			commonPile.forEach(Card -> TopTrumpsCLIApplication.testlog2.fine( Card.toString()));
		}
	}

	// VictoryHandler
	public static void victoryHandler() {
		System.out.println("Round " + round + ": Player " + roundVictor.toString() + " won this round");
		printWinCard(winCard, choice);

		for (Cards card : commonPile) {
			roundVictor.getHand().add(card);
		}
		int commonPileSize = commonPile.size();
		for (int i = 0; i < commonPileSize; i++) {
			commonPile.remove(0);
		}

//		//In Gameplay at the position when cards are compared - getCardsInPlay method needed
		if (TopTrumpsCLIApplication.writingTestLogs == true) {
			TopTrumpsCLIApplication.testlog2.fine(
					"The contents of the current cards in play (the cards from the top of the users deck and the computers deck(s)):");
			for (Player player : players) {
				TopTrumpsCLIApplication.testlog2.fine( player.getHand().toString());
			}
		}
		for (Player player : players) {
			if (!player.equals(roundVictor)) {
				roundVictor.getHand().add(player.getHand().get(0));
				player.getHand().remove(0);
			}
		}
		activePlayer = roundVictor;
		activePlayer.incrementCounter();
	}

//	Removes any players with no cards left
	public static void removeLosers() {
		for (Player player : players) {
			if (player.getHand().size() == 0)
				losers.add(player);
		}

		for (Player player : losers) {
			players.remove(player);
		}
		losers.clear();
	}

/////////////////////////////////

//	Handles game end
	public static void gameEndHandler() {
		round--;
		// Why players.get(0) this is hard coded, wouldn't lead that to always the same
		// winner
		gameWinner = players.get(0);
		System.out.println("Game End");
		System.out.println("The overall winner was " + gameWinner.toString());
		System.out.println("Scores:");

		for (Player player : endgameArray) {
			System.out.println(player.toString() + ": " + player.getWinCounter());
		}
	}

	public static int gameOrStats() {
		Scanner sc = new Scanner(System.in);
		int res = 0;
		while (res != 1 && res != 2) {
			System.out.print("Do you want to see past results or play a game?\n" + "   1: Print Game Statistics\n"
					+ "   2: Play game\n" + "Enter the number for your selection: ");
			res = sc.nextInt();
		}

		return res;
	}

	public static void printWinCard(Cards winCard, int choice) {
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

}