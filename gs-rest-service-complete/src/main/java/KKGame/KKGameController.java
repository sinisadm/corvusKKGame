package KKGame;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
public class KKGameController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private HttpServletRequest webRequest;
	public gameBean GameProcessor = new gameBean();
	public Integer cnt = 1;
	
	

	/************************************************************************
	 * game/new?first={player1}&second={player2} 
	 * game/new?first=Sinisa  i game/new?second=Sinisa 
	 * game/play?gameId={id}&row={row_number}&column={column_number}
	 * game/status?gameId={id}
	 * stats 
	 * 
	 * 
	 * 
	 * 
	 * Play method
	 * 
	 * @return
	 * 
	 * 
	 ***********************************************************************/

	@RequestMapping(value = "/play", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<Game> Play(HttpServletRequest request ) {
		Game Game;
		Integer GameId;
		Cell Cell;
		Integer Row;
		Integer Column;

		String gameId = request.getParameter("gameId");
		String row = request.getParameter("row");
		String column = request.getParameter("column");

		if (gameId == null || row == null || column == null)
		{
			System.out.println("Some of required switches missing" );
			return null;
		}
		// Get game
		GameId = Integer.decode(gameId);
		Game = this.GameProcessor.getGameById(GameId);

		Row = Integer.decode(row); 
		Column = Integer.decode(column);
		Cell = new Cell(Row, Column, GameId, Game.CurrentPlayer);

        HttpHeaders responseHeaders = new HttpHeaders();

		if(Game.Play(Cell) != GameStatus.FieldNotEmpty)
		{
			//System.out.println("returned OK" );
			return new ResponseEntity<>(Game, responseHeaders, HttpStatus.OK);
		}
		else
		{
			//System.out.println("Returned 402" );
	        return new ResponseEntity<>(Game, responseHeaders, HttpStatus.PAYMENT_REQUIRED);
		}

	}
	/************************************************************************
	 * 
	 * Stats method
	 * 
	 * 
	 ***********************************************************************/
	@RequestMapping(value = "/stats", method = RequestMethod.GET, produces = { "application/json" })
	public List<Player> stats(HttpServletRequest request) {
		return this.GameProcessor.Players;
	}
	/************************************************************************
	 * 
	 * Status method
	 * 
	 * 
	 ***********************************************************************/
	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = { "application/json" })
	public Game status(@RequestParam(value="gameId", defaultValue= "-1")Integer gameId, HttpServletRequest request) {
		Game g = this.GameProcessor.GetGameById(gameId);
		Integer s = this.GameProcessor.Statistics.size();
	    
	    return g;

	}
	
	/************************************************************************
	 * 
	 * Status players method
	 * 
	 * 
	 ***********************************************************************/
	@RequestMapping(value = "/statusplayers", method = RequestMethod.GET, produces = { "application/json" })
	public PlayerStatisticDataTable statusPlayers(@RequestParam(value="gameId", defaultValue= "-1")Integer gameId, HttpServletRequest request) {
		List<Player> p = this.GameProcessor.getAllPlayers();
		Integer s = this.GameProcessor.Statistics.size();
		
		PlayerStatisticDataTable psdt = this.GameProcessor.GetPlayerStatisticDataTable();
		
	    return psdt;

	}
	
	
	/************************************************************************
	 * 
	 * New Game method
	 * 
	 * 
	 ***********************************************************************/
	@RequestMapping(value = "/new", method = RequestMethod.GET, produces = { "application/json" })
	public Game New(HttpServletRequest request) {


		// Get required arguments
		String first = request.getParameter("first");
		String second = request.getParameter("second");
		Game game = null;;
		// parse players, is computer playing
		GameProcessor._parseUrl2players(first, second, false);

		// Create new gane and add it to collection
		Integer GameId = this.GameProcessor.Statistics.size() -1;

//		System.out.println("Indeks: " + GameId.toString() );	

		if(GameId <  0)
		{
			game = this.GameProcessor.CreateGameInstanceWithCurrentPlayers(0);

			System.out.println("Igra " + game.GameId + ", Status: " + game.Status  );
			System.out.println( );
		}
		else
		{
			GameId = this.GameProcessor.Statistics.size();
			game = this.GameProcessor.CreateGameInstanceWithCurrentPlayers(GameId);
			//game = this.GameProcessor.getGameById(GameId);
			
			if(game != null && game.Status == GameStatus.Finished)
			{	
				System.out.println("Zadnja igra je dovršena, kreiram novu; = ");
				game = this.GameProcessor.CreateGameInstanceWithCurrentPlayers(GameId);
				System.out.println("Nova igra id= " + game.CurrentPlayer.toString() );
				
				System.out.println("Prvi igra = " + game.CurrentPlayer.toString() );

			}
			else
			{
				System.out.println("Igra se nastavlja, igra = " + game.CurrentPlayer.toString() );
				
			}
				System.out.println( );
				System.out.println( );
		}
		
		return game;/*.getId().toString()*/
			
	}


	/************************************************************************
	 * 
	 * Create Player method
	 * 
	 * 
	 ***********************************************************************/
	@RequestMapping(value = "/newplayer", method = RequestMethod.GET, produces = { "application/json" })
	public Player NewPlayer(HttpServletRequest request) {
		// Get required arguments
		String name = request.getParameter("name");
		Player player;

		if(name == null)
		{
			return null;
		}
		
		player = this.GameProcessor.GetPlayer(name);
		if(player != null)
		{
		}
			return player;		//.Name + this.GameProcessor.Players.size();
	/*	else
		{
			return "Nema takvog, kreiram";
		}*/
	}
	/************************************************************************
	 * 
	 * Get Players method
	 * 
	 ***********************************************************************/
	@RequestMapping(value = "/getallplayers", method = RequestMethod.GET, produces = { "application/json" })
	public List<Player> GetAllPlayers(HttpServletRequest request) {
		return this.GameProcessor.Players;
	}
	/************************************************************************
	 * 
	 * Get All games method
	 * 
	 ***********************************************************************/
	@RequestMapping(value = "/getallgames", method = RequestMethod.GET, produces = { "application/json" })
	public List<Game> GetAllGames(HttpServletRequest request) {
		return this.GameProcessor.Statistics;
	}
	/************************************************************************
	 * 
	 * Test method
	 * 
	 **********************************************************************
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = { "application/json" })
	public Game Test(HttpServletRequest request) {

		String p1 = "computer";
		String p2 = "Sina";

		return this.GameProcessor.TestGame(p1,  p2);
	}*/
}
