package online.dwResources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.CardDeck;
import commandline.Cards;
import commandline.GamePlay;
import commandline.Player;
import commandline.TopTrumpsCLIApplication;
import commandline.TopTrumpsDatabase;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	
	GamePlay g = new GamePlay();
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		
	}
	//When you press New Game, this function will be called to ask for AI players
//	@GET
//	@Path("/askPlayer")
//	public void askPlayer(int num) throws JsonProcessingException {
//		
//	}
	
		
	@GET 
	@Path("/getGame")
	public void startGame() {
		
	}
	
	@GET 
	@Path("/getStats")
	public void getStats() {
		
	}
	
	//game play methods
	
	@GET
	@Path("/getRound")
	public String getRound() throws JsonProcessingException {
		int	res = 100;
		return oWriter.writeValueAsString(res);
		
	}
	
	
//	Need to retrieve num of players from FTL. Maybe PUT?
	@GET
	@Path("/setPlayers")
	public void setPlayers() throws JsonProcessingException {
		
		
	}
	
	//Reset-Game-State
		public void resetGameState() {
			g.setRound(1);
			g.setChoice(0);
			g.setDraw(false);
			g.setDrawCounter(0);
			g.setPlayers(null);
			g.setPlayers(new ArrayList<Player>());
			g.setCommonPile(null);
			g.setCommonPile(new ArrayList<Cards>());
			g.setRoundVictor(null);
			g.setActivePlayer(null);
			g.setGameWinner(null);
			g.setWinCard(null);
			g.setUser(null);
			g.setPlayer1(null);
			g.setPlayer2(null); 
			g.setPlayer3(null);
			g.setPlayer4(null);
		}
	
//	Deal cards to players
	public void dealCards() {
		ArrayList<Cards> deckList;
		CardDeck mainDeck = new CardDeck(new ArrayList<Cards>());
		Collections.shuffle(mainDeck.getDeck());
		int cardsPerPlayer = mainDeck.getDeck().size() / g.getPlayers().size();
		for (Player player : g.getPlayers()) {
			mainDeck.dealCards(cardsPerPlayer, player);
		}
	}
	
//	Creates an array to be used for the scores at the end of the game
	@SuppressWarnings("unchecked")
	public void endGameArray() {
		ArrayList<Player> endGameArray = (ArrayList<Player>) g.getPlayers().clone();
	}	
	
	
	
//	Selects random player for beginning of game
	public Player randomSelectPlayer() {

		Random rand = new Random();
		int randomPlayerIndex = rand.nextInt(g.getPlayers().size() - 1);

		Player selectedPlayer = g.getPlayers().get(randomPlayerIndex);
		return selectedPlayer;
	}
	
//  Assigns randomSelectPlayer result to activePlayer variable 	
	public void decideFirstTurn() {
		g.setActivePlayer(randomSelectPlayer());
	}
	
//	UserInputHandler -  Handles user input when they are the selected player,
//	and has AI players automatically choose the highest category on their card
	public void activeUserInputHandler(Player activePlayer, Player user) {
		String chosenCategory = null;
		int playerChoice;

		if (activePlayer.equals(user)) {
//			Get choice from FTL
//			playerChoice = s.nextInt();
//			choice = playerChoice - 1;
		
//			Handles AI choice if they are the selected player
		} else {
			g.setChoice(activePlayer.getTopCard().getMax());			
		}
	}
	
	
// Finds the Round Winner
	public void findRoundWinner() {
		g.setRoundVictor(g.getPlayers().get(0));
		for (Player player : g.getPlayers()) {
			if (player.getTopCard().attributeArray().get(g.getChoice()) >= g.getRoundVictor().getTopCard().attributeArray()
					.get(g.getChoice())) {
				g.setRoundVictor(player);
				g.setWinCard(g.getRoundVictor().getTopCard());
			}
		}	
	}
	
