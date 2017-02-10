package KKGame;

import java.util.ArrayList;
import java.util.List;

public class Game {

	public final Integer GameId;

/*	public Integer getId() {
		return GameId;
	}
	*/
	private final Player Player1;
	private final Player Player2;
	public Integer Status;
	
	protected Player CurrentPlayer = null;

	public List<Cell> Game = new ArrayList<Cell>();


	/* *	Constructors	* */

	public Game(String first) {
		this.GameId = -1;
		this.Player1 = new Player(first);
		this.Player2 = new Player("Computer");
		_gameInProggress();
		System.out.println("Game created, Id: " + this.GameId + "\\n Prvi igrač: " + first + ", drugi igrač: computer" );	
	}

	public Game(String first, String second) {
		this.GameId = -1;
		this.Player1 = new Player(first);
		this.Player2 = new Player(second);
		_gameInProggress();
		System.out.println("Game created, Id: " + this.GameId + "\\n Prvi igrač: " + this.Player1.Name + ", drugi igrač: " + this.Player2.Name );	
	}

	public Game(Player _player1, Player _player2) {

		this.GameId = -1;
		this.Player1 = _player1;
		this.Player2 = _player2;
		_gameInProggress();

		System.out.println("Game created, Id: " + this.GameId + "\\n Prvi igrač: " + this.Player1.Name + ", drugi igrač: " + this.Player2.Name );	
	}

	public Game(Player _player1, Player _player2, Integer gameId) {

		this.GameId = gameId;
		this.Player1 = _player1;
		this.Player2 = _player2;
		_gameInProggress();

		System.out.println("Kreirana igra ");
		System.out.println("Game created, Id: " + this.GameId + "\\n Prvi igrač: " + this.Player1.Name + ", drugi igrač: " + this.Player2.Name );	
	}

	/* *	Public methods	* */ 
	public String toString() {
		Integer size =  this.Game.size();
		return this.Player1 + " : " + this.Player2 + this.GameId.toString()
		+ "\\n Zauzetu: " + size.toString();
	
	}

	
	public Boolean Play(Player player, Cell cell) {
		Boolean moved = false;
		if (!this._checkCellsLimit() && !this._checkIfCellsCantBeAdded(this.Game, cell))
		{	
			moved = true;
			this.Game.add(cell);
			System.out.println("Added: " + cell.toString() );	
		}
		Boolean isGameOver = this._isSomeoneWin();
		
		if(isGameOver)
		{
			if(this.CurrentPlayer == this.Player1)		
				this.Status = GameStatus.Player1Wins;
			else
				this.Status = GameStatus.Player2Wins;
		}
		Integer size = this.Game.size();
		System.out.println("Game played, Id: " + this.GameId + "\\n Prvi igrač: " + this.Player1.Name + ", drugi igrač: " + this.Player2.Name + "\\n polja: " + size );	
		return isGameOver;

	}

	public void SetCurrentPlayer(Player player) {
		this.CurrentPlayer = player;
	}

	/*   // Public methods
	 * 
	 * 	
	 * Private methods
	 * */ 

	private Player _getOppositePlayer()
	{
		return (this.CurrentPlayer == this.Player1) ? this.Player2 : this.Player1;
	}
	private Boolean _checkCellsLimit() {
	
		return (this.Game.size() >= 9) ? true : false;
	}
	
	private Boolean _checkIfCellsCantBeAdded(final List<Cell> list, final Cell cell) {

	    return list.stream().filter(c -> c.getRow().equals(cell.getRow()) && c.getColumn().equals(cell.getColumn())).findFirst().isPresent();
	
	}

	private Boolean _isSomeoneWin() {
		Boolean returnValue = false;
		List<Combination> comb = _fillCells2Combination();
		Combination combination = null;
		
		for(Combination c : comb)
		{
			if(c.Cells.size()== 3)
				combination = c;
		}
		
		if(combination == null)
			return  returnValue;
		
		Boolean winn = !combination.IsPlayerTheWinner(this.CurrentPlayer);
		Boolean filled = this.Game.size() == 9;
		if(winn)
		{
			this.CurrentPlayer.Winn();
			this._getOppositePlayer().Lose();
			_gameFinished();
			return returnValue;
		}
		else if(filled)
		{
			this.CurrentPlayer.Draw();;
			this._getOppositePlayer().Draw();;
			_gameFinished();
			return returnValue;
		}
		else
		{		
			this._switchPlayers();
			return returnValue;
		}
	}

