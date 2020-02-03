package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import com.mysql.jdbc.PreparedStatement;

//This class will establish the connection between the Database and the game, it will update the stats in the database.
 
public class TopTrumpsDatabase {
		
		//database columns, declared as variables 
		private int numberOfGames;
		private int winner;
		private int numberOfDraws;
		private int computerWins;
		private int humanWins;
		private int numberOfRoundsPlayedInGame;
		
		//defining the attributes for the connection: user and password
		//create a variable for the connection along with the user ID and password 
		    private String username = "m_19_2459499l";
		    private String password ="2459499l";
		    private static Connection connection = null;
		  
		    //testing the database
		    public static void main(String args[]) {
		    		TopTrumpsDatabase test = new TopTrumpsDatabase();
		    		test.connect();
		    }
		    
		//setup the database connection, reference: https://www.postgresqltutorial.com/postgresql-jdbc/connecting-to-postgresql-database/
			public Connection connect() {
				
		    String dbname = "m_16_2287667k";
			String username = "m_16_2287667k";
			String password = "2287667k";

			try {
				connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/", username, password);
			}

			catch (SQLException e) {
				System.err.println("Connection Failed!");
				e.printStackTrace();
			}
			return null;

		}
		
		//closing the database connection
		public void closeConenction() {
			try {
				connect().close();	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		//insert the values in the database, a bit updated, reference is: https://www.postgresqltutorial.com/postgresql-jdbc/insert/
		public int updateDatabase(int numberOfGames, int winner, int numberOfDraws, int numberOfRounds) {
			String SQL = "INSERT INTO GAME_STATS(GAME_ID,WIN,DRAWS,ROUNDS)" + "VALUESVALUES (?,?,?,?,?)";
			
			int id = 0;
	 
	        try (Connection connect = connect();
	             java.sql.PreparedStatement pstmt = connect.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {
	        	
	            pstmt.setInt(1, numberOfGames);
	            pstmt.setInt(2, winner);
	            pstmt.setInt(3, numberOfDraws);
	            pstmt.setInt(4, numberOfRounds);
	            
	            //executes updates
	            int affectedRows = pstmt.executeUpdate();
	           //will check the affected rows
	            if(affectedRows > 0) {
	                //will get the id back 
	            	try(ResultSet rs = pstmt.getGeneratedKeys()) {
	                    if(rs.next()) {
	                        id = rs.getInt(1);
	                    }
	                } catch (SQLException ex) {
	                    System.out.println(ex.getMessage());
	                }
	            }
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
	        return id;
	    }
		 
		//reference for the following getters: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
		//query to return the number of games played overall  
		public int getNumberOfGames() throws SQLException {
			Statement stmt = null;
			String queryOne = "select COUNT(GAME_ID)" +
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
			String queryTwo = "select COUNT(GAME_ID)" + 
							  "from GAME_STATS" +
							  "where winner = 'COMPUTER'";
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
			String queryThree = "select COUNT(WINS)" +
								"from GAME_STATS" +
								"where winner = 'HUMAN";
								
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
		
		//query to return the average number of draws 
		public double getNumberOfDraws() throws SQLException {
			Statement stmt = null;
			String queryFour = "select AVG(DRAWS)" + 
							   "from GAME_STATS";

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
							   "from GAME_STATS";
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