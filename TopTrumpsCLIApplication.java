package commandline;

import java.io.IOException;
import java.util.Scanner;
/**
 * Top Trumps command line application
 */
import java.util.logging.*; //logging class

public class TopTrumpsCLIApplication {

public static final Logger testlog = Logger.getLogger(TopTrumpsCLIApplication.class.getName());
public static boolean writingTestLogs;
//final -> so that the logger cannot be manipulated after it has been created
//TopTrumpsCLIApplication -> should only be for CLI, not online

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 * @throws IOException 
	 * @throws SecurityException 
	 */

	public static void main(String[] args) throws SecurityException, IOException {
		
		
		
		
		boolean writeGameLogsToFile = false; 			// Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) {
			writeGameLogsToFile=true; // Command line selection
			writingTestLogs = writeGameLogsToFile;
		//L's code
		//SecurityException and IOException needed for filesHandler		
				Handler filesHandler = null; //handles files
				Formatter simpleFormatter = null; //used for formatting of log file
				try { //where it could catch an exception
					filesHandler = new FileHandler("./toptrumps.log"); 
					//FileHandler object named "filesHandler", incl. location for log file (./toptrumps.log)
					//"./" means to save it where the program is
					testlog.addHandler(filesHandler); 			//log is being handed a filesHandler
					filesHandler.setLevel(Level.ALL);	 		//Security level of filesHandler is set -> every Exception is shown
					testlog.setLevel(Level.ALL); 				//Security level of testlog is set -> every Exception is shown
					simpleFormatter = new SimpleFormatter(); 	//Java Class to format logfiles
					filesHandler.setFormatter(simpleFormatter);
					//testlog.config("Testlog configured to save all log entries in toptrumps.log");
				} catch (IOException exception) {
					testlog.log(Level.SEVERE, "Error occured with the logging FileHandler", exception);
				}
		}		
		//L's code end		
				
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			System.out.println("Do you want to display statistics from previous games(1) or start a new game(2)? Choose by entering the number");
			Scanner keyboardInputScanner = new Scanner(System.in);
			String keyboardchoice = keyboardInputScanner.nextLine();
			if (keyboardchoice.equalsIgnoreCase("1")) {
				// displayGameStatistics(); // method that gets statistics from db needs to be added
			}else if (keyboardchoice.equalsIgnoreCase("2")) {
				//depending on the implementation of gameplay either newGame() method has to be called or just proceed without doing anything and the next step will be the start of the gameplay
			}else {
				//In case of neither "1" or "2" do we want to start a new game or ask again(more complicated and including a loop)
			}
						
			userWantsToQuit=true; // use this when the user wants to exit the game
		}
		
		
		//Logging code that needs to be inserted into the right gameplay code
		if (TopTrumpsCLIApplication.writingTestLogs==true) {
						TopTrumpsCLIApplication.testlog.log(Level.FINE, "The contents of the complete deck once it has been read in and constructed" + "getDeckContent");
		}
		
		if (TopTrumpsCLIApplication.writingTestLogs==true) {
			TopTrumpsCLIApplication.testlog.log(Level.FINE, "The contents of the complete deck after it has been shuffled" + "getDeckContent");
		}
		//in the Gameplay after allocation of Decks
		if (TopTrumpsCLIApplication.writingTestLogs==true) {
			TopTrumpsCLIApplication.testlog.log(Level.FINE, "The contents of the user’s deck and the computer’s deck(s) once they have been allocated." + "p1.getDeckContent" + "p2.getDeckContent"+"p3.getDeckContent" + "p4.getDeckContent");
		}
		//In CardDecks in the Add/Remove CommonPileSection at the end
		if (TopTrumpsCLIApplication.writingTestLogs==true) {
			TopTrumpsCLIApplication.testlog.log(Level.FINE, "The contents of the communal pile when cards are added or removed from it" + "getCommunalPileContent");
		}
		//In Gameplay at the position when cards are compared - getCardsInPlay method needed
		if (TopTrumpsCLIApplication.writingTestLogs==true) {
			TopTrumpsCLIApplication.testlog.log(Level.FINE, "The contents of the current cards in play (the cards from the top of the user’s deck and the computer’s deck(s)):" + "getCardsInPlay");
		}
		
		//In Gameplay when Category is selected, this is added, and also based on the category the value needs to be represented
		if (TopTrumpsCLIApplication.writingTestLogs==true) {
			TopTrumpsCLIApplication.testlog.log(Level.FINE, "The category selected and corresponding values when a user or computer selects a category" + "getCategorySelected" + "getCorrespondingValues");
		}
		//In Gameplay at the end of each round
		if (TopTrumpsCLIApplication.writingTestLogs==true) {
			TopTrumpsCLIApplication.testlog.log(Level.FINE, "The contents of each deck after a round" + "getDeckContent");
		}
		

	}

}
