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
		this.combinationsList.clear();
		this.oCombinationsList.clear();
		this.fillAllCombinations();
		
		switch(this.gameStage)
		{
			case 0:
				// Random cell, Take 5

				System.out.println( "Random cell");
				return this.randomCell(this.game.CurrentPlayer, this.game.GameId);
			case 1:
				// Take 5, Random cell
				System.out.println( "Take 5");
				
				Cell cell = null;
				cell = this.take5(this.game.CurrentPlayer, this.game.GameId);
				if(cell == null)
					cell = this.anyCombination(this.game.CurrentPlayer, this.game.GameId);
				if(cell == null)
					return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			case 2:
				//  Defense, Any combination, Take 5, 
				System.out.println( "whatICan");
				return this.whatICan(this.game.CurrentPlayer, this.game.GameId);

			case 3:
				// Defense, Any combination, Take 5
				System.out.println( "whatICan");
				return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			case 4:
				// Defense, Any combination, Take 5
				System.out.println( "whatICan");
				return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			case 5:
				// Defense, Any Strike, Take 5
				System.out.println( "whatICan");
				return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			case 6:
				// Defense, Any Strike, Take 5
				System.out.println( "whatICan");
				return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
			case 7:
				// Defense, Any Strike, Take 5
				System.out.println( "AwhatICann");
				return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
	
			case 8:
				// What You Can
				System.out.println( "whatICan");
				return this.whatICan(this.game.CurrentPlayer, this.game.GameId);
				default:
					break;
		}


		System.out.println( "Vraćam null vrijednost");
		return null;
		
	}		
	private Cell randomCell(Player player, Integer gameId)
	{
		Integer row = (int) ((Math.random() * 3) +1);
		Integer coll = (int) ((Math.random() * 3) +1);

		System.out.println( "Random cell: row :" + row + " , column: " + coll);
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

		Cell cell = null;
		fillAllCombinations();
		Integer key = (int)(Math.random() * this.combinationsList.size());		
		if(key <= this.combinationsList.size())
		{
			Combination combination = this.combinationsList.get(key);
			List<Cell> stack = combination.GeneratePossibleMoves();
			Integer key2 = (int)(Math.random() * stack.size());		
			if(key2 > stack.size())
				cell =  stack.get(key2);
		
			//Boolean isFree=! this.game.checkIfCellsCantBeAdded(cell);
			
			System.out.println("Tražim Kombinaciju :");
			System.out.println("Broj kombinacija: :" + stack.size());
			System.out.println("Tražim Key :");
			System.out.println("Tražim ID :");
			System.out.println("Tražim Kombinaciju :");
	
			
			return cell;	
		}	
		else
			return this.whatICan(player, gameId);
	}
	
	
	private Cell whatICan(Player player, Integer gameId)
	{
		
		System.out.println("What CAN I PLAY");
		List<Combination> comb = new ArrayList<Combination>();
		Combination rows1 = new Combination(GameRuleType.HORIZONTAL,GameRowType.FIRST);
		Combination rows2 = new Combination(GameRuleType.HORIZONTAL,GameRowType.SECOND);
		Combination rows3 = new Combination(GameRuleType.HORIZONTAL,GameRowType.THIRD);;
		Combination columns1 =new Combination(GameRuleType.VERTICAL, GameCellType.A);
		Combination columns2 = new Combination(GameRuleType.VERTICAL, GameCellType.B);
		Combination columns3 = new Combination(GameRuleType.VERTICAL, GameCellType.C);
		Combination diagonal1 = new Combination(GameRuleType.DIAGONAL, GameCellType.A);
		Combination diagonal2 = new Combination(GameRuleType.DIAGONAL, GameCellType.C);;
		
		for (Cell cell : this.game.Game) {
			if (cell.Row == 1) {
				rows1.Cells.add(cell);
				if (cell.Column == 1) {
					columns1.Cells.add(cell);
					diagonal1.Cells.add(cell);
				}
				if (cell.Column == 2) {
					columns2.Cells.add(cell);
				}

				if (cell.Column == 3) {
					columns3.Cells.add(cell);
					diagonal2.Cells.add(cell);
				}
			}
			if (cell.Row == 2) {

				rows2.Cells.add(cell);
				if (cell.Column == 1) {		
					columns1.Cells.add(cell);
				}
				if (cell.Column == 2) {
					columns2.Cells.add(cell);
					diagonal1.Cells.add(cell);
					diagonal2.Cells.add(cell);
				}

				if (cell.Column == 3) {
					columns3.Cells.add(cell);
				}

			}
			if (cell.Row == 3) {
				rows3.Cells.add(cell);
				if (cell.Column == 1) {
					columns1.Cells.add(cell);
					diagonal2.Cells.add(cell);

				}
				if (cell.Column == 2) {
					columns2.Cells.add(cell);
				}

				if (cell.Column == 3) {
					columns3.Cells.add(cell);
					diagonal1.Cells.add(cell);
				}
			}
		}

		if(rows1 != null)
		{
			if(rows1.Cells.size() == 0 || rows1.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer) == CombinationStatus.HASFREE)
				comb.add(rows1);
		}
		if(rows2 != null)
		{
			if(rows2.Cells.size() == 0 || rows2.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer) == CombinationStatus.HASFREE)
				comb.add(rows2);

		}
		if(rows3 != null)	
		{
			if(rows3.Cells.size() == 0 || rows3.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer) == CombinationStatus.HASFREE)
				comb.add(rows3);
		}
		
		if(columns1 != null)	
		{
			if(columns1.Cells.size() == 0 || columns1.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer) == CombinationStatus.HASFREE)
				comb.add(columns1);
		}
		
		if(columns2 != null)	
		{
			if(columns2.Cells.size() == 0 || columns2.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer) == CombinationStatus.HASFREE)
				comb.add(columns2);
		}
		
		if(columns3 != null)	
		{
			if(columns3.Cells.size() == 0 || columns3.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer) == CombinationStatus.HASFREE)
				comb.add(columns3);
		}
		
		if(diagonal1 != null && diagonal1.Cells.size() == 0)	
		{
			if(diagonal1.Cells.size() == 0 || diagonal1.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer) == CombinationStatus.HASFREE)
				comb.add(diagonal1);
		}
		
		if(diagonal2 != null && diagonal2.Cells.size() == 0)	
		{

			if(diagonal2.Cells.size() == 0 || diagonal2.checkCombination(this.game.GetOppositePlayer(), this.game.CurrentPlayer) == CombinationStatus.HASFREE )
				comb.add(diagonal2);
		}
		

		System.out.println("Broj mogućih kombinacija : " + comb.size());
		Combination combination = this.pickAnyCombination(comb);
		
		

		System.out.println("broj poteza: " + combination.Cells.size());
		Cell cell = combination.pickAnyCell();
		if(cell != null)
			return cell;
		else
			return this.randomCell(this.game.CurrentPlayer, this.game.GameId);
	/////////////////              
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
				System.out.println("Dodajem opciju za protivnika: "  + comb.type + ", Red: " + comb.rowNo+ " , Kolona: " + comb.colNo );
				oCombinationsList.add(comb);
				
			}
			if(comb.checkCombination(this.game.CurrentPlayer, this.game.GetOppositePlayer())  != CombinationStatus.OCCUPIED)
			{	
				System.out.println("Dodajem opciju za trenutnog igrača: "  + comb.type + ", Red: " + comb.rowNo+ " , Kolona: " + comb.colNo );
				combinationsList.add(comb);
			}
		}
		
	}
}
