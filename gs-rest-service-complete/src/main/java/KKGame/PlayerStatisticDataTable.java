package KKGame;

import java.util.List;

public class PlayerStatisticDataTable {
	
	public Integer draw;
	public Integer recordsTotal;
	public Integer recordsFiltered;
	public List<Player> data;
	public PlayerStatisticDataTable(Integer draw, Integer recordsTotal, Integer recordsFiltered, List<Player> data) {
		super();
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}
	

}
