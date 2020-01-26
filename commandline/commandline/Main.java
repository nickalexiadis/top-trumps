package commandline;
import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
//		
		int mode = gameOrStats();
		
		if(mode == 1) {
//			Stats function!
		}
		
		boolean gameOn = true;
		while(gameOn) {
//			While the players hasn't chosen number of AI players, asks for input
			int numPlayers = 0;
			Scanner s = new Scanner(System.in);
			while (numPlayers != 1 && numPlayers != 2 && numPlayers != 3 && numPlayers != 4) {
				System.out.println("How many players do you want to play against? Choose between 1 and 4: ");
				numPlayers = s.nextInt();
			}
			
//			Creates the ArrayList of players, initializes the user player and adds them to the array
			ArrayList<Player> players = new ArrayList<Player>();
			Player user = new Player("You", new ArrayList<Cards>());
			players.add(user);
			
//			Creates the appropriate number of AI players based on user choice and adds them to the players array
			
			if(numPlayers == 1) {
				Player player1 = new Player("AI Player 1", new ArrayList<Cards>());
				players.add(player1);
			}
			else if (numPlayers == 2) {
				Player player1 = new Player("AI Player 1", new ArrayList<Cards>());
				Player player2 = new Player("AI Player 2", new ArrayList<Cards>());
				players.add(player1);
				players.add(player2);
				
			}
			else if (numPlayers == 3) {
				Player player1 = new Player("AI Player 1", new ArrayList<Cards>());
				Player player2 = new Player("AI Player 2", new ArrayList<Cards>());
				Player player3 = new Player("AI Player 3", new ArrayList<Cards>());
				players.add(player1);
				players.add(player2);
				players.add(player3);
			}
			else {
				Player player1 = new Player("AI Player 1", new ArrayList<Cards>());
				Player player2 = new Player("AI Player 2", new ArrayList<Cards>());
				Player player3 = new Player("AI Player 3", new ArrayList<Cards>());
				Player player4 = new Player("AI Player 4", new ArrayList<Cards>());
				players.add(player1);
				players.add(player2);
				players.add(player3);
				players.add(player4);	
//			}
			
//			Creates an array to be used for the scores at the end of the game
			ArrayList<Player> endgameArray = (ArrayList<Player>) players.clone();
			
//			Randomly selects the player who will decide the first category
			Random rand = new Random();
			int randomPlayerIndex = rand.nextInt(players.size() - 1);
			Player activePlayer = players.get(randomPlayerIndex);
			
//			Main Gameplay -TBC
//			Variable declarations
			int round = 1;
			int choice = 0;
			boolean draw = false;
			Cards winCard = null;
			ArrayList<Cards> commonPile = new ArrayList<Cards>();
			int drawCounter = 0;
			Player gameWinner;
			
			System.out.println("Game Start");
			
//			Initialize deck
			CardDeck mainDeck = new CardDeck(new ArrayList<Cards>());
			

			mainDeck.initializeDeck();

			
//			Deal cards to players - Needs work!
			int cardsPerPlayer = mainDeck.getDeck().size() / players.size();
			
			for (Player player : players) {
				mainDeck.dealCards(cardsPerPlayer, player);
			}
			
			
			
//			The main game loop. The game will continue as long as there is one player left with cards
			while (players.size() > 0) {
		
				System.out.println("Round " + round);
				System.out.println("Round " + round + ": Players have drawn their cards");
				
//				If the player hasn't lost, displays his top card and remaining number of cards of their deck
				if(players.get(0).equals(user)) {
					System.out.println("You drew \'" + user.getHand().get(0).getDescription() + "\':");
					for(int i =0; i < user.getHand().get(0).attributeNames().size(); i++) {
						System.out.println("> " + user.getHand().get(0).attributeNames().get(i) + ": " + winCard.attributeArray().get(i));
					}
					System.out.println("There are " + user.getHand().size() + " cards in your deck");
				}
				
//					Handles user input when they are the selected player
				if(activePlayer.equals(user)) {
					System.out.println("It is your turn to select a category, the categories are: ");
					System.out.println("1: size");
					System.out.println("2: speed");
					System.out.println("3: cargo");
					System.out.println("4: range");
					System.out.println("5: firepower");
					
					int playerChoice;
					Scanner c = new Scanner(System.in);
					do {
						System.out.println("Enter the number for your attribute: ");
						playerChoice = c.nextInt();
					} while (playerChoice != 1 && playerChoice != 2 && playerChoice != 3 && playerChoice != 4 && playerChoice != 5);
					choice = playerChoice - 1;
//						Handles AI choice if they are the selected player
				} else {
					choice = activePlayer.getHand().get(0).getMax();
				}
				
				
//					Determines round winner
				Player roundVictor = players.get(0);
				for(Player player : players) {
					if(player.getHand().get(0).attributeArray().get(choice) > roundVictor.getHand().get(0).attributeArray().get(choice)) {
						roundVictor = player;
						winCard = roundVictor.getHand().get(0);
					}
				}
				
//					Checks for draw
				for(Player player : players) {
					if(!player.equals(roundVictor)) {
						if(player.getHand().get(0).attributeArray().get(choice) == winCard.attributeArray().get(choice)) {
							draw= true;
						}
					}
				}
				
//					Handles draw condition
				if(draw) {
					drawCounter++;
					System.out.println("Round " + round + ": This round was a draw, common pile nows has 5 cards");	
					printWinCard(winCard, choice);
					
					for(Player player : players) {
						commonPile.add(player.getHand().get(0));
						player.getHand().remove(0);
					}	
				} 
		
//					Handles victory condition
				else {
					System.out.println("Round " + round + ": Player " + roundVictor.toString() + " won this round");	
					printWinCard(winCard, choice);
					
					for (Cards card : commonPile) {
						roundVictor.getHand().add(card);
					}
					
					for(Player player : players) {
						if(!player.equals(roundVictor)) {
							roundVictor.getHand().add(player.getHand().get(0));
							player.getHand().remove(0);
						}
					}
					activePlayer = roundVictor;
					activePlayer.incrementCounter();
				}
			
				
//					Removes any players with no cards left
				for (Player player: players) {
					if(player.getHand().size() == 0) players.remove(player);
				}
				
//				Increments round
				round++;
						
			}
			
			
//			Handles game end
			round--;
			gameWinner = players.get(0);
			System.out.println("Game End");
			System.out.println("The overall winner was " + gameWinner.toString());
			System.out.println("Scores:");
			
			for(Player player : endgameArray) {
				System.out.println(player.toString() + ": " + player.getWinCounter());
			}	
		}	
			
		mode = gameOrStats();
		
	}				
}	

	public static int gameOrStats() {
		Scanner sc = new Scanner(System.in);	
		int res = 0;
		while(res != 1 && res != 2) {
		System.out.print("Do you want to see past results or play a game?\n" + 
				"   1: Print Game Statistics\n" + 
				"   2: Play game\n" + 
				"Enter the number for your selection: ");
		 res = sc.nextInt();
		}
		
		return res;
	}
	
	
	public static void printWinCard(Cards winCard, int choice) {
		System.out.println("The winning card was \'" + winCard.getDescription() + "\' :");
		for(int i =0; i < winCard.attributeNames().size(); i++) {
			if(i == winCard.attributeArray().get(choice)) {
				System.out.println("> " + winCard.attributeNames().get(i) + ": " + winCard.attributeArray().get(i) + " <--");
			}
			else {
				System.out.println("> " + winCard.attributeNames().get(i) + ": " + winCard.attributeArray().get(i));
			}
		}
	}
	
}