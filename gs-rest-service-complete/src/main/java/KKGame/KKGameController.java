package KKGame;

import java.util.ArrayList;
import java.util.List;
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
	private Boolean isComputerPlaying = false;
	private WebRequest webRequest;
	private String _player1Name;
	private String _player2Name;
	private Player _player1;
	private Player _player2;

	private List<Game> Statistics = new ArrayList<Game>();
	private List<Player> Players = new ArrayList<Player>();

	public Integer cnt = 1;

	// @RequestMapping(method = RequestMethod.GET)
	// public final @ResponseBody void playersHandler( HttpServletRequest
	// request, HttpServletResponse response, WebRequest webRequest) {}

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
		// return GameId.toString();

		Game = this.getGameById(GameId);
		String ret = "";
		/*
		 * for(Game gam : this.Statistics) { ret+= gam.toString(); }
		 */
		for (Player pl : this.Players) {
			ret += pl.toString();
		} /**/

		if (this.Players.size() == 0)
			ret = "Nema igrača";
		if (Game != null)
			return ret;// ReturnValue.toString();

		else
			return "wwwwwwwwwww";
		/*
		 * Row = Integer.decode(row); Column = Integer.decode(column);
		 * Game.CurrentPlayer = (Game.CurrentPlayer == Game.Player1) ?
		 * Game.Player2 : Game.Player1;
		 * 
		 * 
		 * Cell = new Cell(Row, Column, GameId, Game.CurrentPlayer);
		 * 
		 * ReturnValue = Game.Play(Game.CurrentPlayer, Cell);
		 * 
		 * return Game.toString(); //ReturnValue.toString();
		 */

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
		_parseUrl2players(first, second);

		// Create new gane and add it to collection
		Integer GameId = this.Statistics.size() -1;


		System.out.println("Indeks: " + GameId.toString() );	
	//	return GameId.toString();
		if(GameId <  0)
		{
			game = CreateGameInstanceWithCurrentPlayers(0);
		//	game.Status = GameStatus.Finished;
			System.out.println("kreiram Prvu igru ");
			System.out.println("Prvi igra = " + game.CurrentPlayer.toString() );
		//	System.out.println(game.toString() );
			System.out.println( );
			System.out.println( );

		}
		else
		{
			game = this.getGameById(GameId);
			
			if(game != null && game.Status == GameStatus.Finished)
			{	
				game = CreateGameInstanceWithCurrentPlayers(GameId);
				System.out.println("Zadnja igra je dovršena, kreiram novu; = ");
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

		//	
		}
		
		//if(game.getId() > 2)
		return game.getId().toString();/**/
			
	}

	/**
	 * @param GameId
	 * @return
	 */
	protected Game CreateGameInstanceWithCurrentPlayers(Integer GameId) {
		Game game;
		game = new Game(this._player1, this._player2, GameId);
		game.CurrentPlayer = this.WhoPlaysFirst();
		this.Statistics.add(game);
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
		
		player = this.GetPlayer(name);
		if(player != null)
		{
			return player.Name + this.Players.size();
		}
		else
		{
			return "Nema takvog, kreiram";
		}
		/*
		else if(this.GetPlayerByName(name) != null)
		{
			return"Player with given Name exists";
		}
		else
		{
			player = this.NewPlayer(name);
			
			if(player == null)
				return "Greška";
			else
			{
				this.Players.add(player);
				return "Kreiran igrač " + name;
			}
			
		}*/


	}

	public Player CreatePlayer(String name) {
		for (Player pl : this.Players) {
			if (pl.Name.matches(name))
				return pl;
		}
		return this.NewPlayer(name);
	}

	private Player WhoPlaysFirst() {
		// TODO Auto-generated method stub
		int temp = (Math.random() <= 0.5) ? 1 : 2;
		if (temp == 1)
			return this._player1;
		else
			return this._player2;
	}

	/**
	 * 
	 */

	protected Game GetLastGame() {
		return this.Statistics.get(this.Statistics.lastIndexOf(Statistics) );
	}

	protected Game getGameById(Integer id) {
		Game game = null;
		if(id >= 0)
			game = this.Statistics.get(id);
		
		return (game != null) ? game : null;
	}

	/**
	 * @param first
	 * @param second
	 */
	protected void _parseUrl2players(String first, String second) {
		if (first != null && second != null) // Ako su zadaba oba igraća
		{
			if (first == "computer" || second == "computer") // I jedan od njih
																// je kompjuter
			{
				this.isComputerPlaying = true;
			} else if (first != "computer" && second != "computer")// I ni jedan
																	// od njih
																	// nije
																	// kompjuter
			{
				this.isComputerPlaying = false;
			}

			this._player1 = this.GetPlayer(first);
			this.Players.add(this._player1);
			this._player1Name = this._player1.Name;
			this._player2 = this.GetPlayer(second);
			this.Players.add(this._player2);
			this._player2Name = this._player2.Name;/*	*/
		}

		if (first == null && second == null) // Ako nije zadan niti jedan igrač
		{
			this.isComputerPlaying = true;
		}
		if (first == null || second == null) // Ako nije zadan jedan igrač
		{
			this.isComputerPlaying = true;
			if (first != null || second == null) // Ako je drugi igrač null
			{
				this._player1 = this.GetPlayer(first);
				this._player2 = this.GetPlayer("computer");
			}

			if (first == null && second != null) {
				this._player1 = this.GetPlayer("computer");
				this._player2 = this.GetPlayer(second);
			}
		}
	}

	protected Player GetPlayerByName(String name) {
	
		for (Player player : this.Players) {
			if (player.Name.matches(name))  {
				System.out.println("Value returned = " + player.toString() + ", " + name);
				return player;
			}
		}
		return null;
	}

	protected Player GetPlayer(String name) {
		Player returnPlayer = this.GetPlayerByName(name);
		if (returnPlayer == null){
			returnPlayer = this.NewPlayer(name);
			this.Players.add(returnPlayer);
		}
	
		return returnPlayer;
	}

	protected Player NewPlayer(String name) {

		System.out.println("kreiram igrača = " + name);
		return new Player(name);
	}

	public static void New(String first) {

	}

	public static void Status() {

	}
}