	/**
	 * 
	 */
	private void _gameInProggress() {
		this.Status = GameStatus.InProgress;
	}

	/**
	 * 
	 */
	private void _gameFinished() {
		this.Status = GameStatus.Finished;
	}

	private void _switchPlayers() {

			System.out.println("Trenutni igrač:  " + this.CurrentPlayer.toString());
		this.CurrentPlayer = (this.CurrentPlayer == this.Player1) ? this.Player2 : this.Player1;
		System.out.println("nakon zamjene je igrač:  " + this.CurrentPlayer.toString());
	}

	/**
	 * 
	 */
	private List<Combination> _fillCells2Combination() {
		List<Combination> comb = new ArrayList<Combination>();
		Combination rows1 = new Combination();
		Combination rows2 = new Combination();
		Combination rows3 = new Combination();
		Combination columns1 = new Combination();
		Combination columns2 = new Combination();
		Combination columns3 = new Combination();
		Combination diagonal1 = new Combination();
		Combination diagonal2 = new Combination();
		
		for (Cell cell : this.Game) {
			if (cell.Row == 1) {
				rows1.Cells.add(cell);
				if (cell.Column == 1) {
					columns1.Cells.add(cell);
					diagonal1.Cells.add(cell);
				}
				if (cell.Column == 2) {
					columns2.Cells.add(cell);
				}

				if (cell.Column == 3) {
					columns3.Cells.add(cell);
					diagonal2.Cells.add(cell);
				}
			}
			if (cell.Row == 2) {
				rows2.Cells.add(cell);
				if (cell.Column == 1) {
					columns1.Cells.add(cell);
				}
				if (cell.Column == 2) {
					columns2.Cells.add(cell);
					diagonal1.Cells.add(cell);
					diagonal2.Cells.add(cell);
				}

				if (cell.Column == 3) {
					columns3.Cells.add(cell);
				}

			}
			if (cell.Row == 3) {
				rows3.Cells.add(cell);
				if (cell.Column == 1) {
					columns1.Cells.add(cell);
					diagonal2.Cells.add(cell);

				}
				if (cell.Column == 2) {
					columns2.Cells.add(cell);
				}

				if (cell.Column == 3) {
					columns3.Cells.add(cell);
					diagonal1.Cells.add(cell);
				}
			}
		}
		comb.add(rows1);
		comb.add(rows2);
		comb.add(rows3);
		comb.add(columns1);
		comb.add(columns2);
		comb.add(columns3);
		comb.add(diagonal1);
		comb.add(diagonal2);
		return comb;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * /*
	 * 
	 * 
	 * 
	 * Old methods idea private void gameStatistic() {
	 * this.CheckIfStartedGameExist(); this.GetPlayerConfiguration();
	 * this.GetPlayingMode(); }
	 * 
	 * private void GetPlayingMode() { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * private void GetPlayerConfiguration() { // TODO Auto-generated method
	 * stub
	 * 
	 * }
	 * 
	 * private void CheckIfStartedGameExist() { // TODO Auto-generated method
	 * stub
	 * 
	 * }
	 * 
	 * public void gameTurn(){
	 * 
	 * this.ExecuteMove(); this.CheckIfGameIsEnded(); }
	 * 
	 * private void CheckIfGameIsEnded() {
	 * 
	 * 
	 * }
	 * 
	 * private void ExecuteMove() {
	 * 
	 * }
	 * 
	 * public void gameEnd() {
	 * 
	 * }
	 * 
	 * public void GamesStatistic() { this.CurrentGameStatistic();
	 * this.AlGamesStatistic(); }
	 * 
	 * private void CurrentGameStatistic() { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * private void AlGamesStatistic() { this.gameStatistic(); }
	 */
}
