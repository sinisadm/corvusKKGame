package KKGame;

import java.util.ArrayList;
import java.util.List;

public class Combination {
	public List<Cell> Cells = new ArrayList<Cell>();
	public int CombinationSize = 3;
	GameRuleType type = GameRuleType.HORIZONTAL;
	private GameRowType rowNo;
	private GameCellType colNo;
	private CombinationStatus potential;
	private CombinationStatus opositepotential;
	
	public Boolean isAchieved()
	{
		if(this.Cells.size() == this.CombinationSize)
			return true;
		else
			return false;
	}

	public Combination(GameRuleType type) {
		super();
		this.type = type;
	}

	public Combination(GameRuleType horizontal, GameRowType a) {
		this.type = horizontal;
		this.rowNo = a;
	}

	public Combination(GameRuleType vertical, GameCellType a) {
		this.type = vertical;
		this.colNo = a;
	}

	public Boolean checkCombination(Player player, Player player2)
	{
		switch (this.rowNo)
		{
			default:
				break;
			case FIRST:
				this.potential = this.checkRow(1, player);
				this.opositepotential = this.checkRow(1, player2);
				break;
			case SECOND:
				this.potential = this.checkRow(2, player);
				this.opositepotential = this.checkRow(2, player2);
				break;
			case THIRD:
				this.potential = this.checkRow(3, player);
				this.opositepotential = this.checkRow(3, player2);
				break;

		}
		switch (this.colNo)
		{
			default:
				break;
			case A:
				this.potential = this.checkCol(1, player);
				this.opositepotential = this.checkCol(1, player2);
				break;
			case B:
				this.potential = this.checkCol(2, player);
				this.opositepotential = this.checkCol(2, player2);
				break;
			case C:
				this.potential = this.checkCol(3, player);
				this.opositepotential = this.checkCol(3, player2);
				break;

		}
		
		if(this.potential == CombinationStatus.OCCUPIED)
			return false;
		else
			return true;
	}

	private CombinationStatus  checkRow(Integer id, Player player)
	{
		CombinationStatus status = CombinationStatus.EMPTY;
		Integer playerCount = 0;
		Boolean opposite = false;
		for(Cell cell : this.Cells)
		{
			if(cell.Row == id)
			{
				if(!cell.Player.equals(player))
				{
					opposite = true;
				}
				else
				{
					playerCount++;
				}
			}
		}
		if(opposite)
			return CombinationStatus.OCCUPIED;
		else if(playerCount == 0)
			return CombinationStatus.EMPTY;
		else if(playerCount.equals(1))
			return CombinationStatus.POTENTIAL;
		else if(playerCount.equals(2))
			return CombinationStatus.VERY_POTENTIAL;
		return status;
	}
	private CombinationStatus checkCol(Integer id, Player player)
	{
		CombinationStatus status = CombinationStatus.EMPTY;
		Integer playerCount = 0;
		Boolean opposite = false;
		for(Cell cell : this.Cells)
		{
			if(cell.Column == id)
			{
				if(!cell.Player.equals(player))
				{
					opposite = true;
				}
				else
				{
					playerCount++;
				}
			}
		}
		if(opposite)
			return CombinationStatus.OCCUPIED;
		else if(playerCount == 0)
			return CombinationStatus.EMPTY;
		else if(playerCount.equals(1))
			return CombinationStatus.POTENTIAL;
		else if(playerCount.equals(2))
			return CombinationStatus.VERY_POTENTIAL;
		return status;
	}
	
	
	public boolean IsPlayerTheWinner(Player player) {
		
		Boolean returnValue = false;
		Integer cnt = 0;
		
		if(this.isAchieved())
		{
			for(Cell cell : this.Cells)
			{
				if(cell.Player == player)
					cnt++;
			}
		}
		
		if(cnt.equals(this.CombinationSize))
		{	
			returnValue = true;
		}
		
		return returnValue;
	}
}
