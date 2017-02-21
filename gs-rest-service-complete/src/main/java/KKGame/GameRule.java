package KKGame;

import java.util.List;

public abstract class GameRule {
	
	private GameRuleType _type;
	private GameRowType _row;
	private GameCellType _coll;
	private List<GameMapField> _gameMap;
	public GameRule(GameRuleType type)
	{
		this._type = type;
		this._fillGameMap();
		this._fillRules();
		
	}
	private void _fillRules() {
		// TODO Auto-generated method stub
		
	}
/*	public GameRule(GameRuleType type, GameRowType row)
	{
		this._type = type;
		this._row = row;
		this._fillGameMap();
	}*/
	public GameRule(GameRuleType type, GameCellType coll)
	{
		this._type = type;
		this._coll = coll;
		this._fillGameMap();
	}
	public GameRule(GameRuleType type, GameRowType row, GameCellType coll)
	{
		this._type = type;
		this._row = row;
		this._coll = coll;
		this._fillGameMap();
	}
	public GameRule(GameRuleType horizontal, GameRowType first) {
		// TODO Auto-generated constructor stub
	}
	/*private Boolean _isObbeyed(Game game)
	{
		switch(this._type)
		{
		case HORIZONTAL:
			if(this._row != null && this._row == GameRowType.FIRST)
			break;
		case VERTICAL:
			
			break;
		case DIAGONAL:
			
			break;

		
		}
		return false;
	}*/
	
	private void _fillGameMap() {
		this._gameMap.add(new GameMapField(GameRowType.FIRST, GameCellType.A));
		this._gameMap.add(new GameMapField(GameRowType.FIRST, GameCellType.B));
		this._gameMap.add(new GameMapField(GameRowType.FIRST, GameCellType.C));
		this._gameMap.add(new GameMapField(GameRowType.SECOND, GameCellType.A));
		this._gameMap.add(new GameMapField(GameRowType.SECOND, GameCellType.B));
		this._gameMap.add(new GameMapField(GameRowType.SECOND, GameCellType.C));
		this._gameMap.add(new GameMapField(GameRowType.THIRD, GameCellType.A));
		this._gameMap.add(new GameMapField(GameRowType.THIRD, GameCellType.B));
		this._gameMap.add(new GameMapField(GameRowType.THIRD, GameCellType.C));
	}
}
