package KKGame;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
		return computerPotential;
	}

	/**
	 * @param potential the potential to set
	 */
	public void setPotential(CombinationStatus potential) {
		this.computerPotential = potential;
	}

	/**
	 * @return the opositePotential
	 */
	public CombinationStatus getOpositePotential() {
		return playerPotential;
	}

	/**
	 * @param opositePotential the opositePotential to set
	 */
	public void setOpositePotential(CombinationStatus opositePotential) {
		this.playerPotential = opositePotential;
	}

	private CombinationStatus computerPotential;
	private CombinationStatus playerPotential;
	
	private List<CellCoordinates> combinationCoordinates;




	public List<CellCoordinates> getCombinationCoordinates() {
		return combinationCoordinates;
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

	public CellCoordinates checkCombination(Player player, Player player2)
	{ 
		CellCoordinates returnValue = calculatePotentialMovesForCombination(player, player2);
		//if()
	/*	if(this.rowNo != null)
		{
			switch (this.rowNo)
			{
				default:
					break;
				case FIRST:
					returnValue = calculatePotentialMovesForCombination(player, player2);
					break;
				case SECOND:
					this.potential = this.checkCombinationStatus(2, player);
					this.opponentPotential = this.checkCombinationStatus(2, player2);
					break;
				case THIRD:
					this.potential = this.checkCombinationStatus(3, player);
					this.opponentPotential = this.checkCombinationStatus(3, player2);
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
					this.opponentPotential = this.checkCol(1, player2);
					break;
				case B:
					this.potential = this.checkCol(2, player);
					this.opponentPotential = this.checkCol(2, player2);
					break;
				case C:
					this.potential = this.checkCol(3, player);
					this.opponentPotential = this.checkCol(3, player2);
					break;
	
			}
		}*/
		return returnValue;
	}

	/**
	 * @param player
	 * @param player2
	 * @return 
	 */
	private CellCoordinates calculatePotentialMovesForCombination(Player player, Player player2) {

		this.CalculateKeys();
		this.computerPotential = this.checkCombinationStatus(player, player2);
		this.playerPotential = this.checkCombinationStatus(player2, player);
		
		if(this.computerPotential == CombinationStatus.VERY_POTENTIAL || this.computerPotential == CombinationStatus.MUST_DEFFEND)
		{
			System.out.println("Very potential schema");
			System.out.println(this.type + " " + this.rowNo + " " + this.colNo);
			return this.lastFreeCellInCombination();
		}


		else if(this.computerPotential == CombinationStatus.POTENTIAL)
		{
			System.out.println("Potential schema");
			System.out.println(this.type + " " + this.rowNo + " " + this.colNo);
			// Get cell 2 make more combinations
			return this.lastFreeCellInCombination();
		
		}
		
		return null;
	}

	private	CellCoordinates lastFreeCellInCombination() {
		for(CellCoordinates cor: this.combinationCoordinates){

		//	Long potentialIndex =  this.Cells.stream().filter(c -> c.Player.equals(player)).collect(Collectors.counting());
			
			if(this.Cells.stream()
					.filter(	c -> c.Row.equals(cor.getX()) && c.Column.equals(cor.getY())	)
					.collect(Collectors.counting()) == 0){
				CellCoordinates cordinates = new CellCoordinates(cor.getX(), cor.getY());

				System.out.println("Very potential schema key");
				System.out.println(cordinates);
				return cordinates;
				}
		}
		return null;
	}/*
	private ArrayList<ArrayList<Integer>> freeCellsInCombination() {
		ArrayList<ArrayList<Integer>> listOLists = new ArrayList<ArrayList<Integer>>();

		for(int a = 0; a < 3; a++){
			int x = this.combinationRowKeys.get(a);
			int y = this.combinationColumnKeys.get(a);
			

		//	Long potentialIndex =  this.Cells.stream().filter(c -> c.Player.equals(player)).collect(Collectors.counting());
			
			
			if(this.Cells
					.stream()
					.filter(
							c -> c.Row.equals(x) 
							&& c.Column.equals(y)
							)
					.collect(
							Collectors.counting()
							 ) == 0)
			{
				ArrayList<Integer> singleList = new ArrayList<Integer>();
				singleList.add(x);
				singleList.add(y);
				listOLists.add( singleList);
			}
		}
		return null;
	}
*/
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
	
	public List<CellCoordinates> GeneratePossibleMoves() {
		this.CalculateKeys();
		List<CellCoordinates> ret = new ArrayList<CellCoordinates>();
		Boolean keyExists;

		
		if(this.type == GameRuleType.HORIZONTAL){
			System.out.println("Horizontal rule:");
			System.out.println("Horizontal rule:" + this.combinationCoordinates + "   ");
			System.out.println();
			System.out.println();
			for(CellCoordinates localPos : combinationCoordinates)
			{
				keyExists = this.getCells().stream().filter(p -> p.Row.equals(localPos)).findFirst().isPresent();
				System.out.println("");
				
				
				if(	 keyExists 	&& !IsCordinatesInCombination(localPos.getX(), localPos.getY()))
				{
					ret.add(localPos);
				}
			}
		} else {

			System.out.println("Vertical or Diagonal rule:");
			System.out.println(this.combinationCoordinates + "   ");
			System.out.println();
			System.out.println();
			for(CellCoordinates localPos : combinationCoordinates)
			{
				keyExists = this.getCells().stream().filter(p -> p.Column.equals(localPos.getY())).findFirst().isPresent();
				if(	 keyExists 	&& !IsCordinatesInCombination(localPos.getX() - 1, localPos.getY() - 1))
				{
					ret.add(localPos);
				}
			}
		}
			 System.out.println("return combination list: Size of the list: " + ret.size());
		return ret;
	}

	public CellCoordinates pickAnyCell() {		

		System.out.println("pick ANY CEKK");
		List<CellCoordinates> comb = this.GeneratePossibleMoves();

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
		System.out.println("combinationRowKeys: " + this.combinationCoordinates);

		System.out.println("potential: " + this.computerPotential);
		System.out.println("opositepotential: " + this.playerPotential);
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

	private List<CellCoordinates> AvailableCombonationCoordinates()
	{ 

		List<CellCoordinates> ret = new ArrayList<CellCoordinates>();
		switch(this.type)
		{
			case DIAGONAL:
				switch(this.colNo)
				{
					case A:
					//	System.out.println( "Diagonal	 - " + this.colNo );
						ret.add(new CellCoordinates(3, 1));
						ret.add(new CellCoordinates(2, 2));
						ret.add(new CellCoordinates(1, 3));
						
						break;
					case C:
					//	System.out.println( "Diagonal	 - " + this.colNo );
						ret.add(new CellCoordinates(1, 3));
						ret.add(new CellCoordinates(2, 2));
						ret.add(new CellCoordinates(3, 1));
						
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
						ret.add(new CellCoordinates(1, 1));
						ret.add(new CellCoordinates(1, 2));
						ret.add(new CellCoordinates(1, 3));
						
						break;
					case SECOND:
					//	System.out.println( "HORIZONTAL	 - " + this.rowNo );
						ret.add(new CellCoordinates(2, 1));
						ret.add(new CellCoordinates(2, 2));
						ret.add(new CellCoordinates(2, 3));
						
						break;
					case THIRD:
					//	System.out.println( "HORIZONTAL	 - " + this.rowNo );
						ret.add(new CellCoordinates(3, 1));
						ret.add(new CellCoordinates(3, 2));
						ret.add(new CellCoordinates(3, 3));
						break;
					default:
						break;			
				}
	
				break;
			case VERTICAL:
				// retci
				switch(this.colNo)
				{
					case A:
					//	System.out.println( "Diagonal	 - " + this.colNo );
						ret.add(new CellCoordinates(1, 1));
						ret.add(new CellCoordinates(2, 1));
						ret.add(new CellCoordinates(3, 1));
						
						break;
						
					case B:
					//	System.out.println( "Diagonal	 - " + this.colNo );
						ret.add(new CellCoordinates(1, 2));
						ret.add(new CellCoordinates(2, 2));
						ret.add(new CellCoordinates(3, 2));
						
						break;
						
					case C:
					//	System.out.println( "Diagonal	 - " + this.colNo );
						ret.add(new CellCoordinates(1, 3));
						ret.add(new CellCoordinates(2, 3));
						ret.add(new CellCoordinates(3, 3));
						
						break;

				}
				break;
		}
		
		return ret;		
	}
	
	private void CalculateKeys()
	{
		this.combinationCoordinates = this.AvailableCombonationCoordinates();
	}


	/**
	 * Check row/coil methods  
	 * 
	 * Returns combination status identified by combination row or column
	 * @param player2 
	 * 
	 */
	private CombinationStatus  checkCombinationStatus(Player player, Player player2)
	{
		CombinationStatus status = CombinationStatus.EMPTY;

		Long potentialIndex =  this.Cells.stream().filter(c -> c.Player.equals(player)).collect(Collectors.counting());
		Long opponentpotentialIndex    =  this.Cells.stream().filter(c -> c.Player.equals(player2)).collect(Collectors.counting());
		
		if(potentialIndex == 0 && opponentpotentialIndex == 0)
			status = CombinationStatus.EMPTY;
		else if(potentialIndex == 1 && opponentpotentialIndex == 0)
			status = CombinationStatus.POTENTIAL;
		else if(potentialIndex == 2 && opponentpotentialIndex == 0)
			status = CombinationStatus.VERY_POTENTIAL;
		else if(potentialIndex == 0 && opponentpotentialIndex == 1)
			status = CombinationStatus.OCCUPIED;
		else if(potentialIndex == 0 && opponentpotentialIndex == 2)
			status = CombinationStatus.MUST_DEFFEND;

		return status;
	}/*
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
*/
	public Cell getRemainingCell(Game game) {
		Cell cell = null;
		CalculateKeys();
		System.out.println(" Get remaining cell : " + this.combinationCoordinates + "    -    ");
		for(Cell c : this.Cells)
			System.out.println(c.Row + " - " + c.Column + " postoji u ugri");
		for(CellCoordinates cellc : this.combinationCoordinates)
		{
			cell = new Cell(cellc.getX(), cellc.getY(), game.GameId, game.CurrentPlayer);
			if(!game.checkIfCellsCantBeAdded(cell))
			{ 
				System.out.println("Combination: " + this.type + " - " + this.rowNo + " - " + this.colNo);
				System.out.println("Remaining cell: " + cell.Row + " - " + cell.Column);
				return cell;
			}
		}	
			
		return null;
	}

	public void reset() {
		this.Cells.clear();
		this.computerPotential = null;
		this.playerPotential = null;
		
	}

}
