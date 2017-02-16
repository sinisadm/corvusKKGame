package KKGame;

import java.util.ArrayList;
import java.util.List;

public class Combination {
	public List<Cell> Cells = new ArrayList<Cell>();
	public int CombinationSize = 3;
	GameRuleType type = GameRuleType.HORIZONTAL;
	public GameRowType rowNo;
	public GameCellType colNo;
	private CombinationStatus potential;
	private CombinationStatus opositepotential;
	
	private List<Integer> combinationRowKeys;
	private List<Integer> combinationColumnKeys;

	public List<Integer> getCombinationColumnKeys() {
		return combinationColumnKeys;
	}

	public List<Integer> getCombinationRowKeys() {
		return combinationRowKeys;
	}

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
		System.out.println("Kreiram horizontalnu kombinaciju: +  gameRowType=" + a);
		this.CalculateKeys();
	}

	public Combination(GameRuleType vertical, GameCellType a) {
		this.type = vertical;
		this.colNo = a;
		if(vertical == GameRuleType.VERTICAL)
			System.out.println("Kreiram vertikalnu  kombinaciju:   gameColumnType=" + a);
		if(vertical == GameRuleType.DIAGONAL)
			System.out.println("Kreiram dijagonalnu kombinaciju:   gameColumnType=" + a);
		this.CalculateKeys();

	}

	public CombinationStatus checkCombination(Player player, Player player2)
	{
		if(this.rowNo != null)
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
		}
		if(this.colNo != null)
		{
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
		}
		return this.potential;
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
			status = CombinationStatus.OCCUPIED;
		else if(playerCount == 0)
			status = CombinationStatus.EMPTY;
		else if(playerCount.equals(1))
			status = CombinationStatus.POTENTIAL;
		else if(playerCount.equals(2))
			status = CombinationStatus.VERY_POTENTIAL;
		else if(this.Cells.size() < 3)
			status = CombinationStatus.HASFREE;
		
		System.out.println(status);
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
	
	public Boolean IsCordinatesInCombination(Integer row, Integer col)
	{
		for(Cell cell : this.Cells)
		{
			if(cell.Row.equals(row) && cell.Column.equals(col))
			{
				System.out.println("IsCordinatesInCombination" + row + "   " +  col);
				return true;
			}
		}
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
		
		if(cnt.equals(this.CombinationSize))
		{	
			returnValue = true;
		}
		
		return returnValue;
	}
	
	private List<Integer> AvailableCombonationRowKeys()
	{ 
		List<Integer> ret = new ArrayList<Integer>();
		switch(this.type)
		{
			case DIAGONAL:
				if(this.colNo != null)
				{
					switch(this.colNo)
					{
						case A:
						//	System.out.println( "DIAGONAL - A");
							ret.add(1);
							ret.add(2);
							ret.add(3);
							
							break;
						case C:
						//	System.out.println( "DIAGONAL - C");
							ret.add(3);
							ret.add(2);
							ret.add(1);	
							
							break;
						default:
							break;			
					}
				}

				break;
			case HORIZONTAL:
				// retci
				if(this.rowNo != null)
				{
					//System.out.println( "HORIZONTAL - " + this.rowNo);
					ret.add(1);
					ret.add(2);
					ret.add(3);
				}
	
				break;
			case VERTICAL:
				// kolone
				
				if(this.rowNo != null)
				{
					if(this.rowNo != null)
					{
						switch(this.rowNo)
						{
							case FIRST:
							//	System.out.println( "VERTICAL - A");
								ret.add(1);
								ret.add(1);
								ret.add(1);
								
								break;
							case SECOND:
							//	System.out.println( "VERTICAL - B" );
								ret.add(2);
								ret.add(2);
								ret.add(2);	
								
								break;
							case THIRD:
							//	System.out.println( "VERTICAL	 - C" );
								ret.add(3);
								ret.add(3);
								ret.add(3);	
								
								break;
							default:
								break;			
						}
					}
				}
		
		}
		
		return ret;		
	}

	private List<Integer> AvailableCombonationColumnKeys()
	{ 

		List<Integer> ret = new ArrayList<Integer>();
		switch(this.type)
		{
			case DIAGONAL:
				switch(this.colNo)
				{
					case A:
					//	System.out.println( "Diagonal	 - " + this.colNo );
						ret.add(3);
						ret.add(2);
						ret.add(1);	
						
						break;
					case C:
					//	System.out.println( "Diagonal	 - " + this.colNo );
						ret.add(1);
						ret.add(2);
						ret.add(3);
						
						break;
					default:
						break;			
				}

				break;
			case HORIZONTAL:
				// kolone
				
				switch(this.rowNo)
				{
					case FIRST:
					//	System.out.println( "HORIZONTAL	 - " + this.rowNo );
						ret.add(1);
						ret.add(1);
						ret.add(1);
						
						break;
					case SECOND:
					//	System.out.println( "HORIZONTAL	 - " + this.rowNo );
						ret.add(2);
						ret.add(2);
						ret.add(2);	
						
						break;
					case THIRD:
					//	System.out.println( "HORIZONTAL	 - " + this.rowNo );
						ret.add(3);
						ret.add(3);
						ret.add(3);	
						
						break;
					default:
						break;			
				}
	
				break;
			case VERTICAL:
				// retci
			//	System.out.println( "VERTICAL	 - " + this.colNo );
						ret.add(1);
						ret.add(2);
						ret.add(3);

				break;
			default:
				break;	
		}
		
		return ret;		
	}
	
	private void CalculateKeys()
	{
		this.combinationRowKeys = this.AvailableCombonationRowKeys();
		this.combinationColumnKeys = this.AvailableCombonationColumnKeys();
	}

	
	public List<Cell> GeneratePossibleMoves() {
		this.CalculateKeys();
		List<Cell> ret = new ArrayList<Cell>();
		
		for(int a = 0; a <= 2; a++)
		{
			Boolean rowKeyExists = this.combinationRowKeys.size() <= a;
			
			System.out.println("rowKeyExists : " + a + " - " + rowKeyExists.toString());
			Boolean rowColumnExists = this.combinationColumnKeys.size() <= a;
			System.out.println("rowColumnExists" + a + " - " + rowColumnExists.toString());
			if(		rowKeyExists 
					&&  rowColumnExists 
					&& !IsCordinatesInCombination(this.combinationRowKeys.get(a), this.combinationColumnKeys.get(a)))
			{
				ret.add(new Cell(this.combinationRowKeys.get(a), this.combinationColumnKeys.get(a)));
			}
		}
		return ret;
	}

	public Cell pickAnyCell() {		

		System.out.println("pick ANY CEKK");
		List<Cell> comb = this.GeneratePossibleMoves();

		System.out.println("vračam  " + comb);
		if(comb.size() == 0)
		{
			return null;
		}
		else if(comb.size() == 1)
		{

			System.out.println("vračam  " + comb);
			return comb.get(0);
			
		}
		else
		{
			int key = (int)( Math.random() * comb.size() + 1);
			
			System.out.println("generoiram key " + key);
			return comb.get(key);
		}
				
	}
}
