package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TopTrumpsDatabase {
		
		//database columns, declared as variables 
		private int numberOfGames;
		private int computerWins;
		private int humanWins;
		private double numberOfDraws;
		private int numberOfRoundsPlayedInGame;
		
		//defining the attributes for the connection: URL, user and password
		//create a variable for the connection along with the user ID and password 
		private String connectionURL = "jdbc:postgresql://localhost/dvdrental";
		private final String user = "postgres";
		private final String password = "<add password>";

		    
		    
		//setup the database connection, reference: https://www.postgresqltutorial.com/postgresql-jdbc/connecting-to-postgresql-database/
		public Connection con() throws SQLException {
		       
			Connection connect = null;
		    
			try {
		            connect = (Connection) DriverManager.getConnection(connectionURL, user, password);
		            System.out.println("Connected to the PostgreSQL server successfully.");
		        } catch (SQLException e) {
		           
		        	System.out.println(e.getMessage());
		        	
		        } finally {
		        	//after everything is performed, close the connection
		        	connect.close();
		        }
			
				return connect;
		    
			}
		
		//reference for the following getters: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
		
		//query to return the number of games played overall  
		public int getNumberOfGames() throws SQLException {
			Statement stmt = null;
			String queryOne = "select SUM(NUMBER_OF_GAMES)" +
							  "from table"; 
			try {
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					int numberOfGames = rs.getInt("NUMBER_OF_GAMES");
					System.out.println(numberOfGames+ "\t");
				}
			} catch (SQLException e) {
				
			}finally {
				if (stmt != null) { stmt.close();
			        }
			  }
		 }
		
		//query to return how many times the computer has won 
		public int getComputerWins() throws SQLException {
			Statement stmt = null;
			String QueryTwo = "select SUM(COMPUTER_WINS)" + 
							  "from table";
			try {
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					int computerWins = rs.getInt("COMPUTER_WINS");
					System.out.println(computerWins + "\t");
				}
			} catch (SQLException e) {
				
			}finally {
				if (stmt != null) { stmt.close();
			        }
			  }
		}
		
		//query to return how many times the human has won  
		public int getHumanWins() throws SQLException {
			Statement stmt = null;
			String QueryThree = "select SUM(HUMAN_WINS)" +
								"from table";
			try {
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					int humanWins = rs.getInt("HUMAN_WINS");
					System.out.println(humanWins + "\t");
				}
			} catch (SQLException e) {
				
			}finally {
				if (stmt != null) { stmt.close();
			        }
			  }
		}
		
		//James, query to return the average number of draws 
		public double getNumberOfDraws() throws SQLException {
			String QueryFour = 
			return numberOfDraws;	
		}
		
		//Nana, query to return the largest number of rounds played in a single game
		public int getNumberOfRoundsPlayedInGame() throws SQLException {
			String QueryFive = 
			return numberOfRoundsPlayedInGame;	
		}	
}