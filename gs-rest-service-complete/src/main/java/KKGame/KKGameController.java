package KKGame;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController

@RequestMapping("/game")
public class KKGameController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private WebRequest webRequest;
	public gameBean GameProcessor = new gameBean();
	public Integer cnt = 1;

	/************************************************************************
	 * 
	 * Play method
	 * 
	 * @return
	 * 
	 * 
	 ***********************************************************************/
	@RequestMapping(value = "/play", method = RequestMethod.GET, produces = { "application/json" })
	public String Play(HttpServletRequest request) {
		Boolean ReturnValue = false;
		// Get required arguments
		
		Game Game;
		Integer GameId;
		Cell Cell;
		Integer Row;
		Integer Column;

		String gameId = request.getParameter("gameId");
		String row = request.getParameter("row");
		String column = request.getParameter("column");

		if (gameId == null || row == null || column == null)
			return ReturnValue.toString();

		// Get game
		GameId = Integer.decode(gameId);
		Game = this.GameProcessor.getGameById(GameId);


		Row = Integer.decode(row); 
		Column = Integer.decode(column);
		Cell = new Cell(Row, Column, GameId, Game.CurrentPlayer);
		ReturnValue = Game.Play(Game.CurrentPlayer, Cell);
		return Game.CurrentPlayer.Name;/**/ //ReturnValue.toString();

	}

	/************************************************************************
	 * 
	 * New Game method
	 * 
	 * 
	 ***********************************************************************/
	@RequestMapping(value = "/new", method = RequestMethod.GET, produces = { "application/json" })
	public String New(HttpServletRequest request) {
		// Get required arguments
		String first = request.getParameter("first");
		String second = request.getParameter("second");
		Game game = null;;
		// parse players, is computer playing
		GameProcessor._parseUrl2players(first, second);

		// Create new gane and add it to collection
		Integer GameId = this.GameProcessor.Statistics.size() -1;

		System.out.println("Indeks: " + GameId.toString() );	
	//	return GameId.toString();
		if(GameId <  0)
		{
			game = CreateGameInstanceWithCurrentPlayers(0);

			System.out.println("kreiram Prvu igru, igra id = " + game.getId().toString());
			System.out.println("Prvi igra = " + game.CurrentPlayer.toString() );
			System.out.println( );
			System.out.println( );
		}
		else
		{
			game = this.GameProcessor.getGameById(GameId);
			
			if(game != null && game.Status == GameStatus.Finished)
			{	
				System.out.println("Zadnja igra je dovršena, kreiram novu; = ");
				game = CreateGameInstanceWithCurrentPlayers(GameId);
				System.out.println("Nova igra id= " + game.CurrentPlayer.toString() );
				
				System.out.println("Prvi igra = " + game.CurrentPlayer.toString() );
				System.out.println( );
				System.out.println( );
			}
			else
			{
				System.out.println("Igra se nastavlja, igra = " + game.CurrentPlayer.toString() );
				game.Status = GameStatus.Finished;
				System.out.println( );
				System.out.println( );
			}
		}
		
		return game.getId().toString();/**/
			
	}

	/**
	 * @param GameId
	 * @return
	 */
	protected Game CreateGameInstanceWithCurrentPlayers(Integer GameId) {
		Game game;
		game = new Game(this.GameProcessor._player1, this.GameProcessor._player2, GameId);
		game.CurrentPlayer = this.GameProcessor.WhoPlaysFirst();
		this.GameProcessor.Statistics.add(game);
		return game;
	}

	/************************************************************************
	 * 
	 * Create Player method
	 * 
	 * 
	 ***********************************************************************/
	@RequestMapping(value = "/newplayer", method = RequestMethod.GET, produces = { "application/json" })
	public String NewPlayer(HttpServletRequest request) {
		// Get required arguments
		String name = request.getParameter("name");
		Player player;

		if(name == null)
		{
			return"Give me a name";
		}
		
		player = this.GameProcessor.GetPlayer(name);
		if(player != null)
		{
			return player.Name + this.GameProcessor.Players.size();
		}
		else
		{
			return "Nema takvog, kreiram";
		}
	}
}
