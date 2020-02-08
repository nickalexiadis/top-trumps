package commandline;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game_Object{

private int round;
private int choice;
private boolean draw;
private Cards winCard;
private ArrayList<Cards> commonPile;
private ArrayList<Cards> deckList;
private ArrayList<Cards> userList, p1List, p2List, p3List, p4List;
private int drawCounter;
private Player gameWinner;
private ArrayList<Player> losers;
private ArrayList<Player> players;
private ArrayList<Player> endgameArray;
private Player roundVictor;
private Player activePlayer;
private int randomPlayerIndex;
private Player user;
private int numPlayers;
private Random rand;
private Player player1, player2, player3, player4;
private int mode;
private boolean gameOn;
private Scanner s;
private Scanner c;
private CardDeck mainDeck;
private int cardsPerPlayer;
private int playerChoice;
private boolean logCheck;
private String chosenCategory;


public Game_Object (int numPlayers) {
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
}
}