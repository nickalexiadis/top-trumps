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
		//while (!userWantsToQuit) {
			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			Main.main(args);
						
		//	userWantsToQuit=true;  use this when the user wants to exit the game
		}
		
	
}
