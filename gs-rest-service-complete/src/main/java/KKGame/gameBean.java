package KKGame;

import java.util.ArrayList;
import java.util.List;

public class gameBean {
	public String _player1Name;
	public String _player2Name;
	public Player _player1;
	public Player _player2;

	private Boolean isComputerPlaying = false;
	

	public List<Game> Statistics = new ArrayList<Game>();
	public List<Player> Players = new ArrayList<Player>();
	
	public Player CreatePlayer(String name) {
		for (Player pl : this.Players) {
			if (pl.Name.matches(name))
				return pl;
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


	protected Game GetLastGame() {
		return this.Statistics.get(this.Statistics.lastIndexOf(Statistics) );
	}

	protected Game getGameById(Integer id) {
		Game game = null;
		
		System.out.println("traženi gameId = :" + id.toString() );
		System.out.println("Statistika sadrži : " + this.Statistics.size() + " igara" );
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
			//	System.out.println("Value returned = " + player.toString() + ", " + name);
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

}
