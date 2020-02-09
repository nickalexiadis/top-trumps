package commandline;

import java.sql.SQLException;

public class RunCommandLine {
	
	public static void main(String[] args) throws SQLException {
		
		GamePlay g = new GamePlay();
		g.playGame();
	}
}
