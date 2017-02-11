package KKGame;

import java.util.ArrayList;
import java.util.List;

public class Combination {
	public List<Cell> Cells = new ArrayList<Cell>();
	public int CombinationSize = 3;
	GameRuleType type = GameRuleType.HORIZONTAL;
	private GameRowType rowNo;
	private GameCellType colNo;
	
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
