package KKGame;

public class Cell {

	protected Integer GameId;
	protected Player Player;
	protected Integer Row;
	protected Integer Column;
	
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


	public Integer getRow() {
		return Row;
	}
	public Integer getColumn() {
		return Column;
	}

	
}
