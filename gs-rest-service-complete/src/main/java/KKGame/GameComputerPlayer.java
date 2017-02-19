package KKGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GameComputerPlayer {
	
	private Integer gameStage;
	private Integer gameStrategy;
	private Game game;
	private Combination LastUsedCombination = null;
	private List<Combination> combinationsList = new ArrayList<Combination>();
	private List<Combination> oCombinationsList = new ArrayList<Combination>();
	private List<Combination> ownVeryPotentialList = new ArrayList<Combination>();
	private List<Combination> oppositeVeryPotentialList = new ArrayList<Combination>();
	private List<Combination> ownPotentialList = new ArrayList<Combination>();
	private List<Combination> oppositePotentialList = new ArrayList<Combination>();
	public Cell Hint(Game game, ComputerSkill skill)
	{
		this.game = game;
		this.gameStage = game.Game.size();
		this.combinationsList.clear();
		this.oCombinationsList.clear();
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
				System.out.println( "First move : Random cell");
				System.out.println( "");
				System.out.println( "================================");
				return this.randomCell(this.game.CurrentPlayer, this.game.GameId);
			
			
			case 1:
				System.out.println( "================================");
				System.out.println( "");
				System.out.println( "Second  move : whatICan");
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
/*
				this.LastUsedCombination = this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
				cell = null;
				if(this.LastUsedCombination != null)
					cell = this.LastUsedCombination.getRemainingCell(game);
				//  Defense, Any combination, Take 5, 
				return (cell != null) ? cell : this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			
		*/	
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
			potComb = this.combinationsList.stream().filter(
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
				

		Boolean hasOwnPotential = this.combinationsList.stream().filter(hasPotentialPredicate).findFirst().isPresent();
		Boolean hasOwnVeryPotential = this.combinationsList.stream().filter(hasVeryPotentialPredicate).findFirst().isPresent();
		Boolean hasOppositePotential = this.oCombinationsList.stream().filter(hasPotentialPredicate).findFirst().isPresent();
		Boolean hasOppositeVeryPotential = this.oCombinationsList.stream().filter(hasVeryPotentialPredicate).findFirst().isPresent();

		if(hasOwnPotential )
			ownPotentialList = this.combinationsList.stream().filter(hasPotentialPredicate).collect(Collectors.toList());	
		if(hasOwnVeryPotential )
			ownVeryPotentialList = this.combinationsList.stream().filter(hasVeryPotentialPredicate).collect(Collectors.toList());	
		if(hasOppositePotential )
			oppositePotentialList = this.oCombinationsList.stream().filter(hasPotentialPredicate).collect(Collectors.toList());	
		if(hasOppositeVeryPotential )
			oppositeVeryPotentialList = this.oCombinationsList.stream().filter(hasVeryPotentialPredicate).collect(Collectors.toList());		

		System.out.println("ownPotentialList size= " + ownPotentialList.size());
	//	ownPotentialList.forEach(c -> c.OutputAll());
		System.out.println("ownVeryPotentialList size= " + ownVeryPotentialList.size());
	//	ownVeryPotentialList.forEach(c -> c.OutputAll());
		System.out.println("oppositePotentialList size= " + oppositePotentialList.size());
	//	oppositePotentialList.forEach(c -> c.OutputAll());
		System.out.println("oppositeVeryPotentialList size= " + oppositeVeryPotentialList.size());
	//	oppositeVeryPotentialList.forEach(c -> c.OutputAll());
		
		if(ownVeryPotentialList.size() > 0)
		{	
			System.out.println();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			combination = ownVeryPotentialList.get(0);
			this.LastUsedCombination = combination;
			combination.OutputAll();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println();
			System.out.println();
		
		}
		
/*******************
		
		
		
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
        
        
        ***********/
        return combination;
	}
	
	
	private Cell whatICan(Player player, Integer gameId)
	{
		
		System.out.println("What CAN I PLAY");
		List<Combination> comb = new ArrayList<Combination>();
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

		if(rows1 != null)
		{	
		
			CombinationStatus row1CheckedCombination = rows1.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			if(rows1.getCells().size() != 0 || row1CheckedCombination != CombinationStatus.OCCUPIED)		
			{
				//rows1.OutputAll();
				comb.add(rows1);
			}
		}
		if(rows2 != null)
		{
			CombinationStatus row2CheckedCombination = rows1.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			if(rows2.getCells().size() != 0 || row2CheckedCombination != CombinationStatus.OCCUPIED)		
			{
				//rows2.OutputAll();
				comb.add(rows2);
			}

		}
		if(rows3 != null)	
		{
			CombinationStatus row3CheckedCombination = rows1.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			if(rows3.getCells().size() != 0 || row3CheckedCombination != CombinationStatus.OCCUPIED)		
			{
				//rows3.OutputAll();
				comb.add(rows3);
			}
		}
		
		if(columns1 != null)	
		{
			CombinationStatus column1CheckedCombination = columns1.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			if(columns1.getCells().size() != 0 || column1CheckedCombination != CombinationStatus.OCCUPIED)		
			{
				//columns1.OutputAll();
				comb.add(columns1);
			}
		}
		
		if(columns2 != null)	
		{
			CombinationStatus column2CheckedCombination = columns2.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			if(columns2.getCells().size() != 0 || column2CheckedCombination != CombinationStatus.OCCUPIED)
			{
				//columns2.OutputAll();
				comb.add(columns2);
			}
		}
		
		if(columns3 != null)	
		{
			CombinationStatus column3CheckedCombination = columns3.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			if(columns3.getCells().size() != 0 || column3CheckedCombination == CombinationStatus.OCCUPIED)
			{
				//columns3.OutputAll();
				comb.add(columns3);
			}
		}
		
		if(diagonal1 != null )	
		{
			CombinationStatus diagonal1CheckedCombination = diagonal1.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			if(diagonal1.getCells().size() != 0 || diagonal1CheckedCombination == CombinationStatus.OCCUPIED)		
			{
				//diagonal1.OutputAll();
				comb.add(diagonal1);
			}
		}
		
		if(diagonal2 != null )	
		{
			CombinationStatus diagonal2CheckedCombination = diagonal2.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer);
			if(diagonal1.getCells().size() != 0 || diagonal2CheckedCombination == CombinationStatus.OCCUPIED)		
			{
				//diagonal2.OutputAll();
				comb.add(diagonal2);
			}
		}
		

		Combination combination = this.pickAnyCombination(comb);
		Cell cell = null;
		
		if(combination != null)
		{
			System.out.println("broj poteza: " + combination.getCells().size());
			cell = combination.getRemainingCell(this.game);
			
			
		}
		if(cell != null)
		{
			return cell;
		}
		else
			return this.randomCell(this.game.CurrentPlayer, this.game.GameId);
       
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
		this.combinationsList.clear();
		this.oCombinationsList.clear();
		for(Combination comb : this.game._fillCells2Combination())
		{
			if(comb.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer)  != CombinationStatus.OCCUPIED)
			{	
				//System.out.println("Dodajem opciju za protivnika: "  + comb.type + ", Red: " + comb.rowNo+ " , Kolona: " + comb.colNo );
				oCombinationsList.add(comb);
				
			}
			if(comb.checkCombination(this.game.CurrentPlayer, this.game.GetOppositePlayer())  != CombinationStatus.OCCUPIED)
			{	
				//System.out.println("Dodajem opciju za trenutnog igrača: "  + comb.type + ", Red: " + comb.rowNo+ " , Kolona: " + comb.colNo );
				combinationsList.add(comb);
			}
		}
		
	}
}
