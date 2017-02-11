package KKGame;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public final String Name;
    
    public Integer Wins;
    public Integer Lose;
    public Integer Draw;
    
    private Double _successProcent;

	private ArrayList<Cell> _own;

	private ArrayList<Cell> _oponent;
    
    private void _setSuccessProcent()
    {
    	Integer gamesPlayed = this.Wins + this.Lose + this.Draw;
    	this._successProcent = (double)this.Wins / gamesPlayed;
    }


    public Player(String name)
	{
    	this.Name = name;
	    this.Wins = 0;
	    this.Lose = 0;
	    this.Draw = 0;
	}


    public String toString() {
        return this.Name;
    }


	public void Winn() {
		this.Wins++;
		
	}

	public void Lose() {
		this.Lose++;
		
	}

	public void Draw() {
		this.Draw++;
		
	}


	public void MakeMove(Game game) {
		// get Own Troops
		// get Opponents Troops
		// get SkillLevel
		this._setSuccessProcent();
		this._own = new ArrayList<Cell>();
		this._oponent = new ArrayList<Cell>();
		
		Player computer = game.CurrentPlayer;
		
		_getTroops(game, this._own, this._oponent, computer);
		_getFreeFields(game);
		_analyzeTroops();
		_makeDecision();
		this._move();
		
	}


	private List<Cell> _getFreeFields(Game game) {
		
		List<Cell> free = new ArrayList<Cell>();
		Integer row = 0;
		Integer col = 0;
		for(GameRowType rowType : GameRowType.values())
		{
			for(GameCellType colType : GameCellType.values() )
			{
				Cell cell = new Cell(row, col, game.GameId, game.CurrentPlayer);
				if(!game.checkIfCellsCantBeAdded(cell))
					free.add(cell);
			}
		}
		return free;
		
	}


	private void _makeDecision() {
		// TODO Auto-generated method stub
		
	}


	private void _analyzeTroops() {
		// TODO Auto-generated method stub
		
	}


	/**
	 * @param game
	 * @param own - own troops
	 * @param oponent - op. troops
	 * 
	 * @param computer
	 */
	private void _getTroops(Game game, List<Cell> own, List<Cell> oponent, Player computer) {
		for(Cell cell : game.Game)
		{
			if(cell.Player.equals(computer))
				own.add(cell);
			else
				oponent.add(cell);
		}
	}


	private void _move() {
		// TODO Auto-generated method stub
		
	}


	

}