//		Checks for draw
	public void drawChecker() {
		g.setDraw(false);
		for (Player player : g.getPlayers()) {
			if (!player.equals(g.getRoundVictor())) {
				if (player.getTopCard().attributeArray().get(g.getChoice()) == g.getWinCard().attributeArray().get(g.getChoice())) {
					g.setDraw(true);
				}
			}
		}
		if (g.isDraw() == false) {
			victoryHandler();
			g.getRoundVictor().incrementCounter(); // Taken from end of VictoryCounter
		} else {
			drawHandler();
		}
	}
	
	// Handles draws when drawChecker finds the round is a draw
	public void drawHandler() {
		for (Player player : g.getPlayers()) {
			g.getCommonPile().add(player.getTopCard());
			player.getHand().remove(0);
		}
		g.setDraw(false);
//		Send to FTL
//		System.out.println(
//				"Round " + round + ": This round was a draw, common pile nows has " + commonPile.size() + " cards");
	}
	
	
// 	Handles victories when drawChecker finds the round is not a draw
	public void victoryHandler() {
//		Send to FTL
//		System.out.println("Round " + round + ": Player " + roundVictor.toString() + " won this round");

		for (Cards card : g.getCommonPile()) {
			g.getRoundVictor().getHand().add(card);
		}
		int commonPileSize = g.getCommonPile().size();
		for (int i = 0; i < commonPileSize; i++) {
			g.getCommonPile().remove(0);
		}

		for (Player player : g.getPlayers()) {
			if (!player.equals(g.getRoundVictor())) {
				g.getRoundVictor().getHand().add(player.getTopCard());
				player.getHand().remove(0);
			}
		}
		g.setActivePlayer(g.getRoundVictor()); // makes the game acknowledge when a round has a victory
	}

	//	Removes any players with no cards left
	public void removeLosers() {
		ArrayList<Player> losers = new ArrayList<Player>();
		for (Player player : g.getPlayers()) {
			if (player.getHand().size() == 0)
				losers.add(player);
		}
		for (Player player : losers) {
			g.getPlayers().remove(player);
		}
		losers.clear();
	}
	
	// Increments the round number and updates players' hands
	public int nextRound() {
		g.setRound(g.getRound() + 1);
		for (Player player : g.getPlayers()) {
			Cards tempCard = player.getTopCard();
			player.getHand().remove(0);
			player.getHand().add(tempCard);
		}
		return g.getRound();
	}

	
	public void endGame() {
		if(g.getPlayers().size() <= 1) gameEndHandler();
	}
	
	public void gameEndHandler() {
		g.setRound(g.getRound() - 1);
		g.setGameWinner(g.getPlayers().get(0));
//		Send to FTL
//		System.out.println("Game End");
//		System.out.println("The overall winner was " + gameWinner.toString());
//		System.out.println("Scores:");

//		for (Player player : g.endGameArray) {
//			System.out.println(player.toString() + ": " + player.getWinCounter());
//		}
	}
	
	
	
	
	
	
	public void playRound() {
		
	}
	
	
	
	
	
	
//	Display Statistics methods
	@GET
	@Path("/numOfGames")
	public String numOfGames() throws SQLException, JsonProcessingException {
		
		TopTrumpsDatabase stats = new TopTrumpsDatabase();
		int res = stats.getNumberOfGames();		
		return oWriter.writeValueAsString(res);
	}
	
	@GET
	@Path("/humanWins")
	
	public String humanWins() throws JsonProcessingException, SQLException {
		TopTrumpsDatabase stats = new TopTrumpsDatabase();
		int res = stats.getHumanWins();		
		return oWriter.writeValueAsString(res);
	}
	
	@GET
	@Path("/AI1Wins")

	public String AIWins() throws SQLException, JsonProcessingException {
		TopTrumpsDatabase stats = new TopTrumpsDatabase();
		int res1 = stats.getAI1Wins();
		int res2 = stats.getAI2Wins();
		int res3 = stats.getAI3Wins();
		int res4 = stats.getAI4Wins();
		int res = res1 + res2 + res3 + res4;
		return oWriter.writeValueAsString(res);		
	}
	
	@GET
	@Path("/avgDraw")
	
	public String avgDraw() throws SQLException, JsonProcessingException {
		TopTrumpsDatabase stats = new TopTrumpsDatabase();
		double res = stats.getNumberOfDraws();		
		return oWriter.writeValueAsString(res);
	}
	
	@GET
	@Path("/longestGame")
	
	public String longestGame() throws SQLException, JsonProcessingException {
		TopTrumpsDatabase stats = new TopTrumpsDatabase();
		int res = stats.getNumberOfRoundsPlayedInGame();		
		return oWriter.writeValueAsString(res);
		
	}
}