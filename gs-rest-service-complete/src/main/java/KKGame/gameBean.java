package KKGame;

import java.util.ArrayList;
import java.util.List;

public class gameBean {
	public String _player1Name;
	public String _player2Name;
	public Player _player1;
	public Player _player2;

	private Boolean isComputerPlaying = false;
	


	public Boolean getIsComputerPlaying() {
		return  this._player1.Name.equals("computer") || this._player2.Name.equals("computer");
	}

	public List<Game> Statistics = new ArrayList<Game>();
	public List<Player> Players = new ArrayList<Player>();
	
	public Player CreatePlayer(String name) {
		for (Player pl : this.Players) {
			if (pl.Name.matches(name))
			{	
				System.out.println("Found Player " + name);
				return pl;
			}
		}
		return this.NewPlayer(name);
	}

	Player WhoPlaysFirst() {
		// TODO Auto-generated method stub
		int temp = (Math.random() <= 0.5) ? 1 : 2;
		if (temp == 1)
			return this._player1;
		else
			return this._player2;
	}


	public Game GetGameById(Integer gameId) {
		return this.Statistics.get(gameId);
	}
	public gameBean() {
		super();
		if(this.Statistics.size() == 0)
		{	
			this.GetPlayer("computer");
			this.GetPlayer("Sina");
		}
	}

	public List<Player> getAllPlayers() {
		return this.Players;
	}
	
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
	protected void _parseUrl2players(String first, String second, Boolean forceTest) {
		

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

			System.out.println("Dodajem : " + first + " u statistiku igrača");

			this._player1Name = this._player1.Name;

			this._player2 = this.GetPlayer(second);

			
			this._player2Name = this._player2.Name;/*	*/

			System.out.println("player : " + second + " u statistiku igrača");
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
				//System.out.println("Player found = " + player.toString() + ", " + name);
				return player;
			}
		}
		return null;
	}

	public Player GetPlayer(String name) {
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

	/**
	 * @param GameId
	 * @return
	 */
	protected Game CreateGameInstanceWithCurrentPlayers(Integer GameId) {
		Game game;
		if(GameId== null)
			GameId=-1;
		game = new Game(this._player1, this._player2, GameId);
		System.out.println("kreiram Prvu igru, igra id = " + game.GameId.toString());
		game.CurrentPlayer = this.WhoPlaysFirst();
		System.out.println("Prvi igra = " + game.CurrentPlayer.toString() );
		
		if(game.CurrentPlayer.Name.equals("computer"))
		{
			System.out.println("CreateGameInstanceWithCurrentPlayer: Igram umjesto kompjutera" );
			game.CurrentPlayer._isComputer = true;
			game.Status = game.Play(game.CurrentPlayer.computer.Hint(game, ComputerSkill.MEDIUM));/**/
		}
		this.Statistics.add(game);
		game.GameId = this.Statistics.size() -1;
		System.out.println("Statistic size =" + this.Statistics.size());	
		return game;
	}

	public PlayerStatisticDataTable GetPlayerStatisticDataTable() {
		return new PlayerStatisticDataTable(0, this.Players.size(), this.Players.size(), this.Players);
	}

	/*public Game TestGame(String p1, String p2) {
		// TODO Auto-generated method stub
		
		this._parseUrl2players(p1, p2, true);
		Game game = this.CreateGameInstanceWithCurrentPlayers(null);
		System.out.println(game.CurrentPlayer.Name);
		if(game.CurrentPlayer.Name == "computer") {
			game.makeComputerMove();
			System.out.println("Test computer move");
		}
		Cell cell = new Cell(1,1,game.GameId, game.CurrentPlayer);
		game.Play(cell);
		return game;
	}*/
}
