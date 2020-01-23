package Sprint1;

public class PlayerTest {
	
	//Create Players method
	public void createPlayers() {
		
		//Array for players in the game
		Player[] playerList = new Player[/*I think I need a getter for the number of players selected 
										by the user with a scanner to get the right array size*/];
		
		//Human player
		Player user = new Player ("Mr. Meows");
		//AI players
		Player ai1 = new Player ("AI 1");
		Player ai2 = new Player ("AI 2");
		Player ai3 = new Player ("AI 3");
		Player ai4 = new Player ("AI 4");
		//Create Players method
		playerList[1] = ai1;
		playerList[2] = ai2;
		playerList[3] = ai3;
		playerList[4] = ai4;
		
		//This can definitely be tidied up with a for loop and an array for the AIs.
	}

}
