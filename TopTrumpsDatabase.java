package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//This class will establish the connection between the Database and the game, it will update the stats in the database.
 
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
		 public Connection connect() {
		        Connection connection = null;
		        try {
		            connection = DriverManager.getConnection(connectionURL, username, password);
		            System.out.println("Connected to the PostgreSQL server successfully.");
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
		 
		        return connection;
		    }

		
		//closing the database connection, Question: should the system let the user know with a statement whether the connection closed or 
		//failed to close?
		public void closeConenction() {
			try {
				connect().close();	
				//System.out.println("Connection closed.");
			} catch (SQLException e) {
				e.printStackTrace();
				//System.out.println("Connection failed closing.");
			}
		}
		
		//update the database, Question: How do we actually connect the game to the database? So as to update stats with each game played?
		 public int updateGameStats(int numberOfGames, int computerWins, int humanWins, double numberOfDraws, int numberOfRoundsPlayedInGame) {
		        String SQL = "UPDATE NUMBER_OF_GAMES, COMPUTER_WINS, HUMAN_WINS, DRAWS, NUMBER_OF_ROUNDS";
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
							  "from GameStats"; 
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
							  "from GameStats";
			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(queryTwo);
				while(rs.next()) {
					int computerWins = rs.getInt("COMPUTER_WINS");
					System.out.println(computerWins + "\t");
				}
			} catch (SQLException e) {
				System.out.println("Failed to show number of computer wins.");
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
								"from GameStats";
			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(queryThree);
				while(rs.next()) {
					int humanWins = rs.getInt("HUMAN_WINS");
					System.out.println(humanWins + "\t");
				}
			} catch (SQLException e) {
				System.out.println("Failed to show number of human wins.");
			}finally {
				if (stmt != null) { stmt.close();
			        }
			  }
			return humanWins;
		}
		
		//query to return the average number of draws, done by James
		public double getNumberOfDraws() throws SQLException {
			Statement stmt = null;
			String queryFour = "select AVG(DRAWS)" + 
							   "from GameStats";

			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(queryFour);
				while(rs.next()) {
					double numberOfDraws = rs.getDouble("DRAWS");
					System.out.println(numberOfDraws + "\t");
				}
			} catch (SQLException e) {
				System.out.println("Failed to show number of average draws.");
			}finally {
				if (stmt != null) { stmt.close();
				}
			}
			return numberOfDraws;
		}
		
		//query to return the largest number of rounds played in a single game
		public int getNumberOfRoundsPlayedInGame() throws SQLException {
			Statement stmt = null;
			String queryFive = "select MAX(NUMBER_OF_ROUNDS)" +
							   "from GameStats";
			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(queryFive);
				while(rs.next()) {
					int numberOfRoundsPlayedInGame = rs.getInt("NUMBER_OF_ROUNDS");
					System.out.println(numberOfRoundsPlayedInGame + "\t");
				}
			 	
			} catch (SQLException e) {
				System.out.println("Failed to show number of average draws.");
			}finally {
				if (stmt !=null ) { stmt.close();
				}
		    }
			return numberOfRoundsPlayedInGame;	
		 }	
}
