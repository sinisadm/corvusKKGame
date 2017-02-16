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

		System.out.println("Game created, Id: " + this.GameId + "\\n\\n Prvi igrač: " + this.Player1.Name + ",\\n drugi igrač: " + this.Player2.Name + "\\n\\n\\n");	
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
		System.out.println("");
		System.out.println("PLAY");
		System.out.println("");
		System.out.println("Limit je prijeđen: " + limit.toString());
		System.out.println(cell);
		System.out.println("");

		
		if (!limit)
		{	
			if(!cantBeAdded)
			{
				this.Game.add(cell);
				this._gameInProggress();
				System.out.println("Added: " + cell.toString() );
				isGameOver = this._isSomeoneWin();	

				System.out.println("Game Is Over : " + isGameOver.toString() );
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
/*		else
		{
			Player player = this._getCurrentPlayer();
			if(player.Name.equalsIgnoreCase("computer"))
			{

			}
			
		}*/
		Integer size = this.Game.size();
		System.out.println("Game played, Id: " + this.GameId + "\\n Prvi igrač: " + this.Player1.Name + ", drugi igrač: " + this.Player2.Name + "\\n polja: " + size );	
		return this.Status;

	}

	/**
	 * 
	 */
	private void _gameFieldNotEmpty() {
		System.out.println("Game Move not played cause cell is not empty \\n");
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
		Boolean cantBeAdded = false;
		if(this.Game.size()> 0 && cell != null){
			System.out.println("Odigrano je . " + this.Game.size() + " poteza");
			for(Cell c : this.Game)
			{
				
				System.out.println("Cell :. " + c.Row + " , "  + c.Column + ", Player: " + c.Player.Name);
			}

			cantBeAdded = this.Game.stream().filter(c -> c.getRow().equals(cell.getRow()) && c.getColumn().equals(cell.getColumn())).findFirst().isPresent();
		//this.Game.add(cell);
			System.out.println("Provjera da li čelija može biti dodana. " + cell.Row + " , "  + cell.Column + ":  " + cantBeAdded.toString());
		}
		System.out.println("Can't be added   " + cantBeAdded.toString());
	    return cantBeAdded;
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
			if(this.CurrentPlayer.Name.equals("computer"))
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
		this.CurrentPlayer = (this.Player1 == this.CurrentPlayer ) ? this.Player2 : this.Player1;
		System.out.println("nakon zamjene je igrač:  " + this.CurrentPlayer.toString());
		if(this.CurrentPlayer.toString().equals("computer"))
		{
			System.out.println("Computer move ******************************************");			
			this.makeComputerMove();

		}
		else
			System.out.println(this.CurrentPlayer.toString());
		//player.MakeMove(this);
	}

	/**
	 * 
	 */
	private void makeComputerMove() {
		/*
		 * 
		 * Computer Move
		 * 
		 * */	

			this.Status = this.Play(this.CurrentPlayer.computer.Hint(this, ComputerSkill.MEDIUM));/**/
			//this._switchPlayers();

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
		
		for (Cell cell : this.Game) {
			if (cell.Row == 1) {
				if (rows1 == null)
					rows1 = new Combination(GameRuleType.HORIZONTAL,GameRowType.FIRST);

				rows1.Cells.add(cell);
				if (cell.Column == 1) {
					if (columns1 == null)
						columns1 =new Combination(GameRuleType.VERTICAL, GameCellType.A);
					if (diagonal1 == null)
						diagonal1 =new Combination(GameRuleType.DIAGONAL, GameCellType.A);
					columns1.Cells.add(cell);
					diagonal1.Cells.add(cell);
				}
				
				
				if (cell.Column == 2) {
					if (columns2 == null)
						columns2 =new Combination(GameRuleType.VERTICAL, GameCellType.A);
					columns2.Cells.add(cell);
				}

				if (cell.Column == 3) {
					if (columns3 == null)
						columns3 =new Combination(GameRuleType.VERTICAL, GameCellType.C);
					if (diagonal2 == null)
						diagonal2 =new Combination(GameRuleType.DIAGONAL, GameCellType.C);
					columns3.Cells.add(cell);
					diagonal2.Cells.add(cell);
				}
			}
			if (cell.Row == 2) {
				if (rows2 == null)
					rows2 = new Combination(GameRuleType.HORIZONTAL,GameRowType.SECOND);

				rows2.Cells.add(cell);
				if (cell.Column == 1) {
					
					if (columns1 == null)
						columns1 =new Combination(GameRuleType.VERTICAL, GameCellType.A);
					
					columns1.Cells.add(cell);
				}
				if (cell.Column == 2) {
					if (columns2 == null)
						columns2 =new Combination(GameRuleType.VERTICAL, GameCellType.A);
					if (diagonal1 == null)
						diagonal1 =new Combination(GameRuleType.DIAGONAL, GameCellType.A);
					if (diagonal2 == null)
						diagonal2 =new Combination(GameRuleType.DIAGONAL, GameCellType.C);
					columns2.Cells.add(cell);
					diagonal1.Cells.add(cell);
					diagonal2.Cells.add(cell);
				}

				if (cell.Column == 3) {
					if (columns3 == null)
						columns3 =new Combination(GameRuleType.VERTICAL, GameCellType.C);
					columns3.Cells.add(cell);
				}

			}
			if (cell.Row == 3) {
				if (rows3 == null)
					rows3 = new Combination(GameRuleType.HORIZONTAL,GameRowType.THIRD);

				rows3.Cells.add(cell);
				if (cell.Column == 1) {
					if (columns1 == null)
						columns1 =new Combination(GameRuleType.VERTICAL, GameCellType.A);
					if (diagonal2 == null)
						diagonal2 =new Combination(GameRuleType.DIAGONAL, GameCellType.C);
					columns1.Cells.add(cell);
					diagonal2.Cells.add(cell);

				}
				if (cell.Column == 2) {
					if (columns2 == null)
						columns2 =new Combination(GameRuleType.VERTICAL, GameCellType.A);
					columns2.Cells.add(cell);
				}

				if (cell.Column == 3) {
					if (columns3 == null)
						columns3 =new Combination(GameRuleType.VERTICAL, GameCellType.C);
					if (diagonal1 == null)
						diagonal1 =new Combination(GameRuleType.DIAGONAL, GameCellType.A);
					columns3.Cells.add(cell);
					diagonal1.Cells.add(cell);
				}
			}
		}

		if(rows1 != null && rows1.Cells.size() > 0)
			comb.add(rows1);
		if(rows2 != null && rows2.Cells.size() > 0)
			comb.add(rows2);
		if(rows3 != null && rows3.Cells.size() > 0)	
			comb.add(rows3);
		if(columns1 != null && columns1.Cells.size() > 0)	
			comb.add(columns1);
		if(columns2 != null && columns2.Cells.size() > 0)	
			comb.add(columns2);
		if(columns3 != null && columns3.Cells.size() > 0)	
			comb.add(columns3);
		if(diagonal1 != null && diagonal1.Cells.size() > 0)	
			comb.add(diagonal1);
		if(diagonal2 != null && diagonal2.Cells.size() > 0)	
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
