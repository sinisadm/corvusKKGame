package KKGame;

import java.util.ArrayList;
import java.util.List;

public class Combination {
	public List<Cell> Cells = new ArrayList<Cell>();
	public int CombinationSize = 3;
	
	public Boolean isAchieved()
	{
		if(this.Cells.size() == this.CombinationSize)
			return true;
		else
			return false;
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
		
		if(cnt == this.CombinationSize)
		{	
			returnValue = true;
		}
		
		return returnValue;
	}
}
