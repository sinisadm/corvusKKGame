package KKGame;

public class GameComputerPlayer {
	
	private Integer gameStage;
	private Integer gameStrategy;
	private Game game;
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
				break;
			case 3:
				// Defense, Any combination, Take 5
				break;
			case 4:
				// Defense, Any combination, Take 5
				break;
			case 5:
				// Defense, Any Strike, Take 5
				break;
			case 6:
				// Defense, Any Strike, Take 5
				break;
			case 7:
				// Defense, Any Strike, Take 5
				break;
	
			case 8:
				// What You Can
				break;
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
}
