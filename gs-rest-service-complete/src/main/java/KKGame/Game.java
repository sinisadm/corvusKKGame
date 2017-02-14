package KKGame;

import java.util.ArrayList;
import java.util.List;

public class Game {

	public Integer GameId;

/*	public Integer getId() {
		return GameId;
	}
	*/
	public final Player Player1;
	public final Player Player2;
	public GameStatus Status;
	
	public Player CurrentPlayer = null;

	public List<Cell> Game = new ArrayList<Cell>();

	public static boolean logicalXOR(boolean x, boolean y) {
	    return ( ( x || y ) && ! ( x && y ) );
	}

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

		System.out.println("Game created, Id: " + this.GameId + "\\n Prvi igrač: " + this.Player1.Name + ", drugi igrač: " + this.Player2.Name );	
	}

	/* *	Public methods	* */ 
	public String toString() {
		Integer size =  this.Game.size();
		return this.Player1 + " : " + this.Player2 + this.GameId.toString()
		+ "\\n Zauzetu: " + size.toString();
	
	}

	
	public GameStatus Play( Cell cell) {

		
		Boolean limit = this._checkCellsLimit();
		Boolean cantBeAdded = this.checkIfCellsCantBeAdded( cell);
		Boolean isGameOver = false;
		
		
		if (!limit)
		{	
			if(!cantBeAdded)
			{
				this.Game.add(cell);
				this._gameInProggress();
				System.out.println("Added: " + cell.toString() );
				isGameOver = this._isSomeoneWin();	

				System.out.println("Game Status : " + isGameOver.toString() );
				return this.Status;
			}
			else
			{
				this._gameFieldNotEmpty();
				return this.Status;
			}
		}
		else
		{
			this.Status = GameStatus.Finished;
			//return this.Status;
			
		}
		
		if(isGameOver)
		{
			if(this.CurrentPlayer == this.Player1)		
				this.Status = GameStatus.Player1Wins;
			else
				this.Status = GameStatus.Player2Wins;
		}
		else
		{
			Player player = this._getCurrentPlayer();
			if(player.Name.equalsIgnoreCase("computer"))
			{
				player.MakeMove(this);
			}
			
		}
		Integer size = this.Game.size();
		System.out.println("Game played, Id: " + this.GameId + "\\n Prvi igrač: " + this.Player1.Name + ", drugi igrač: " + this.Player2.Name + "\\n polja: " + size );	
		return this.Status;

	}

	/**
	 * 
	 */
	private void _gameFieldNotEmpty() {
		this.Status = GameStatus.FieldNotEmpty;
	}

	public void SetCurrentPlayer(Player player) {
		this.CurrentPlayer = player;
	}

	/*   // Public methods
	 * 
	 * 	
	 * Private methods
	 * */ 

	public Player GetOppositePlayer()
	{
		return (this.CurrentPlayer == this.Player1) ? this.Player2 : this.Player1;
	}
	private Player _getCurrentPlayer()
	{
		return (this.CurrentPlayer == this.Player1) ? this.Player1 : this.Player2;
	}
	private Boolean _checkCellsLimit() {
	
		return (this.Game.size() >= 9) ? true : false;
	}
	
	public Boolean checkIfCellsCantBeAdded( final Cell cell) {
		Boolean b = this.Game.stream().filter(c -> c.getRow().equals(cell.getRow()) && c.getColumn().equals(cell.getColumn())).findFirst().isPresent();
		//this.Game.add(cell);
		System.out.println("Cant be added   " + b.toString());
	    return b;
	}

	private Boolean _isSomeoneWin() {
		Boolean returnValue = false;
		Boolean winn = false;
		Boolean filled = false;
		
		List<Combination> comb = _fillCells2Combination();
		Combination combination = null;
		
		for(Combination c : comb)
		{
			if(c.Cells.size()== 3)
			{
				combination = c;
				if(combination != null)
				{	
					if(combination.IsPlayerTheWinner(this.CurrentPlayer))
					{
						winn = true;
						this._gameFinished();
					}
					filled = this.Game.size() == 9;
				}
			}
		}
		
		if(winn)
		{	returnValue = true;
			this.CurrentPlayer.Winn();
			this.GetOppositePlayer().Lose();
			_gameFinished();
			return returnValue;
		}
		else if(filled)
		{
			this.CurrentPlayer.Draw();;
			this.GetOppositePlayer().Draw();;
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
	public List<Combination> _fillCells2Combination() {
		List<Combination> comb = new ArrayList<Combination>();
		Combination rows1 = null;
		Combination rows2 = null;
		Combination rows3 = null;
		Combination columns1 =null;
		Combination columns2 = null;
		Combination columns3 = null;
		Combination diagonal1 = null;
		Combination diagonal2 = null;
		
if(logicalXOR(rows1 ==	null , rows1.Cells.size() > 0));
	rows1 = new Combination(GameRuleType.HORIZONTAL,GameRowType.FIRST);
	
if(logicalXOR(rows2 ==	null , rows2.Cells.size() > 0));
	rows2 = new Combination(GameRuleType.HORIZONTAL,GameRowType.SECOND);

if(logicalXOR(rows1 ==	null , rows1.Cells.size() > 0));
		 rows1 = new Combination(GameRuleType.HORIZONTAL,GameRowType.FIRST);
		 rows2 = new Combination(GameRuleType.HORIZONTAL,GameRowType.SECOND);
		 rows3 = new Combination(GameRuleType.HORIZONTAL,GameRowType.THIRD);
		 columns1 = new Combination(GameRuleType.VERTICAL, GameCellType.A);
		 columns2 = new Combination(GameRuleType.VERTICAL, GameCellType.B);
		 columns3 = new Combination(GameRuleType.VERTICAL, GameCellType.C);
		 diagonal1 = new Combination(GameRuleType.DIAGONAL, GameCellType.A);
		 diagonal2 = new Combination(GameRuleType.DIAGONAL, GameCellType.C);
		
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
		if(rows1.Cells.size() > 0)
			comb.add(rows1);
		if(rows2.Cells.size() > 0)
			comb.add(rows2);
		if(rows3.Cells.size() > 0)	
			comb.add(rows3);
		if(columns1.Cells.size() > 0)	
			comb.add(columns1);
		if(columns2.Cells.size() > 0)	
			comb.add(columns2);
		if(columns3.Cells.size() > 0)	
			comb.add(columns3);
		if(diagonal1.Cells.size() > 0)	
			comb.add(diagonal1);
		if(diagonal2.Cells.size() > 0)	
			comb.add(diagonal2);
		

		System.out.println("Combination size::  " + comb.size());
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
	 * this.ChecbkIfStartedGameExist(); this.GetPlayerConfiguration();
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
