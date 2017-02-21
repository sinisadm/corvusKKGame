package KKGame;

import java.util.ArrayList;
import java.util.List;

public class Game {

	/*
	 * 	Class Game
	 * 
	 * 	methods
	 * 		
	 * 		Play
	 * 		_fillCells2Combination
	 * 		Analyze available combinations
	 * 			method returns list of lists of integers who represents coordinates 
	 * 		Make Decision
	 * 
	 * 
	 * */

	private Combination rowFIRST 	= new Combination(GameRuleType.HORIZONTAL , GameRowType.FIRST);
	private Combination rowSECOND 	= new Combination(GameRuleType.HORIZONTAL , GameRowType.SECOND);
	private Combination rowTHIRD 	= new Combination(GameRuleType.HORIZONTAL , GameRowType.THIRD);
	private Combination columnA 	= new Combination(GameRuleType.VERTICAL , GameCellType.A);
	private Combination columnB 	= new Combination(GameRuleType.VERTICAL , GameCellType.B);
	private Combination columnC 	= new Combination(GameRuleType.VERTICAL ,  GameCellType.C);
	private Combination diagonalA 	= new Combination(GameRuleType.DIAGONAL,  GameCellType.A);
	private Combination diagonalC 	= new Combination(GameRuleType.DIAGONAL ,  GameCellType.C);
	
	public Integer GameId;

/*	public Integer getId() {
		return GameId;
	}
	*/
	public final Player Player1;
	public final Player Player2;
	public GameStatus Status;
	public Player Winner;
	
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

