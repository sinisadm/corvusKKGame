package KKGame;

public class GameMapField {
	private GameRowType _row;
	private GameCellType _cell;
	public GameMapField(GameRowType _row, GameCellType _cell) {
		super();
		this._row = _row;
		this._cell = _cell;
	}
	
	public GameRowType get_row() {
		return _row;
	}

	public GameCellType get_cell() {
		return _cell;
	}
}
