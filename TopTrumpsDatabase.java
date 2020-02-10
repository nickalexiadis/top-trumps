package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import com.mysql.jdbc.PreparedStatement;

//This class will establish the connection between the Database and the game, it will update the stats in the database.

public class TopTrumpsDatabase {

		//database columns, declared as variables 
		private double draws;
		//private int gameWinner;
		private int userRoundWins;
		private int ai1RoundWins;
		private int ai2RoundWins;
		private int ai3RoundWins;
		private int ai4RoundWins;
		private int roundTotal;
		private int numberOfGames;
	
		//defining the attributes for the connection: user and password

		    private String username = "m_19_2444965h";
		    private String password ="2444965h";
		    private String dbName = "m_19_2444965h";
		   
		    
		    public TopTrumpsDatabase() {
		    	
		    }

		    //testing the database
		    public static void main(String args[]) {
		    		TopTrumpsDatabase test = new TopTrumpsDatabase();
		    		
		    }

		//setup the database connection, reference: https://www.postgresqltutorial.com/postgresql-jdbc/connecting-to-postgresql-database/
			public Connection connect() {
				 Connection connection = null;
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/"+dbName, username, password);
				System.out.println("Connection was successful!");
			}

			catch (SQLException e) {
				System.err.println("Connection Failed!");
				e.printStackTrace();
				
			}
			return connection;
		}
			
		
		//insert the values in the database, a bit updated, reference is: https://www.postgresqltutorial.com/postgresql-jdbc/insert/
		public void insertDatabase(int draws, String gameWinner, int roundTotal, int userRoundWins, int ai1RoundWins, int ai2RoundWins, int ai3RoundWins, int ai4RoundWins) {
//			String SQL = "BEGIN;" + "INSERT INTO GAME_STATS(GAME_ID, DRAWS,GAME_WINNER,ROUND_TOTAL,USER_ROUND_WINS,AI1_ROUND_WINS,AI2_ROUND_WINS,AI3_ROUND_WINS,AI4_ROUND_WINS)" + "VALUES (?,?,?,?,?,?,?,?,?)" + "COMMIT;";
			String SQL = "INSERT INTO GAME_STATS(GAME_ID, DRAWS,GAME_WINNER,ROUND_TOTAL,USER_ROUND_WINS,AI1_ROUND_WINS,AI2_ROUND_WINS,AI3_ROUND_WINS,AI4_ROUND_WINS)" + "VALUES (?,?,?,?,?,?,?,?,?)";

	        try (Connection connect = connect();
	            java.sql.PreparedStatement pstmt = connect.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {
	        	pstmt.setInt(1, numberOfGames);
	            pstmt.setInt(2, draws);
	            pstmt.setString(3, gameWinner);
	            pstmt.setInt(4, roundTotal);
	            pstmt.setInt(5, userRoundWins);
	            pstmt.setInt(6, ai1RoundWins);
	            pstmt.setInt(7, ai2RoundWins);
	            pstmt.setInt(8, ai3RoundWins);
	            pstmt.setInt(9, ai4RoundWins);

	            //executes updates
	            int affectedRows = pstmt.executeUpdate();
	        
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	            System.out.println(ex);
	        }

	    }
		//reference for the following getters: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
		//query to return the number of games played overall  
			public int getNumberOfGames() throws SQLException {
				Statement stmt = null;
				String queryOne = "select COUNT(GAME_ID)" + "from public.game_stats"; 
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
		//query to return how many times the AI1 has won 
		public int getAI1Wins() throws SQLException {
			
			Statement stmt = null;
			
			String queryOne = "select COUNT(GAME_ID) from public.game_stats where GAME_WINNER = 'AI Player 1'";

			try {
				
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(queryOne);
				
				while(rs.next()) {
					
					int ai1_round_wins = rs.getInt("count");
					System.out.println("AI1 Wins:" + ai1_round_wins + "\t");
				}
			} catch (SQLException e) {
				System.out.println(e);
				System.out.println("Failed to show number of computer wins.");
		
			}finally {
			
				if (stmt != null) { stmt.close();
			        }
			  }
			return ai1RoundWins;
		}
		//query to return how many times the AI2 has won 
				public int getAI2Wins() throws SQLException {
					
					Statement stmt = null;
					
					String queryTwo = "select COUNT(GAME_ID) from public.game_stats where GAME_WINNER = 'AI Player 2'";
					try {
						
						stmt = connect().createStatement();
						ResultSet rs = stmt.executeQuery(queryTwo);
						
						while(rs.next()) {
							
							int ai2_round_wins = rs.getInt("count");
							System.out.println("AI2 Wins:" + ai2_round_wins + "\t");
						}
					} catch (SQLException e) {
						System.out.println(e);
						System.out.println("Failed to show number of computer wins.");
					
					}finally {
						
						if (stmt != null) { stmt.close();
					        }
					  }
					return ai2RoundWins;
				}
			//query to return how many times the AI3 has won 
				public int getAI3Wins() throws SQLException {
					
					Statement stmt = null;
					String queryThree = "select COUNT(GAME_ID) from public.game_stats where GAME_WINNER = 'AI Player 3'";
					
					try {
						
						stmt = connect().createStatement();
						ResultSet rs = stmt.executeQuery(queryThree);
						
						while(rs.next()) {
							int ai3_round_wins = rs.getInt("count");
							System.out.println("AI3 Wins:" + ai3_round_wins + "\t");
						
						}
					} catch (SQLException e) {
						System.out.println(e);
						System.out.println("Failed to show number of computer wins.");
					
					}finally {
						
						if (stmt != null) { stmt.close();
					        }
					  }
					return ai3RoundWins;
				}
			//query to return how many times the AI4 has won 
				public int getAI4Wins() throws SQLException {
					
					Statement stmt = null;
					String queryFour = "select COUNT(GAME_ID) from public.game_stats where GAME_WINNER = 'AI Player 4'";

					try {
						
						stmt = connect().createStatement();
						ResultSet rs = stmt.executeQuery(queryFour);
						
						while(rs.next()) {
							
							int ai4_round_wins = rs.getInt("count");
							System.out.println("AI4 Wins:" + ai4_round_wins + "\t");
						}
					} catch (SQLException e) {
						System.out.println(e);
						System.out.println("Failed to show number of computer wins.");
					
					}finally {
					
						if (stmt != null) { stmt.close();
					        }
					  }
					return ai4RoundWins;
				}

		//query to return how many times the human has won  
		public int getHumanWins() throws SQLException {
			
			Statement stmt = null;
			String queryFive = "select COUNT(user_round_wins) from public.game_stats where GAME_WINNER = 'User'";

			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(queryFive);
				
				while(rs.next()) {
					int user_round_wins = rs.getInt("count");
					System.out.println("User Wins:" + user_round_wins + "\t");
				}
			} catch (SQLException e) {
				System.out.println(e);
				System.out.println("Failed to show number of human wins.");
			
			}finally {
				
				if (stmt != null) { stmt.close();
			        }
			  }
			return userRoundWins;
		}

		//query to return the average number of draws 
		public double getNumberOfDraws() throws SQLException {
			
			Statement stmt = null;
			String querySix = "select AVG(DRAWS) from public.GAME_STATS";

			try {
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(querySix);
				
				while(rs.next()) {
					
					double draws = rs.getDouble("avg");
					System.out.println("Average draws:"+draws + "\t");
				}
			} catch (SQLException e) {
				System.out.println(e);
				System.out.println("Failed to show number of average draws.");
			
			}finally {
				
				if (stmt != null) { stmt.close();
				}
			}
			return draws;
		}

		//query to return the largest number of rounds played in a single game
		public int getNumberOfRoundsPlayedInGame() throws SQLException {
			
			Statement stmt = null;
			String querySeven = "select MAX(ROUND_TOTAL) from public.GAME_STATS";
			try {
				
				stmt = connect().createStatement();
				ResultSet rs = stmt.executeQuery(querySeven);
				
				while(rs.next()) {
					
					int round_total = rs.getInt("max");
					System.out.println("Max number of Total rounds:"+ round_total + "\t");
				}

			} catch (SQLException e) {
				
				System.out.println("Failed to show max number of total rounds.");
			
			}finally {
				
				if (stmt !=null ) { stmt.close();
				}
		    }
			return roundTotal;	
		 }		
		//closing the database connection
				public void closeConenction() {
					try {
						connect().close();	
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
}