		if (!limit)
		{	
			if(!cantBeAdded)
			{
				this.Game.add(cell);
				System.out.println("Added: " + cell.toString() );
				for(Cell c : this.Game)
					System.out.println(c.Row + " - " + c.Column + " : " + c.Player.Name);
				this._gameInProggress();
			//	
				isGameOver = this._isSomeoneWin();	

				//System.out.println("Game Is Over : " + isGameOver.toString() );

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
			if(this.Winner != null && this.Winner == this.Player1)		
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
//		System.out.println("Game played, Id: " + this.GameId + "\\n Prvi igrač: " + this.Player1.Name + ", drugi igrač: " + this.Player2.Name + "\\n polja: " + size );	
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
	/*
	private Player _getCurrentPlayer()
	{
		return (this.CurrentPlayer == this.Player1) ? this.Player1 : this.Player2;
	}*/
	private Boolean _checkCellsLimit() {
	
		return (this.Game.size() >= 9) ? true : false;
	}
	
	public Boolean checkIfCellsCantBeAdded( final Cell cell) {
		Boolean cantBeAdded = false;
		if(this.Game.size()> 0 && cell != null){
		/*	for(Cell c : this.Game)
			{
				
			//	System.out.println("Cell :. " + c.Row + " , "  + c.Column + ", Player: " + c.Player.Name);
			}*/

			cantBeAdded = this.Game.stream().filter(c -> c.getRow().equals(cell.getRow()) && c.getColumn().equals(cell.getColumn())).findFirst().isPresent();
			System.out.println("Can't be added: " + cell.Row + " - "  + cell.Column + ":  " + cantBeAdded.toString());
		}

		System.out.println();

	    return cantBeAdded;
	}

	private Boolean _isSomeoneWin() {
		Boolean returnValue = false;
		Boolean filled = this.Game.size() == 9;
		Boolean winn = false;
		
		List<Combination> comb = _fillCells2Combination();
		Combination combination = null;
		
		for(Combination c : comb)
		{
			combination = c;
			if(combination != null)
			{	
				if(combination.IsPlayerTheWinner(this.CurrentPlayer))
				{
					winn = true;
					this.Winner = this.CurrentPlayer;
					this._gameFinished();
				}
			}
		}
		
		if(winn)
		{	
			returnValue = true;
			this.CurrentPlayer.Winn();
			this.GetOppositePlayer().Lose();
			_gameFinished();
		}
		else if(filled && !winn)
		{
			returnValue = false;
			this.CurrentPlayer.Draw();;
			this.GetOppositePlayer().Draw();;
			_gameFinished();
		}
		else
		{	
			this._switchPlayers();
			if(this.CurrentPlayer.Name.equals("computer"))
				this._switchPlayers();
		}
			return returnValue;
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
		if(this.Winner != null) { /* todo   */

			System.out.println("Igra gotova, status:  " + this.Status);
			this.Status = GameStatus.Finished;

		}
	}

	private void _switchPlayers() {

		//System.out.println("Trenutni igrač:  " + this.CurrentPlayer.toString());
		this.CurrentPlayer = (this.Player1 == this.CurrentPlayer ) ? this.Player2 : this.Player1;
		//System.out.println("nakon zamjene je igrač:  " + this.CurrentPlayer.toString());
		if(this.CurrentPlayer.toString().equals("computer"))
		{
			System.out.println("Computer move ******************************************");			
			this.makeComputerMove();
		}
	}


	public void makeComputerMove() {
			this.Status = this.Play(this.CurrentPlayer.computer.Hint(this, ComputerSkill.MEDIUM));
	}


	public List<Combination> _fillCells2Combination() {
		resetCombinations();
		fillCells();
		return extractPotentialCombinations();
	}

	/**
	 * 
	 */
	private void fillCells() {
		for (Cell cell : this.Game) {
			if (cell.Row == 1) {
				this.rowFIRST.addCell2CombinationCells(cell);
				if (cell.Column == 1) {
					this.columnA.addCell2CombinationCells(cell);
					this.diagonalA.addCell2CombinationCells(cell);
				}

				if (cell.Column == 2) {
					this.columnB.addCell2CombinationCells(cell);
				}

				if (cell.Column == 3) {
					this.columnC.addCell2CombinationCells(cell);
					this.diagonalC.addCell2CombinationCells(cell);
				}
			}
			if (cell.Row == 2) {
				this.rowSECOND.addCell2CombinationCells(cell);
				if (cell.Column == 1) {
					this.columnA.addCell2CombinationCells(cell);
				}
				if (cell.Column == 2) {
					this.columnB.addCell2CombinationCells(cell);
					this.diagonalA.addCell2CombinationCells(cell);
					this.diagonalC.addCell2CombinationCells(cell);
				}
				if (cell.Column == 3) {
					this.columnC.addCell2CombinationCells(cell);
				}

			}
			if (cell.Row == 3) {
				this.rowTHIRD.addCell2CombinationCells(cell);
				if (cell.Column == 1) {
					this.columnA.addCell2CombinationCells(cell);
					this.diagonalC.addCell2CombinationCells(cell);
				}
				
				if (cell.Column == 2) {
					this.columnB.addCell2CombinationCells(cell);
				}

				if (cell.Column == 3) {
					this.columnC.addCell2CombinationCells(cell);
					this.diagonalA.addCell2CombinationCells(cell);
				}
			}
		}
	}

	/**
	 * 
	 */
	private void resetCombinations() {
		this.rowFIRST.reset();
		this.rowSECOND.reset();
		this.rowTHIRD.reset();
		this.columnA.reset();
		this.columnB.reset();
		this.columnC.reset();
		this.diagonalA.reset();
		this.diagonalC.reset();
	}

	/**
	 * 
	 */
	void analyzeCombinations(Player player1, Player player2) {
		this.rowFIRST.checkCombination(player1, player2);
		this.rowSECOND.checkCombination(player1, player2);
		this.rowTHIRD.checkCombination(player1, player2);
		this.columnA.checkCombination(player1, player2);
		this.columnB.checkCombination(player1, player2);
		this.columnC.checkCombination(player1, player2);
		this.diagonalA.checkCombination(player1, player2);
		this.diagonalC.checkCombination(player1, player2);
	}

	/**
	 * @return
	 */
	private List<Combination> extractPotentialCombinations() {
		List<Combination> comb = new ArrayList<Combination>();
		if(this.rowFIRST.getCells().size() > 0)
			comb.add(this.rowFIRST);
		if(this.rowSECOND.getCells().size() > 0)
			comb.add(this.rowSECOND);
		if(this.rowTHIRD.getCells().size() > 0)	
			comb.add(this.rowTHIRD);
		if(this.columnA.getCells().size() > 0)	
			comb.add(this.columnA);
		if(this.columnB.getCells().size() > 0)	
			comb.add(this.columnB);
		if(this.columnC.getCells().size() > 0)	
			comb.add(this.columnC);
		if(this.diagonalA.getCells().size() > 0)	
			comb.add(this.diagonalA);
		if(this.diagonalC.getCells().size() > 0)	
			comb.add(this.diagonalC);
		return comb;
	}
}
