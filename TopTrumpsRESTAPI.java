package online.dwResources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.GamePlay;
import commandline.TopTrumpsDatabase;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	
	GamePlay game = new GamePlay();
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		
	}
	 
	@GET 
	@Path("/getGame")
	public void startGame() {
		
	}
	
	@GET 
	@Path("/getStats")
	public void getStats() {
		
	}
	
	//game play methods
	
	@GET
	@Path("/getRound")
	public String getRound() throws JsonProcessingException {
		int	res = 100;
		return oWriter.writeValueAsString(res);
		
	}
	
	
	
	@GET
	@Path("/choosePlayers")
	public String choosePlayers() throws JsonProcessingException {
		return null;
		
	}
	
	
//	Display Statistics methods
	@GET
	@Path("/numOfGames")
	public String numOfGames() throws SQLException, JsonProcessingException {
		
		TopTrumpsDatabase stats = new TopTrumpsDatabase();
		int res = stats.getNumberOfGames();		
		return oWriter.writeValueAsString(res);
	}
	
	@GET
	@Path("/humanWins")
	
	public String humanWins() throws JsonProcessingException, SQLException {
		TopTrumpsDatabase stats = new TopTrumpsDatabase();
		int res = stats.getHumanWins();		
		return oWriter.writeValueAsString(res);
	}
	
	@GET
	@Path("/AI1Wins")

	public String AIWins() throws SQLException, JsonProcessingException {
		TopTrumpsDatabase stats = new TopTrumpsDatabase();
		int res1 = stats.getAI1Wins();
		int res2 = stats.getAI2Wins();
		int res3 = stats.getAI3Wins();
		int res4 = stats.getAI4Wins();
		int res = res1 + res2 + res3 + res4;
		return oWriter.writeValueAsString(res);		
	}
	
	@GET
	@Path("/avgDraw")
	
	public String avgDraw() throws SQLException, JsonProcessingException {
		TopTrumpsDatabase stats = new TopTrumpsDatabase();
		double res = stats.getNumberOfDraws();		
		return oWriter.writeValueAsString(res);
	}
	
	@GET
	@Path("/longestGame")
	
	public String longestGame() throws SQLException, JsonProcessingException {
		TopTrumpsDatabase stats = new TopTrumpsDatabase();
		int res = stats.getNumberOfRoundsPlayedInGame();		
		return oWriter.writeValueAsString(res);
		
	}
}