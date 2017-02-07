package KKGame;

public class Cell {

	public Cell(Integer row, Integer column) {
		// TODO Auto-generated constructor stub
		this.Row = row;
		this.Column = column;
		this.GameId =-1;
	}
	
	public Cell(Integer row, Integer column, Integer gameId) {

		// TODO Auto-generated constructor stub
		this.Row = row;
		this.Column = column;
		this.GameId = gameId;
	}

	public Cell(Integer row, Integer column, Integer gameId, Player currentPlayer) {

		// TODO Auto-generated constructor stub
		this.Row = row;
		this.Column = column;
		this.GameId = gameId;
		this.Player = currentPlayer;
	}

	protected Integer Row;
	protected Integer Column;
	protected Integer GameId;
	protected Player Player;
	
	
}
