package commandline;

import java.io.IOException;
/**
 * Top Trumps command line application
 */
import java.util.logging.*; //logging class

public class TopTrumpsCLIApplication {

//public static final Logger testlog = Logger.getLogger(TopTrumpsCLIApplication.class.getName());
public static final Logger testlog2 = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
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
		
		TestLogger.initializeLogger();
		testlog2.setLevel(Level.ALL);
		testlog2.fine("Test1");
		testlog2.fine("Test2");
		testlog2.fine("Test3");
		testlog2.fine("Test4");
		testlog2.fine("Test5");
		testlog2.fine("Test6");
		testlog2.fine("Test7");
		
		boolean writeGameLogsToFile = false; 			// Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) {
			writeGameLogsToFile=true; // Command line selection
			writingTestLogs = writeGameLogsToFile;

		}		
			
		//L's code end		
				
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		//while (!userWantsToQuit) {
			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			GamePlay.main(args);
						
			userWantsToQuit=true; // use this when the user wants to exit the game
		}
		
	
}
