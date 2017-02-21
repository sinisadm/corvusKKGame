package KKGame;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GameComputerPlayer {
	
	private Integer gameStage;
	private Integer gameStrategy;
	private Game game;
	private Combination LastUsedCombination = null;
	private List<Combination> computerCombinationsList = new ArrayList<Combination>();
	private List<Combination> playerCombinationsList = new ArrayList<Combination>();
	private List<Combination> computerVeryPotentialList = new ArrayList<Combination>();
	private List<Combination> playerVeryPotentialList = new ArrayList<Combination>();
	private List<Combination> computerPotentialList = new ArrayList<Combination>();
	private List<Combination> playerPotentialList = new ArrayList<Combination>();
	
	
	public Cell Hint(Game game, ComputerSkill skill)
	{
		this.game = game;
		this.gameStage = game.Game.size();
		this.computerCombinationsList.clear();
		
		this.playerCombinationsList.clear();
		this.fillAllCombinations();
		Cell cell = null;
		switch(this.gameStage)
		{
			case 0:
				cell = this.take5(this.game.CurrentPlayer, this.game.GameId);
				if(cell != null)
					return cell;
				else
				// Random cell, Take 5

				System.out.println( "================================");
				System.out.println( "");
				System.out.println( "First move : Take5 or Random cell");
				System.out.println( "");
				System.out.println( "================================");
				return this.randomCell(this.game.CurrentPlayer, this.game.GameId);
			
			
			case 1:
				System.out.println( "================================");
				System.out.println( "");
				System.out.println( "Second  move : Take5 or whatICan");
				System.out.println( "");
				System.out.println( "================================");
				cell = this.take5(this.game.CurrentPlayer, this.game.GameId);
				if(cell != null)
				{
					return cell;
				}
				else		
				{	
					cell = extraxtCellFromLastCombination(game, cell);
				}
				if(cell != null)
					return cell;
				if(cell == null)
					return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			
			
			case 2:
				System.out.println( "================================");
				System.out.println( "");
				System.out.println( "Third move : whatICan");
				System.out.println( "");
				System.out.println( "================================");
/*
				this.LastUsedCombination = this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
				cell = null;
				if(this.LastUsedCombination != null)
					cell = this.LastUsedCombination.getRemainingCell(game);
				//  Defense, Any combination, Take 5, 
				return (cell != null) ? cell : this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			*/
				cell = this.take5(this.game.CurrentPlayer, this.game.GameId);
				if(cell != null)
				{
					return cell;
				}
				else		
				{	
					cell = extraxtCellFromLastCombination(game, cell);
				}
				if(cell != null)
					return cell;
				if(cell == null)
					return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			
			
			case 3:
				// Defense, Any combination, Take 5
				System.out.println( "================================");
				System.out.println( "");
				System.out.println( "Fourth move : whatICan");
				System.out.println( "");
				System.out.println( "================================");
/*
				this.LastUsedCombination = this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
				cell = null;
				if(this.LastUsedCombination != null)
					cell = this.LastUsedCombination.getRemainingCell(game);
				//  Defense, Any combination, Take 5, 
				return (cell != null) ? cell : this.whatICan(this.game.CurrentPlayer, this.game.GameId);
		*/
				cell = this.take5(this.game.CurrentPlayer, this.game.GameId);
				if(cell != null)
				{
					return cell;
				}
				else		
				{	
					cell = extraxtCellFromLastCombination(game, cell);
				}
				if(cell != null)
					return cell;
				if(cell == null)
					return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
		
			
			case 4:
				// Defense, Any combination, Take 5
				// Defense, Any combination, Take 5
				System.out.println( "================================");
				System.out.println( "");
				System.out.println( "Fifth move : whatICan");
				System.out.println( "");
				System.out.println( "================================");

				this.LastUsedCombination = this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
				cell = null;
				if(this.LastUsedCombination != null)
					cell = this.LastUsedCombination.getRemainingCell(game);
				//  Defense, Any combination, Take 5, 
				return (cell != null) ? cell : this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			
	/*	*/	
			case 5:
				// Defense, Any Strike, Take 5
				// Defense, Any combination, Take 5
				System.out.println( "================================");
				System.out.println( "");
				System.out.println( "Sixth  move : whatICan");
				System.out.println( "");
				System.out.println( "================================");
/*
				this.LastUsedCombination = this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
				cell = null;
				if(this.LastUsedCombination != null)
					cell = this.LastUsedCombination.getRemainingCell(game);
				//  Defense, Any combination, Take 5, 
				return (cell != null) ? cell : this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			*/
				cell = this.take5(this.game.CurrentPlayer, this.game.GameId);
				if(cell != null)
				{
					return cell;
				}
				else		
				{	
					cell = extraxtCellFromLastCombination(game, cell);
				}
				if(cell != null)
					return cell;
				if(cell == null)
					return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			
			
			
			case 6:
				// Defense, Any Strike, Take 5				// Defense, Any combination, Take 5

				System.out.println( "================================");
				System.out.println( "");
				System.out.println( "Seventh  move : whatICan");
				System.out.println( "");
				System.out.println( "================================");
/*
				this.LastUsedCombination = this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
				cell = null;
				if(this.LastUsedCombination != null)
					cell = this.LastUsedCombination.getRemainingCell(game);
				//  Defense, Any combination, Take 5, 
				return (cell != null) ? cell : this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			*/
				cell = this.take5(this.game.CurrentPlayer, this.game.GameId);
				if(cell != null)
				{
					return cell;
				}
				else		
				{	
					cell = extraxtCellFromLastCombination(game, cell);
				}
				if(cell != null)
					return cell;
				if(cell == null)
					return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			
			
			
			case 7:
				// Defense, Any Strike, Take 5				// Defense, Any combination, Take 5

				System.out.println( "================================");
				System.out.println( "");
				System.out.println( "Eight  move : whatICan");
				System.out.println( "");
				System.out.println( "================================");
			/*
				this.LastUsedCombination = this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
				cell = null;
				if(this.LastUsedCombination != null)
					cell = this.LastUsedCombination.getRemainingCell(game);
				//  Defense, Any combination, Take 5, 
				System.out.println( "whatICan");
				return (cell != null) ? cell : this.whatICan(this.game.CurrentPlayer, this.game.GameId);*/
				
				cell = this.take5(this.game.CurrentPlayer, this.game.GameId);
				if(cell != null)
				{
					return cell;
				}
				else		
				{	
					cell = extraxtCellFromLastCombination(game, cell);
				}
				if(cell != null)
					return cell;
				if(cell == null)
					return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			
			
			
			case 8:
				// What You Can				// Defense, Any combination, Take 5

				System.out.println( "================================");
				System.out.println( "");
				System.out.println( "Last  move : whatICan");
				System.out.println( "");
				System.out.println( "================================");
/*
				this.LastUsedCombination = this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
				cell = null;
				if(this.LastUsedCombination != null)
					cell = this.LastUsedCombination.getRemainingCell(game);
				//  Defense, Any combination, Take 5, 
				System.out.println( "whatICan");
				return (cell != null) ? cell : this.whatICan(this.game.CurrentPlayer, this.game.GameId);*/
				cell = this.take5(this.game.CurrentPlayer, this.game.GameId);
				if(cell != null)
				{
					return cell;
				}
				else		
				{	
					cell = extraxtCellFromLastCombination(game, cell);
				}
				if(cell != null)
					return cell;
				if(cell == null)
					return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			
			
			default:
					break;
		}


		System.out.println( "Vraćam null vrijednost");
		return null;
		
	}
	/**
	 * @param game
	 * @param cell
	 * @return
	 */
	private Cell extraxtCellFromLastCombination(Game game, Cell cell) {
		List<Combination> potComb;
		if(this.LastUsedCombination != null)
		{		
			potComb = this.computerCombinationsList.stream().filter(
			//	p.rowNo.equals(this.LastUsedCombination.rowNo)
			//	&& p.colNo.equals(this.LastUsedCombination.colNo) 
				p ->  p.type.equals(this.LastUsedCombination.type)
				&& !p.getPotential().equals(CombinationStatus.OCCUPIED)
				&& !p.getOpositePotential().equals(CombinationStatus.POTENTIAL)
				&& !p.isAchieved()
			).collect(Collectors.toList());
			
			
			System.out.println("Filtering");
			Combination tmp = null;
			if(potComb.size() > 0)
				tmp = potComb.get(potComb.size()-1);
			this.LastUsedCombination = 	tmp;
		}
		if(this.LastUsedCombination	== null || this.LastUsedCombination.isAchieved())	
			this.LastUsedCombination = this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
		
		if(this.LastUsedCombination != null)
			cell = this.LastUsedCombination.getRemainingCell(game);
		
		System.out.println("Extracting cell from last combination" + cell);
		return cell;
	}		
	private Cell randomCell(Player player, Integer gameId)
	{

		System.out.println( "Random cell");
		Cell cell;
		do
		{
			Integer row = (int) ((Math.random() * 3) +1);
			Integer coll = (int) ((Math.random() * 3) +1);
			cell = new Cell(row, coll, gameId, player);	
			
		}
		while(this.game.checkIfCellsCantBeAdded(cell));

		System.out.println(cell.Row + " : " + cell.Column);
		return cell;
	}	
	private Cell take5(Player player, Integer gameId)
	{
		Cell cell =  new Cell(2, 2, gameId, player);

		Boolean isFree= this.game.checkIfCellsCantBeAdded(cell);
		System.out.println( "TAKE 5: " + isFree.toString());
		System.out.println(cell);
		if(!isFree)
		{
			return cell;
		}
		else
		{
			return null;
		}
	}
	private Combination anyCombination(Player player, Integer gameId)
	{

		Combination combination = null;
		System.out.println("Strategy begin");
		System.out.println();
		System.out.println("Get combination");
		
		fillAllCombinations();
		this.game.analyzeCombinations(player, this.game.GetOppositePlayer());
		/*
		 * 
		 * 	Parse combinations: group combinations by status, and return the most valuable move
		 * 
		 * 	Process own very potential
		 * 	Process opposite very potential
		 * 	Process own potential
		 * 	Process opposite potential
		 * 
		 * 
		 * */
		Predicate<Combination> hasPotentialPredicate  =  e -> e.getPotential().equals(CombinationStatus.POTENTIAL);
		Predicate<Combination> hasVeryPotentialPredicate  = e -> e.getPotential().equals(CombinationStatus.VERY_POTENTIAL);
				

		Boolean hasComputerPotential = this.computerCombinationsList.stream().filter(hasPotentialPredicate).findFirst().isPresent();
		Boolean hasComputerVeryPotential = this.computerCombinationsList.stream().filter(hasVeryPotentialPredicate).findFirst().isPresent();
		Boolean hasPlayerPotential = this.playerCombinationsList.stream().filter(hasPotentialPredicate).findFirst().isPresent();
		Boolean hasPlayerVeryPotential = this.playerCombinationsList.stream().filter(hasVeryPotentialPredicate).findFirst().isPresent();

		if(hasComputerPotential )
			computerPotentialList = this.computerCombinationsList.stream().filter(hasPotentialPredicate).collect(Collectors.toList());	
		if(hasComputerVeryPotential )
			computerVeryPotentialList = this.computerCombinationsList.stream().filter(hasVeryPotentialPredicate).collect(Collectors.toList());	
		if(hasPlayerPotential )
			playerPotentialList = this.playerCombinationsList.stream().filter(hasPotentialPredicate).collect(Collectors.toList());	
		if(hasPlayerVeryPotential )
			playerVeryPotentialList = this.playerCombinationsList.stream().filter(hasVeryPotentialPredicate).collect(Collectors.toList());		

		System.out.println("ownPotentialList size= " + computerPotentialList.size());
	//	ownPotentialList.forEach(c -> c.OutputAll());
		System.out.println("ownVeryPotentialList size= " + computerVeryPotentialList.size());
	//	ownVeryPotentialList.forEach(c -> c.OutputAll());
		System.out.println("oppositePotentialList size= " + playerPotentialList.size());
	//	oppositePotentialList.forEach(c -> c.OutputAll());
		System.out.println("oppositeVeryPotentialList size= " + playerVeryPotentialList.size());
	//	oppositeVeryPotentialList.forEach(c -> c.OutputAll());
		
		if(playerVeryPotentialList.size() > 0)
		{	
			System.out.println();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("++++++++++++++++++++Defence++++++++++++++++++++++++++++++++++++++");
			combination = playerVeryPotentialList.get(0);
			this.LastUsedCombination = combination;
			combination.OutputAll();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println();
			System.out.println();
		
		}
		else if(computerVeryPotentialList.size() > 0)
		{	
			System.out.println();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("++++++++++++++Attack++++++++++++++++++++++++++++++++++++++++++++++");
			combination = computerVeryPotentialList.get(0);
			this.LastUsedCombination = combination;
			combination.OutputAll();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println();
			System.out.println();
		
		}
/* *
		
		
		
        Map<CombinationStatus, List<Combination>> combinationsGroupedByByStatus = this.combinationsList.stream().collect(Collectors.groupingBy(c -> c.getPotential()));
        Map<CombinationStatus, List<Combination>> oCombinationsGroupedByByStatus = this.combinationsList.stream().collect(Collectors.groupingBy(c -> c.getPotential()));
        
        
        //		Get OWN Very Potential
        boolean veryPotentialExists =  combinationsGroupedByByStatus.containsKey(CombinationStatus.VERY_POTENTIAL);
        List<Combination> veryPotencialList = new ArrayList<Combination>();
		if(veryPotentialExists)
        	veryPotencialList = combinationsGroupedByByStatus.getOrDefault(CombinationStatus.VERY_POTENTIAL, null);
		
        
        //		Get OWN Potential
		boolean potentialExists =  combinationsGroupedByByStatus.containsKey(CombinationStatus.POTENTIAL);
        List<Combination> potencialList = new ArrayList<Combination>();
		if(potentialExists)
        	potencialList = combinationsGroupedByByStatus.getOrDefault(CombinationStatus.VERY_POTENTIAL, null);  

        
        //		Get OPPOSITE Very Potential
        boolean oppositeVeryPotentialExists =  oCombinationsGroupedByByStatus.containsKey(CombinationStatus.VERY_POTENTIAL);
        List<Combination> oppositeVeryPotencialList = new ArrayList<Combination>();
		if(oppositeVeryPotentialExists)
			oppositeVeryPotencialList = oCombinationsGroupedByByStatus.getOrDefault(CombinationStatus.VERY_POTENTIAL, null);

        
        //		Get OPPOSITE Potential
		boolean oppositePotentialExists =  oCombinationsGroupedByByStatus.containsKey(CombinationStatus.POTENTIAL);
        List<Combination> oppositePotencialList = new ArrayList<Combination>();
		if(oppositePotentialExists)
			oppositePotencialList = combinationsGroupedByByStatus.getOrDefault(CombinationStatus.VERY_POTENTIAL, null);

        if(veryPotencialList != null && veryPotencialList.size() > 0){
        	System.out.println("Own Very potential ");
        	combination = veryPotencialList.stream().findFirst().get();
        	combination.OutputAll();
        	return combination;
        }
        if(oppositeVeryPotencialList != null && oppositeVeryPotencialList.size() > 0){
        	System.out.println("Opposite Very potential ");
        	combination = oppositeVeryPotencialList.stream().findFirst().get();
        	combination.OutputAll();
        	return combination;
        }
        if(potencialList != null && potencialList.size() > 0){
        	System.out.println("Own potential ");
        	combination = potencialList.stream().findFirst().get();
        	combination.OutputAll();
        	return combination;
        }
        if(oppositePotencialList != null && oppositePotencialList.size() > 0){

        	System.out.println("Opposite potential ");
        	combination = oppositePotencialList.stream().findFirst().get();
        	combination.OutputAll();
         	return combination;
        }	
        
        
* */
        return combination;
	}
	
	
	private Cell whatICan(Player player, Integer gameId)
	{
		
		System.out.println("What CAN I PLAY");
		ArrayList<ArrayList<Integer>> comb = new ArrayList<ArrayList<Integer>>();
		//comb = proccessCombinationComputerMove();
		/**
		 * 	Potencijalni problem
		 * 
		 */

		Cell cell = proccessCombinationComputerMove();
		
		System.out.println(cell);

	/*	Combination combination = this.pickAnyCombination(comb);
		
		if(combination != null)
		{
			System.out.println("broj poteza: " + combination.getCells().size());
			cell = combination.getRemainingCell(this.game);
			
			
		}*/
		if(cell != null)
		{
			return cell;
		}
		else
			return this.randomCell(this.game.CurrentPlayer, this.game.GameId);
       
	}
	/**
	 * @param comb Empty List<Combination>
	 * @return List<Combination>
	 */
	private Cell proccessCombinationComputerMove() {
		
		Combination rows1 = new Combination(GameRuleType.HORIZONTAL,GameRowType.FIRST);
		Combination rows2 = new Combination(GameRuleType.HORIZONTAL,GameRowType.SECOND);
		Combination rows3 = new Combination(GameRuleType.HORIZONTAL,GameRowType.THIRD);
		Combination columns1 =new Combination(GameRuleType.VERTICAL, GameCellType.A);
		Combination columns2 = new Combination(GameRuleType.VERTICAL, GameCellType.B);
		Combination columns3 = new Combination(GameRuleType.VERTICAL, GameCellType.C);
		Combination diagonal1 = new Combination(GameRuleType.DIAGONAL, GameCellType.A);
		Combination diagonal2 = new Combination(GameRuleType.DIAGONAL, GameCellType.C);
		
		for (Cell cell : this.game.Game) {
			if (cell.Row == 1) {
				rows1.addCell2CombinationCells(cell);
				if (cell.Column == 1) {
					columns1.addCell2CombinationCells(cell);
					diagonal1.addCell2CombinationCells(cell);
				}
				if (cell.Column == 2) {
					columns2.addCell2CombinationCells(cell);
				}

				if (cell.Column == 3) {
					columns3.addCell2CombinationCells(cell);
					diagonal2.addCell2CombinationCells(cell);
				}
			}
			if (cell.Row == 2) {

				rows2.addCell2CombinationCells(cell);
				if (cell.Column == 1) {		
					columns1.addCell2CombinationCells(cell);
				}
				if (cell.Column == 2) {
					columns2.addCell2CombinationCells(cell);
					diagonal1.addCell2CombinationCells(cell);
					diagonal2.addCell2CombinationCells(cell);
				}

				if (cell.Column == 3) {
					columns3.addCell2CombinationCells(cell);
				}

			}
			if (cell.Row == 3) {
				rows3.addCell2CombinationCells(cell);
				if (cell.Column == 1) {
					columns1.addCell2CombinationCells(cell);
					diagonal2.addCell2CombinationCells(cell);
				}
				if (cell.Column == 2) {
					columns2.addCell2CombinationCells(cell);
				}

				if (cell.Column == 3) {
					columns3.addCell2CombinationCells(cell);
					diagonal1.addCell2CombinationCells(cell);
				}
			}
		}
		
		List<Combination> availableCombinations = this.game._fillCells2Combination();
		List<CellCoordinates> possibleMoves = new ArrayList<CellCoordinates>();
		
		if(rows1 != null)
		{	
			CellCoordinates move = rows1.checkCombination(this.game.CurrentPlayer, this.game.GetOppositePlayer());
			possibleMoves.add(move);
			if(rows1.getPotential() == CombinationStatus.VERY_POTENTIAL )
			{	System.out.println("play now to win");
				// play now to win
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
				
			}
			else if(rows1.getPotential() == CombinationStatus.MUST_DEFFEND )
			{	
				// play now to stay in game
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}
				
		/*	CombinationStatus row1PlayerCheckedCombination = rows1.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			if(rows1.getCells().size() != 0 || row1ComputerCheckedCombination != CombinationStatus.OCCUPIED)		
			{
				//rows1.OutputAll();
				comb.add(rows1);
			}*/
		}
		if(rows2 != null)
		{
			CellCoordinates move = rows2.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			
			possibleMoves.add(move);
			if(rows2.getPotential() == CombinationStatus.VERY_POTENTIAL )

			{	System.out.println("play now to win");
				// play now to win
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}
			else if(rows2.getPotential() == CombinationStatus.MUST_DEFFEND )
			{	
				// play now to stay in game
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}/*
			if(rows2.getCells().size() != 0 || row2CheckedCombination != CombinationStatus.OCCUPIED)		
			{
				//rows2.OutputAll();
				comb.add(rows2);
			}*/

		}
		if(rows3 != null)	
		{
			CellCoordinates move = rows3.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			possibleMoves.add(move);
			

			if(rows3.getPotential() == CombinationStatus.VERY_POTENTIAL )

			{	System.out.println("play now to win");	
				// play now to win
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}
			else if(rows3.getPotential() == CombinationStatus.MUST_DEFFEND )
			{	
				// play now to stay in game
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}/*
			if(rows3.getCells().size() != 0 || row3CheckedCombination != CombinationStatus.OCCUPIED)		
			{
				//rows3.OutputAll();
				comb.add(rows3);
			}*/
		}
		
		if(columns1 != null)	
		{
			CellCoordinates move = columns1.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			possibleMoves.add(move);

			if(columns1.getPotential() == CombinationStatus.VERY_POTENTIAL )

			{	System.out.println("play now to win");	
				// play now to win
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}
			else if(columns1.getPotential() == CombinationStatus.MUST_DEFFEND )
			{	
				// play now to stay in game
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}/*if(columns1.getCells().size() != 0 || column1CheckedCombination != CombinationStatus.OCCUPIED)		
			{
				//columns1.OutputAll();
				comb.add(columns1);
			}*/
		}
		
		if(columns2 != null)	
		{
			CellCoordinates move = columns2.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			possibleMoves.add(move );

			if(columns2.getPotential() == CombinationStatus.VERY_POTENTIAL )

			{	System.out.println("play now to win");	
				// play now to win
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}
			else if(columns2.getPotential() == CombinationStatus.MUST_DEFFEND )
			{	
				// play now to stay in game
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}
			/*if(columns2.getCells().size() != 0 || column2CheckedCombination != CombinationStatus.OCCUPIED)
			{
				//columns2.OutputAll();
				comb.add(columns2);
			}*/
		}
		
		if(columns3 != null)	
		{
			CellCoordinates move = columns3.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			possibleMoves.add( move);

			if(columns3.getPotential() == CombinationStatus.VERY_POTENTIAL )

			{	System.out.println("play now to win");	
				// play now to win
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}
			else if(columns3.getPotential() == CombinationStatus.MUST_DEFFEND )
			{	
				// play now to stay in game
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}/*if(columns3.getCells().size() != 0 || column3CheckedCombination == CombinationStatus.OCCUPIED)
			{
				//columns3.OutputAll();
				comb.add(columns3);
			}*/
		}
		
		if(diagonal1 != null )	
		{
			CellCoordinates move = diagonal1.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			possibleMoves.add( move);
			

			if(diagonal1.getPotential() == CombinationStatus.VERY_POTENTIAL )

			{	System.out.println("play now to win");
				// play now to win
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}
			else if(diagonal1.getPotential() == CombinationStatus.MUST_DEFFEND )
			{	
				// play now to stay in game
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}
			/*if(diagonal1.getCells().size() != 0 || diagonal1CheckedCombination == CombinationStatus.OCCUPIED)		
			{
				//diagonal1.OutputAll();
				comb.add(diagonal1);
			}*/
		}
		
		if(diagonal2 != null )	
		{
			CellCoordinates move =  diagonal2.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			possibleMoves.add(move);
			

			if(diagonal2.getPotential() == CombinationStatus.VERY_POTENTIAL )

			{	System.out.println("play now to win");	
				// play now to win
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}
			else if(diagonal2.getPotential() == CombinationStatus.MUST_DEFFEND )
			{	
				// play now to stay in game
				return new Cell(move.getX(), move.getY(), this.game.GameId, this.game.CurrentPlayer);
			}
			/*if(diagonal1.getCells().size() != 0 || diagonal2CheckedCombination == CombinationStatus.OCCUPIED)		
			{
				//diagonal2.OutputAll();
				comb.add(diagonal2);
			}*/
		}
		return null;
	}
	
	
	
	private Combination pickAnyCombination(List<Combination> comb) {
		// TODO Auto-generated method stub
		if(comb.size() == 0)
		{
			System.out.println("pickAnyCombination :  NULL)");
			return null;
		}
		else if(comb.size() == 1)
		{
			System.out.println("pickAnyCombination :  " + comb.get(0));
			return comb.get(0);
		}
		else
		{
			int key = (int)(Math.random() * comb.size());
			System.out.println("pickAnyCombination :  " + comb.get(key));
			return comb.get(key);
		}
				
	}
	/**
	 * @return 
	 * 
	 */
	private void fillAllCombinations() {
		/*
		 * Razvrstavanje mogućih kombinacija po potencijalu za oba igrača
		 * */
		this.computerCombinationsList.clear();
		this.playerCombinationsList.clear();
		for(Combination comb : this.game._fillCells2Combination())
		{
			if(comb.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer) != null)
			{	
				//System.out.println("Dodajem opciju za protivnika: "  + comb.type + ", Red: " + comb.rowNo+ " , Kolona: " + comb.colNo );
				playerCombinationsList.add(comb);
				
			}
			if(comb.checkCombination(this.game.CurrentPlayer, this.game.GetOppositePlayer()) != null)
			{	
				//System.out.println("Dodajem opciju za trenutnog igrača: "  + comb.type + ", Red: " + comb.rowNo+ " , Kolona: " + comb.colNo );
				computerCombinationsList.add(comb);
			}
		}
		
	}
}
