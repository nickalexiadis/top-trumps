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
		
		//update the database
		 public int updateGameStats() {
		        String SQL = 
		 
		        int affectedrows = 0;
		 
		        try (Connection connection = connect();
		                java.sql.PreparedStatement pstmt = connection.prepareStatement(SQL)) {
		 
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
							  "from table"; 
			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(query);
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
		 }
		
		//query to return how many times the computer has won 
		public int getComputerWins() throws SQLException {
			Statement stmt = null;
			String QueryTwo = "select SUM(COMPUTER_WINS)" + 
							  "from table";
			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(query);
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
		}
		
		//query to return how many times the human has won  
		public int getHumanWins() throws SQLException {
			Statement stmt = null;
			String QueryThree = "select SUM(HUMAN_WINS)" +
								"from table";
			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(query);
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
		}
		
		//James, query to return the average number of draws 
		public double getNumberOfDraws() throws SQLException {
			Statement stmt = null;
			String QueryFour = "select AVG(DRAWS)" + 
							   "from TABLE";

			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(query);
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
		}
		
		//Nana, query to return the largest number of rounds played in a single game
		public int getNumberOfRoundsPlayedInGame() throws SQLException {
			String QueryFive = 
			return numberOfRoundsPlayedInGame;	
		}	
}
