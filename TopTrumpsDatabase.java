package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;
import com.sun.xml.internal.fastinfoset.sax.Properties;

public class TopTrumpsDatabase {
		
		//database columns, declared as variables 
		private int numberOfGames;
		private int computerWins;
		private int humanWins;
		private double numberOfDraws;
		private int numberOfRoundsPlayedInGame;
		
		//defining the attributes for the connection: URL, user and password
		//create a variable for the connection along with the user ID and password 
		    private String connectionURL = "Add connection URL";
		    private String username = "Add username";
		    private String password ="Add password";
		    
		//setup the database connection, reference: https://www.postgresqltutorial.com/postgresql-jdbc/connecting-to-postgresql-database/
		 private Connection connect() {
		        Connection connection = null;
		        try {
		            connection = DriverManager.getConnection(connectionURL, username, password);
		            System.out.println("Connected to the PostgreSQL server successfully.");
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		 
		        return connection;
		    }

		
		//closing the database connection
		public void closeConenction() {
			
		}
		
		//update the database, not final!
		 public int updateGameStats(int numberOfGames, int computerWins, int humanWins, double numberOfDraws, int numberOfRoundsPlayedInGame) {
		        String SQL = "UPDATE NUMBER_OF_GAMES, COMPUTER_WINS, HUMAN_WINS, DRAWS, ROUNDS_PLAYED";
		        int affectedrows = 0;
		        try (Connection connection = connect();
		                java.sql.PreparedStatement pstmt = connection.prepareStatement(SQL)) {
		 
		            pstmt.setInt(0, numberOfGames);
		            pstmt.setInt(0, computerWins);
		            pstmt.setInt(0, humanWins);
		            pstmt.setDouble(0, numberOfDraws);
		            pstmt.setInt(0, numberOfRoundsPlayedInGame);
		 
		            affectedrows = pstmt.executeUpdate();
		 
		        } catch (SQLException ex) {
		            System.out.println(ex.getMessage());
		        }
		        return affectedrows;
		    }
		
		//reference for the following getters: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
		//query to return the number of games played overall  
		public int getNumberOfGames() throws SQLException {
			Statement stmt = null;
			String queryOne = "select SUM(NUMBER_OF_GAMES)" +
							  "from GAME_STATS"; 
			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(queryOne);
				while(rs.next()) {
					int numberOfGames = rs.getInt("NUMBER_OF_GAMES");
					System.out.println(numberOfGames+ "\t");
				}
			} catch (SQLException e) {
				System.out.println("Failed to show number of games");	
			}finally {
				if (stmt != null) { stmt.close();
			        }
			  }
			return numberOfGames;
		 }
		
		//query to return how many times the computer has won 
		public int getComputerWins() throws SQLException {
			Statement stmt = null;
			String queryTwo = "select SUM(COMPUTER_WINS)" + 
							  "from GAME_STATS";
			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(queryTwo);
				while(rs.next()) {
					int computerWins = rs.getInt("COMPUTER_WINS");
					System.out.println(computerWins + "\t");
				}
			} catch (SQLException e) {
				System.out.println("Failed to show number of computer wins");
			}finally {
				if (stmt != null) { stmt.close();
			        }
			  }
			return computerWins;
		}
		
		//query to return how many times the human has won  
		public int getHumanWins() throws SQLException {
			Statement stmt = null;
			String queryThree = "select SUM(HUMAN_WINS)" +
								"from GAME_STATS";
			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(queryThree);
				while(rs.next()) {
					int humanWins = rs.getInt("HUMAN_WINS");
					System.out.println(humanWins + "\t");
				}
			} catch (SQLException e) {
				System.out.println("Failed to show number of human wins");
			}finally {
				if (stmt != null) { stmt.close();
			        }
			  }
			return humanWins;
		}
		
		//James, query to return the average number of draws 
		public double getNumberOfDraws() throws SQLException {
			Statement stmt = null;
			String queryFour = "select AVG(DRAWS)" + 
							   "from GAME_STATS";

			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(queryFour);
				while(rs.next()) {
					int numberOfDraws = rs.getInt("DRAWS");
					System.out.println(numberOfDraws + "\t");
				}
			} catch (SQLException e) {
				System.out.println("Failed to show number of average draws");
			}finally {
				if (stmt != null) { stmt.close();
				}
			}
			return numberOfDraws;
		}
		
		//Nana, query to return the largest number of rounds played in a single game
		public int getNumberOfRoundsPlayedInGame() throws SQLException {
			return numberOfRoundsPlayedInGame;	
		}	
}
