import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.lang.reflect.Array;

/**
 * 
 */

/**
 * @author Hit The Code Jack
 *
 */
public class GamePlay {
	//private Player user; //a player object for the human user
	private int numOfPlayers;
	private int winCounter;
	private int drawCounter;
	private Player [] allPlayers; //an array of the players in the game
	//private Card [] communalPile;
	private CardDeck communalPile;
	private Player user;
	private Player activePlayer;
	private CardDeck playersDeck;
	
	public GamePlay() {
		this.user=user;
		this.numOfPlayers=numOfPlayers;
		this.winCounter=winCounter;
		this.drawCounter=drawCounter;
		this.allPlayers= allPlayers;
		this.activePlayer= activePlayer;
		
		
		
		
		
	Scanner sc = new Scanner(System.in);	
	System.out.print("Do you want to see past results or play a game?\n" + 
			"   1: Print Game Statistics\n" + 
			"   2: Play game\n" + 
			"Enter the number for your selection: ");
	
	
	System.out.print("Enter number of AI players: ");
	numOfPlayers=sc.nextInt();
	
	if (numOfPlayers>4) {
		System.out.print("Choose between 1 to 4 players");
	}
	else 
	{
				
	/*number of players are created based on the user input
	 * with index 0 being the user making number of players input by
	 * user increase by 1
	 * 
	*/
	numOfPlayers=numOfPlayers+1;
	allPlayers[0]= user;
	
	for (int i=1; i<numOfPlayers;i++) {
		allPlayers[i]= new Player (i, null);
	}
	
	}
	
	
	/*selecting a player at random from the players array when game starts
	 * 
	 */
	Random r= new Random();
	activePlayer= allPlayers[r.nextInt(numOfPlayers)];
	
	if (!(activePlayer==user)){
		activePlayer.getHand();
		
		
	}
}
	//if user has no card in 
	public boolean userTurn(){
		if (!(user.getHand()==null));
			return true;
	}
	
		
	
	while(userTurn) {
		user.getTopCard();
	}
		

	//this mehod should return a string of the winning card in parenthesis
	//can't seem to work
	
	
     public String toString() {
     String winCardDesc = (String)Array.get(playersDeck,0);
     System.out.print("\""+ winCardDesc+ "\" ");
     }
}
     
	
	
	
		
	
	

	


