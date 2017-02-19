package KKGame;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Combination {
	
	public GameRuleType type = GameRuleType.HORIZONTAL;
	private List<Cell> Cells = new ArrayList<Cell>();
	public List<Cell> getCells() {
		return Cells;
	}
	
	public Boolean addCell2CombinationCells(Cell cell) {
		Boolean cantBeAdded = this.getCells().stream().filter(c -> c.getRow().equals(cell.getRow()) && c.getColumn().equals(cell.getColumn())).findFirst().isPresent();
		if(!cantBeAdded) {
			Cells.add(cell);
			CurrentSize = Cells.size();
			return true;
		}
		else
			return false;
	}
	
	public Player AllCapacitiOwnByOnePlayer;
	
	public void setAllCapacitiOwnByOnePlayer(Player player) {
		
		Predicate<Cell> allBelongs2SamePlayer =  e -> e.Player .equals(player);
		Boolean ownAll = this.Cells.stream().allMatch(allBelongs2SamePlayer);
		if(ownAll)
			AllCapacitiOwnByOnePlayer = player;
	}

	public int CombinationSize = 3;
	
	public int CurrentSize = 0;
	
	public GameRowType rowNo;
	
	public GameCellType colNo;
	
	/**
	 * @return the potential
	 */
	public CombinationStatus getPotential() {
		return potential;
	}

	/**
	 * @param potential the potential to set
	 */
	public void setPotential(CombinationStatus potential) {
		this.potential = potential;
	}

	/**
	 * @return the opositePotential
	 */
	public CombinationStatus getOpositePotential() {
		return opositePotential;
	}

	/**
	 * @param opositePotential the opositePotential to set
	 */
	public void setOpositePotential(CombinationStatus opositePotential) {
		this.opositePotential = opositePotential;
	}

	private CombinationStatus potential;
	private CombinationStatus opositePotential;
	
	private List<Integer> combinationRowKeys;
	private List<Integer> combinationColumnKeys;

	public List<Integer> getCombinationColumnKeys() {
		return combinationColumnKeys;
	}

	public List<Integer> getCombinationRowKeys() {
		return combinationRowKeys;
	}

	/**
	 * isAchieved method
	 *
	 * 		Check if combination capacity is full
	 * 
	 * 
	 */	
	public Boolean isAchieved()
	{
		if(this.Cells.size() == this.CombinationSize)
			return true;
		else
			return false;
	}

	/**
	 * Constructors
	 */
	public Combination(GameRuleType type) {
		super();
		this.type = type;
	}

	public Combination(GameRuleType horizontal, GameRowType a) {
		this.type = horizontal;
		this.rowNo = a;
	//	System.out.println("Kreiram horizontalnu kombinaciju: +  gameRowType=" + a);
		this.CalculateKeys();
	}

	public Combination(GameRuleType vertical, GameCellType a) {
		this.type = vertical;
		this.colNo = a;
		if(vertical == GameRuleType.VERTICAL)
	//		System.out.println("Kreiram vertikalnu  kombinaciju:   gameColumnType=" + a);
		if(vertical == GameRuleType.DIAGONAL)
	//		System.out.println("Kreiram dijagonalnu kombinaciju:   gameColumnType=" + a);
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
					this.opositePotential = this.checkRow(1, player2);
					break;
				case SECOND:
					this.potential = this.checkRow(2, player);
					this.opositePotential = this.checkRow(2, player2);
					break;
				case THIRD:
					this.potential = this.checkRow(3, player);
					this.opositePotential = this.checkRow(3, player2);
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
					this.opositePotential = this.checkCol(1, player2);
					break;
				case B:
					this.potential = this.checkCol(2, player);
					this.opositePotential = this.checkCol(2, player2);
					break;
				case C:
					this.potential = this.checkCol(3, player);
					this.opositePotential = this.checkCol(3, player2);
					break;
	
			}
		}
		return this.potential;
	}

	/**
	 * IsCordinatesInCombination method
	 * 
	 * 		@Integer row
	 *		@Integer column
	 *
	 * 		Check if coordinates belongs into combination, identified by row and column
	 * 
	 * 
	 */
	public Boolean IsCordinatesInCombination(Integer row, Integer column)
	{
		for(Cell cell : this.Cells)
		{
			if(cell.Row.equals(row) && cell.Column.equals(column))
			{
				System.out.println("IsCordinatesInCombination" + row + "   " +  column);
				return true;
			}
		}
		return false;
	}
	
	public List<Cell> GeneratePossibleMoves() {
		this.CalculateKeys();
		List<Cell> ret = new ArrayList<Cell>();
		Boolean keyExists;

		
		if(this.type == GameRuleType.HORIZONTAL){
			System.out.println("Horizontal rule:");
			System.out.println("Horizontal rule:" + this.combinationRowKeys + "   " + this.combinationColumnKeys);
			System.out.println();
			System.out.println();
			for(Integer localPos : combinationRowKeys)
			{
				keyExists = this.getCells().stream().filter(p -> p.Row.equals(localPos)).findFirst().isPresent();
				System.out.println("");
				
				
				if(	 keyExists 	&& !IsCordinatesInCombination(this.combinationRowKeys.get(localPos), this.combinationColumnKeys.get(localPos)))
				{
					ret.add(new Cell(this.combinationRowKeys.get(localPos ), this.combinationColumnKeys.get(localPos)));
				}
			}
		} else {

			System.out.println("Vertical or Diagonal rule:");
			System.out.println(this.combinationRowKeys + "   " + this.combinationColumnKeys);
			System.out.println();
			System.out.println();
			for(Integer localPos : combinationColumnKeys)
			{
				keyExists = this.getCells().stream().filter(p -> p.Column.equals(localPos - 1)).findFirst().isPresent();
				if(	 keyExists 	&& !IsCordinatesInCombination(this.combinationRowKeys.get(localPos - 1), this.combinationColumnKeys.get(localPos - 1)))
				{
					ret.add(new Cell(this.combinationRowKeys.get(localPos - 1), this.combinationColumnKeys.get(localPos - 1)));
				}
			}
		}
			 System.out.println("return combination list: Size of the list: " + ret.size());
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

	public void OutputAll() {
		this.CalculateKeys();
		System.out.println("KOmbinacija:" + this.type + ", rowNo: " + this.rowNo + ", columnNo: " + this.colNo);
		
		for(Cell c : this.Cells){
			System.out.println(c.Player.Name + ": "+ c.Row + " - " + c.Column);
		}
		System.out.println("combinationRowKeys: " + this.combinationRowKeys);
		System.out.println("combinationColumnKeys: " + this.combinationColumnKeys);
		System.out.println("potential: " + this.potential);
		System.out.println("opositepotential: " + this.opositePotential);
			System.out.println("");
			System.out.println("");/**/
	}

	/**
	 * IsPlayerTheWinner method
	 * 
	 * 		@Player player
	 *
	 * 		Check if given player wins the game
	 * 
	 * 
	 */	
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
	
	
	private void setCells(List<Cell> cells) {
		Cells = cells;
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
				
				if(this.colNo != null)
				{
					if(this.colNo != null)
					{
						switch(this.colNo)
						{
							case A:
							//	System.out.println( "VERTICAL - A");
								ret.add(1);
								ret.add(1);
								ret.add(1);
								
								break;
							case B:
							//	System.out.println( "VERTICAL - B" );
								ret.add(2);
								ret.add(2);
								ret.add(2);	
								
								break;
							case C:
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


	/**
	 * Check row/col methods
	 * 
	 * Returns combination status identified by combination row or column
	 * 
	 */
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
					this.opositePotential = CombinationStatus.POTENTIAL;
				}
				else
				{
					playerCount++;
				}
				
			}
			//	System.out.println("Kombinacija : " + cell.Row + ", " + cell.Column + ", oznake ");
		}
		if(playerCount == 0)
			status = CombinationStatus.EMPTY;
		else if(playerCount.equals(1))
			status = CombinationStatus.POTENTIAL;
		else if(playerCount.equals(2))
			status = CombinationStatus.VERY_POTENTIAL;
		else if(this.Cells.size() < 3)
			status = CombinationStatus.HASFREE;
		
		//System.out.println(status);
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

	public Cell getRemainingCell(Game game) {
		Cell cell = null;
		CalculateKeys();
		System.out.println(" Get remaining cell : " + this.combinationRowKeys + "    -    " + this.combinationColumnKeys);
		for(Cell c : this.Cells)
			System.out.println(c.Row + " - " + c.Column + " postoji u ugri");
		for(Integer i = 0; i < this.combinationRowKeys.size(); i++)
		{
			cell = new Cell(this.combinationRowKeys.get(i), this.combinationColumnKeys.get(i), game.GameId, game.CurrentPlayer);
			if(!game.checkIfCellsCantBeAdded(cell))
			{ 
				System.out.println("Combination: " + this.type + " - " + this.rowNo + " - " + this.colNo);
				System.out.println("Remaining cell: " + cell.Row + " - " + cell.Column);
				return cell;
			}
		}	
			
		return null;
	}

}
