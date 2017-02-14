package KKGame;

import java.util.ArrayList;
import java.util.List;

public class GameComputerPlayer {
	
	private Integer gameStage;
	private Integer gameStrategy;
	private Game game;
	
	private List<Combination> combinationsList = new ArrayList<Combination>();
	private List<Combination> oCombinationsList = new ArrayList<Combination>();
	public Cell Hint(Game game, ComputerSkill skill)
	{
		this.game = game;
		this.gameStage = game.Game.size();
		switch(this.gameStage)
		{
			case 0:
				// Random cell, Take 5

				System.out.println( "Random cell");
				return this.randomCell(this.game.CurrentPlayer, this.game.GameId);
			case 1:
				// Take 5, Random cell
				System.out.println( "Take 5");
				return this.take5(this.game.CurrentPlayer, this.game.GameId);
			case 2:
				//  Defense, Any combination, Take 5, 
				System.out.println( "Any combination");
				return this.anyCombination(this.game.CurrentPlayer, this.game.GameId);

			case 3:
				// Defense, Any combination, Take 5
				System.out.println( "Any combination");
				return this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
			case 4:
				// Defense, Any combination, Take 5
				System.out.println( "Any combination");
				return this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
			case 5:
				// Defense, Any Strike, Take 5
				System.out.println( "Any combination");
				return this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
			case 6:
				// Defense, Any Strike, Take 5
				System.out.println( "Any combination");
				return this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
			case 7:
				// Defense, Any Strike, Take 5
				System.out.println( "Any combination");
				return this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
	
			case 8:
				// What You Can
				System.out.println( "Any combination");
				return this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
				default:
					break;
		}

		
		return null;
		
	}		
	private Cell randomCell(Player player, Integer gameId)
	{
		Integer row = (int) ((Math.random() * 3) +1);
		Integer coll = (int) ((Math.random() * 3) +1);

		System.out.println( "Calculated :" + row + " , " + coll);
		return new Cell(row, coll, gameId, player);	
	}	
	private Cell take5(Player player, Integer gameId)
	{
		Cell cell =  new Cell(2, 2, gameId, player);
		
		Boolean isFree=! this.game.checkIfCellsCantBeAdded(cell);
		if(!this.game.checkIfCellsCantBeAdded(cell))
			return cell;
		else
			return null;
	}
	private Cell anyCombination(Player player, Integer gameId)
	{
	
		fillAllCombinations();
		
		Integer key = (int)(Math.random() * this.combinationsList.size());		
		Combination combination = this.combinationsList.get(key);		
		List<Cell> stack = combination.GeneratePossibleMoves();
		Integer key2 = (int)(Math.random() * stack.size());

		Cell cell =  stack.get(key2);
		
		//Boolean isFree=! this.game.checkIfCellsCantBeAdded(cell);
		return cell;

	}
	/**
	 * 
	 */
	private void fillAllCombinations() {
		/*
		 * Razvrstavanje mogućih kombinacija po potencijalu za oba igrača
		 * */
		for(Combination comb : this.game._fillCells2Combination())
		{
			if(comb.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer))
				oCombinationsList.add(comb);
			if(comb.checkCombination(this.game.CurrentPlayer, this.game.GetOppositePlayer()))
				combinationsList.add(comb);
		}
	}
}
